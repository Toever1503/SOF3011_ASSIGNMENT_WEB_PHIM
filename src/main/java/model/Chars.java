package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the chars database table.
 * 
 */
@Entity
@Table(name = "chars")
@NamedQuery(name = "Chars.findAll", query = "SELECT c FROM Chars c")
public class Chars implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 500)
	private String name;

	@ManyToMany(mappedBy = "chars", cascade = CascadeType.ALL)
	private Set<Video> charsVideos;

	public Chars() {
	}

	public Chars(Long id, String name) {
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

	public Set<Video> getCharsVideos() {
		return charsVideos;
	}

	public void setCharsVideos(Set<Video> charsVideos) {
		this.charsVideos = charsVideos;
	}

}