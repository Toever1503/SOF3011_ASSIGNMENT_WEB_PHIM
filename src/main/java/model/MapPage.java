package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MapPage implements Serializable {

	@Id
	Serializable id;
	Long keyID;
	Long page;

	public MapPage() {
		// TODO Auto-generated constructor stub
	}

	public MapPage(Long keyID, Long page) {
		super();
		this.keyID = keyID;
		this.page = page;
	}

	public Long getKeyID() {
		return keyID;
	}

	public void setKeyID(Long keyID) {
		this.keyID = keyID;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "MapPage [keyID=" + keyID + ", page=" + page + "]";
	}

}
