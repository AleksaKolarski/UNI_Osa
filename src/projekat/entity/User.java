package projekat.entity;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "user_name", unique = false, nullable = false)
	private String name;
	
	@Column(name = "user_username", unique = false, nullable = false)
	private String username;
	
	@Column(name = "user_password", unique = false, nullable = false)
	private String password;
	
	@Lob
	@Column(name = "user_photo", unique = false, nullable = true)
	private byte[] photo;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Comment> comments = new ArrayList<>();

	
	public User() {
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	public void addPost(Post post) {
		post.setUser(this);
		posts.add(post);
	}
	
	public void removePost(Post post) {
		post.setUser(null);
		posts.remove(post);
	}
	
	public void addComment(Comment comment) {
		comment.setUser(this);
		comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		comment.setUser(null);
		comments.remove(comment);
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}
}
