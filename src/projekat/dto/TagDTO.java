package projekat.dto;

import java.io.Serializable;

import projekat.entity.Tag;

public class TagDTO implements Serializable {
	
	private Integer id;
	private String name;
	
	
	public TagDTO() {
	}
	
	public TagDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public TagDTO(Tag tag) {
		this.id = tag.getId();
		this.name = tag.getName();
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
}
