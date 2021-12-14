package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the studio database table.
 * 
 */
@Entity
@NamedQuery(name = "Studio.findAll", query = "SELECT s FROM Studio s")
public class Studio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 255, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "studios", cascade = CascadeType.ALL)
	Set<Video> stuVideos;

	public Studio() {
	}

	public Studio(Long id, String name) {
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

	public Set<Video> getStuVideos() {
		return stuVideos;
	}

	public void setStuVideos(Set<Video> stuVideos) {
		this.stuVideos = stuVideos;
	}

	@Override
	public String toString() {
		return "Studio [id=" + id + ", name=" + name + "]";
	}

}