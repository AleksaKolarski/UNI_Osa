package projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>  {
}
