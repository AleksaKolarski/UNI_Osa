package projekat.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import projekat.entity.Post;

public class PostDTO implements Serializable {

	private Integer id;
	private String title;
	private String description;
	private byte[] photo;
	private Date date;
	private Integer likes;
	private Integer dislikes;
	private double longitude;
	private double latitude;
	private UserDTO user;
	private List<TagDTO> tags;
	
	public PostDTO() {
	}

	public PostDTO(Integer id, String title, String description, byte[] photo, Date date, Integer likes,
			Integer dislikes, double longitude, double latitude, UserDTO user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.photo = photo;
		this.date = date;
		this.likes = likes;
		this.dislikes = dislikes;
		this.longitude = longitude;
		this.latitude = latitude;
		this.user = user;
	}
	
	public PostDTO(Post post) {
		this(post.getId(), post.getTitle(), post.getDescription(), 
				post.getPhoto(), post.getDate(), post.getLikes(), 
				post.getDislikes(), post.getLongitude(), 
				post.getLatitude(), new UserDTO(post.getUser()));
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}
}
