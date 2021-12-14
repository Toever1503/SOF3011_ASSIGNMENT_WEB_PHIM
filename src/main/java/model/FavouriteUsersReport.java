package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FavouriteUsersReport implements Serializable {
	@Id
	Serializable group;

	String username;
	String fullname;
	String email;
	Date favoDate;

	public FavouriteUsersReport(String username, String fullname, String email, Date favoDate) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.favoDate = favoDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFavoDate() {
		return favoDate;
	}

	public void setFavoDate(Date favoDate) {
		this.favoDate = favoDate;
	}

	@Override
	public String toString() {
		return "FavouriteUsersReport [username=" + username + ", fullname=" + fullname + ", email=" + email
				+ ", favoDate=" + favoDate + "]";
	}

}
