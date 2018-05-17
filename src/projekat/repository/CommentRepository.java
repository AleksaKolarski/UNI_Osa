package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
