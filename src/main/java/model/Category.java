package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	public Category(Long id, String name, Set<Category> cats) {
		super();
		this.id = id;
		this.name = name;
//		this.videos = videos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 100, nullable = false)
	private String name;

	// bi-directional many-to-many association to Video
	@ManyToMany(mappedBy = "categores", cascade = CascadeType.ALL)
	private Set<Video> videoByCategory;

	public Category() {
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

	public Set<Video> getVideos() {
		return this.videoByCategory;
	}

	public void setVideos(Set<Video> videoByCategory) {
		this.videoByCategory = videoByCategory;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}