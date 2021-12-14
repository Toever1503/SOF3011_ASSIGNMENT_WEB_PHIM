package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the share database table.
 * 
 */
@Entity
@Table(name = "share")
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 255)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "share_date", nullable = false)
	private Date shareDate;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "video_id", nullable = false)
	private Long videoId;

	public Share() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getShareDate() {
		return this.shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
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
		return "Share [id=" + id + ", email=" + email + ", shareDate=" + shareDate + ", userId=" + userId + ", videoId="
				+ videoId + "]";
	}

}