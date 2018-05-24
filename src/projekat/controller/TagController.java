package projekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projekat.dto.TagDTO;
import projekat.entity.Tag;
import projekat.service.TagServiceInterface;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

	@Autowired
	private TagServiceInterface tagService;

	// GET ALL
	@GetMapping
	public ResponseEntity<List<TagDTO>> getTags() {
		List<Tag> tags = tagService.findAll();
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		for (Tag tag : tags) {
			tagsDTO.add(new TagDTO(tag));
		}
		return new ResponseEntity<List<TagDTO>>(tagsDTO, HttpStatus.OK);
	}

	// GET BY ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<TagDTO> getById(@PathVariable("id") Integer id) {
		Tag tag = tagService.findOne(id);
		if (tag == null) {
			return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.OK);
	}
	
	// GET BY POST ID
	@GetMapping(value = "/byPost/{postId}")
	public ResponseEntity<List<TagDTO>> getByPostId(@PathVariable("postId") Integer postId){
		List<Tag> tags = tagService.findByPost_Id(postId);
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		for(Tag tag: tags) {
			tagsDTO.add(new TagDTO(tag));
		}
		return new ResponseEntity<List<TagDTO>>(tagsDTO, HttpStatus.OK);
	}

	// CREATE NEW
	@PostMapping(consumes = "application/json")
	public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
		Tag tag = new Tag();
		tag.setName(tagDTO.getName());
		tag = tagService.save(tag);
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.CREATED);
	}

	// UPDATE
	@PutMapping(value = "/{id}", consumes = "application/json")
	public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDTO, @PathVariable("id") Integer id) {
		Tag tag = tagService.findOne(id);
		if (tag == null) {
			return new ResponseEntity<TagDTO>(HttpStatus.BAD_REQUEST);
		}
		tag.setName(tagDTO.getName());
		tag = tagService.save(tag);
		return new ResponseEntity<TagDTO>(new TagDTO(tag), HttpStatus.OK);
	}

	// DELETE
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteTag(@PathVariable("id") Integer id) {
		Tag tag = tagService.findOne(id);
		if (tag == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		tagService.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
