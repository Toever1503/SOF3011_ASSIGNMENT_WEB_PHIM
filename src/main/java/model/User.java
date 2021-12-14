package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.math.BigInteger;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false)
	private byte admin;

	@Column()
	private Timestamp dateCreate;

	@Column()
	private Timestamp dateUpdate;

	@Column(nullable = false, length = 255)
	private String email;

	@Column(nullable = false, length = 255)
	private String fullname;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 255)
	private String username;

	@ManyToMany(mappedBy = "likedUser", cascade = CascadeType.ALL)
	private Set<Video> myVideos;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getAdmin() {
		return this.admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public Timestamp getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Timestamp getDateUpdate() {
		return this.dateUpdate;
	}

	public void setDateUpdate(Timestamp dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Video> getMyVideos() {
		return myVideos;
	}

	public void setMyVideos(Set<Video> myVideos) {
		this.myVideos = myVideos;
	}

}