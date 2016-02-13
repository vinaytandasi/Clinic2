package clinic2.activlife.com.myapplication.model;

import java.util.Date;

public class baseModel {

	protected int id;
	protected Date createdTime;
	protected Date lastUpdatedTime;
	
	//Non-persisted field. Used to keep track of the object's state wrt the state in DB.
	public enum ObjectStatus  {
		NEW, 
		SAVED, 
		EDITED,
		DELETED
	};
	
	public ObjectStatus mObjectStatus = ObjectStatus.NEW;

	/**
	 * @return the mObjectStatus
	 */
	public ObjectStatus getObjectStatus() {
		return mObjectStatus;
	}
	/**
	 * @param mObjectStatus the mObjectStatus to set
	 */
	public void setObjectStatus(ObjectStatus mObjectStatus) {
		this.mObjectStatus = mObjectStatus;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the lastUpdatedTime
	 */
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	/**
	 * @param lastUpdatedTime the lastUpdatedTime to set
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}


}
