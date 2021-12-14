package model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * The persistent class for the video database table.
 * 
 */
@Entity
@Table(name = "video")
@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v") })
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	private Byte active;

	@Column(length = 500)
	private String alternateTtitle;

	@Column(length = 100)
	private String country;

	private Timestamp dateCreate;

	private Timestamp dateUpdate;

	@Lob
	private String description;

	private Integer duration;

	@Column(length = 500)
	private String imageBanner;

	private Integer rating;

	@Column(length = 100)
	private String releaseDate;

	@Column(length = 6)
	private String season;

	@Column(length = 50)
	private String status;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(length = 500)
	private String titleJp;

	@Column(length = 500)
	private String trailer;

	private Long userId;

	private Integer views;

	private Integer volume;

	private Integer year;

	// bi-directional many-to-many association to Staff
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "videoandstaff", joinColumns = { @JoinColumn(name = "video") }, inverseJoinColumns = {
			@JoinColumn(name = "staff") })
	@Fetch(FetchMode.SUBSELECT)
	private Set<Staff> staffs;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "videoandcategory", joinColumns = { @JoinColumn(name = "video") }, inverseJoinColumns = {
			@JoinColumn(name = "category") })
	@Fetch(FetchMode.SUBSELECT)
	private Set<Category> categores;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "videoandtag", joinColumns = { @JoinColumn(name = "video") }, inverseJoinColumns = {
			@JoinColumn(name = "tag") })
	@Fetch(FetchMode.SUBSELECT)
	private Set<Tag> tags;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "videoandstudio", joinColumns = { @JoinColumn(name = "video") }, inverseJoinColumns = {
			@JoinColumn(name = "studio") })
	@Fetch(FetchMode.SUBSELECT)
	private Set<Studio> studios;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "videoandchar", joinColumns = { @JoinColumn(name = "video") }, inverseJoinColumns = {
			@JoinColumn(name = "char") })
	@Fetch(FetchMode.SUBSELECT)
	private Set<Chars> chars;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "favorites", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	@Fetch(FetchMode.SELECT)
	private Set<User> likedUser;

	public Video() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAlternateTtitle() {
		return alternateTtitle;
	}

	public void setAlternateTtitle(String alternateTtitle) {
		this.alternateTtitle = alternateTtitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Timestamp getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Timestamp dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getImageBanner() {
		return imageBanner;
	}

	public void setImageBanner(String imageBanner) {
		this.imageBanner = imageBanner;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleJp() {
		return titleJp;
	}

	public void setTitleJp(String titleJp) {
		this.titleJp = titleJp;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Set<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}

	public Set<Category> getCategores() {
		return categores;
	}

	public void setCategores(Set<Category> categores) {
		this.categores = categores;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Studio> getStudios() {
		return studios;
	}

	public void setStudios(Set<Studio> studios) {
		this.studios = studios;
	}

	public Set<Chars> getChars() {
		return chars;
	}

	public void setChars(Set<Chars> chars) {
		this.chars = chars;
	}

	public Set<User> getLikedUser() {
		return likedUser;
	}

	public void setLikedUser(Set<User> likedUser) {
		this.likedUser = likedUser;
	}
	
	public static void main(String[] args) {
		String s = "catagory=1314 a&s=af";
		System.out.println(s.replaceAll("\bcatagory\\b=[\\w\\s]+", ""));
	}
}