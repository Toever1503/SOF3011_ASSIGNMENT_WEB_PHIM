package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ShareFriendsReport implements Serializable {

	@Id
	Serializable group;

	String senderName;
	String senderEmail;
	String receiveEmail;
	@Temporal(TemporalType.DATE)
	Date sentDate;

	public ShareFriendsReport(String senderName, String senderEmail, String receiveEmail, Date sentDate) {
		super();
		this.senderName = senderName;
		this.senderEmail = senderEmail;
		this.receiveEmail = receiveEmail;
		this.sentDate = sentDate;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	@Override
	public String toString() {
		return "ShareFriendsReport [senderName=" + senderName + ", senderEmail=" + senderEmail + ", receiveEmail="
				+ receiveEmail + ", sentDate=" + sentDate + "]";
	}

}
