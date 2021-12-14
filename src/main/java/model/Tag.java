package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@NamedQuery(name = "Tag.findAll", query = "SELECT t FROM Tag t")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
	Set<Video> tagVideos;

	public Tag() {
	}

	public Tag(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Video> getTagVideos() {
		return tagVideos;
	}

	public void setTagVideos(Set<Video> tagVideos) {
		this.tagVideos = tagVideos;
	}

}