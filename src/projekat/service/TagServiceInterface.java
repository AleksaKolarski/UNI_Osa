package projekat.service;

import java.util.List;

import projekat.entity.Tag;

public interface TagServiceInterface {
	
	Tag findOne(Integer tagId);
	
	List<Tag> findAll();
	
	List<Tag> findByPost_Id(Integer postId);
	
	Tag save(Tag tag);
	
	void remove(Integer id);
}
