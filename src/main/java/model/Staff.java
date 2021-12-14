package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@Table(name = "staff")
@NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 500)
	private String name;

	// bi-directional many-to-many association to Video
	@ManyToMany(mappedBy = "staffs", cascade = CascadeType.ALL)
	private Set<Video> videosByStaff;

	public Staff() {
	}

	public Staff(Long id, String name, Set<Video> videos) {
		this.id = id;
		this.name = name;
		this.videosByStaff = videos;
	}

	public Staff(Long id, String name) {
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

	public Set<Video> getVideosByStaff() {
		return videosByStaff;
	}

	public void setVideosByStaff(Set<Video> videosByStaff) {
		this.videosByStaff = videosByStaff;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + "]";
	}

}