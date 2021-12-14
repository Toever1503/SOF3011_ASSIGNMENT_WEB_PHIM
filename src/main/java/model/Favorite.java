package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the favorites database table.
 * 
 */
@Entity
@Table(name="favorites")
@NamedQuery(name="Favorite.findAll", query="SELECT f FROM Favorite f")
public class Favorite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="like_date", nullable=false)
	private Date likeDate;

	@Column(name="user_id", nullable=false)
	private Long userId;

	@Column(name="video_id", nullable=false)
	private Long videoId;

	public Favorite() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLikeDate() {
		return this.likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVideoId() {
		return this.videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", likeDate=" + likeDate + ", userId=" + userId + ", videoId=" + videoId + "]";
	}
	
	

}