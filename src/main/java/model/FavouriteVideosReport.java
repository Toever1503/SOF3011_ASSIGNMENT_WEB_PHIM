package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FavouriteVideosReport implements Serializable {
	@Id
	Serializable group;

	String videoTitle;
	Long favourCount;
	Date oldestDate;
	Date latestDate;
	
	public FavouriteVideosReport(String videoTitle, Long favourCount, Date oldestDate, Date latestDate) {
		super();
		this.videoTitle = videoTitle;
		this.favourCount = favourCount;
		this.oldestDate = oldestDate;
		this.latestDate = latestDate;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public Long getFavourCount() {
		return favourCount;
	}

	public void setFavourCount(Long favourCount) {
		this.favourCount = favourCount;
	}

	public Date getOldestDate() {
		return oldestDate;
	}

	public void setOldestDate(Date oldestDate) {
		this.oldestDate = oldestDate;
	}

	public Date getLatestDate() {
		return latestDate;
	}

	public void setLatestDate(Date latestDate) {
		this.latestDate = latestDate;
	}

	@Override
	public String toString() {
		return "FavouriteVideosReport [videoTitle=" + videoTitle + ", favourCount=" + favourCount + ", oldestDate="
				+ oldestDate + ", latestDate=" + latestDate + "]";
	}

}
