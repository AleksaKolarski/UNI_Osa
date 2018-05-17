package projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.entity.Post;
import projekat.repository.PostRepository;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post findById(Integer postId) {
		return postRepository.findOne(postId);
	}

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void remove(Integer id) {
		postRepository.delete(id);
	}
	
}
