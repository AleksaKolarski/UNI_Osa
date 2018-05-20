package projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.entity.Comment;
import projekat.repository.CommentRepository;

@Service
public class CommentService implements CommentServiceInterface {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment findById(Integer commentId) {
		return commentRepository.findOne(commentId);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void remove(Integer commentId) {
		commentRepository.delete(commentId);
	}
	
}
