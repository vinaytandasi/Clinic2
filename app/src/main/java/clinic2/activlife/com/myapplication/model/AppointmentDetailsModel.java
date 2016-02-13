package clinic2.activlife.com.myapplication.model;

import java.util.Date;

public class AppointmentDetailsModel extends baseModel {

	private int patientId;

	// to be used later to support different treatment sessions for same patient.
	private int sessionGroupId;
	private String groupSessionStatus;
	// to be used later to support different clinic locations.
	private int locationId;

	private int sessionId; //running number of sessions.
	private String sessionStatus; // Scheduled, Treatment Done, Payment Received.
	public Date sessionStartTime;
	public  Date sessionEndTime;
	private Double sessionAmount;

	private Double sessionPendingAmount;
	private String sessionTreatment; // What is the treatment ? Eg. SWT for back .
	private String sessionNotes;
	private String reminderSent;
	
	
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the sessionGroupId
	 */
	public int getSessionGroupId() {
		return sessionGroupId;
	}
	/**
	 * @param sessionGroupId the sessionGroupId to set
	 */
	public void setSessionGroupId(int sessionGroupId) {
		this.sessionGroupId = sessionGroupId;
	}
	/**
	 * @return the groupSessionStatus
	 */
	public String getGroupSessionStatus() {
		return groupSessionStatus;
	}
	/**
	 * @param groupSessionStatus the groupSessionStatus to set
	 */
	public void setGroupSessionStatus(String groupSessionStatus) {
		this.groupSessionStatus = groupSessionStatus;
	}
	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	/**
	 * @return the sessionId
	 */
	public int getSessionId() {
		return sessionId;
	}
	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * @return the sessionStatus
	 */
	public String getSessionStatus() {
		return sessionStatus;
	}
	/**
	 * @param sessionStatus the sessionStatus to set
	 */
	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	/**
	 * @return the sessionStartTime
	 */
	public Date getSessionStartTime() {
		return sessionStartTime;
	}
	/**
	 * @param sessionStartTime the sessionStartTime to set
	 */
	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}
	/**
	 * @return the sessionEndTime
	 */
	public Date getSessionEndTime() {
		return sessionEndTime;
	}
	/**
	 * @param sessionEndTime the sessionEndTime to set
	 */
	public void setSessionEndTime(Date sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}
	/**
	 * @return the sessionAmount
	 */
	public Double getSessionAmount() {
		return sessionAmount;
	}
	/**
	 * @param sessionAmount the sessionAmount to set
	 */
	public void setSessionAmount(Double sessionAmount) {
		this.sessionAmount = sessionAmount;
	}
	/**
	 * @return the sessionTreatment
	 */
	public String getSessionTreatment() {
		return sessionTreatment;
	}
	/**
	 * @param sessionTreatment the sessionTreatment to set
	 */
	public void setSessionTreatment(String sessionTreatment) {
		this.sessionTreatment = sessionTreatment;
	}
	/**
	 * @return the sessionNotes
	 */
	public String getSessionNotes() {
		return sessionNotes;
	}
	/**
	 * @param sessionNotes the sessionNotes to set
	 */
	public void setSessionNotes(String sessionNotes) {
		this.sessionNotes = sessionNotes;
	}
	/**
	 * @return the reminderSent
	 */
	public String getReminderSent() {
		return reminderSent;
	}
	/**
	 * @param reminderSent the reminderSent to set
	 */
	public void setReminderSent(String reminderSent) {
		this.reminderSent = reminderSent;
	}

	public Double getSessionPendingAmount() {
		return sessionPendingAmount;
	}

	public void setSessionPendingAmount(Double sessionPendingAmount) {
		this.sessionPendingAmount = sessionPendingAmount;
	}



}
