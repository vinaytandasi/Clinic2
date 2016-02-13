package clinic2.activlife.com.myapplication.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientModel extends baseModel{

	private String name;
	private int age;
	private String phone;
	private String emailId;
	private String referencedBy;
	private String notes; 
	//Needs to be a list in a separate table.
	private String paymentDetails;
	private String followupInstructions;

	private String gender;
	private String currentIssue;
	private String nextSession;
	private String diagnosis;
	
	private int painLevel;
	private String profession;
	private int numberOfSittings;
	private String doAndDont;
	private String specialInstruction;
	
	//DB version 2 fields
	private String maritalStatus;
	private String occupationDetails;
	private String chiefComplaints; 
	private String medicalHistory; 
	private String physioRx; 
	private String oralExamination;
	private String habbits;
	
	//this counter is kept to keep track of the sessionId so that all the sessions for a patient have a incrementing number. Useful for handling cases where 
	// session can be deleted in between.
	private int AppointmentDetailsLastSessionId = 0;
	private List<AppointmentDetailsModel> mAppointmentDetailsModelList = new ArrayList<AppointmentDetailsModel>();


	/**
	 * @return the occupationDetails
	 */
	public String getOccupationDetails() {
		return occupationDetails;
	}

	/**
	 * @param occupationDetails the occupationDetails to set
	 */
	public void setOccupationDetails(String occupationDetails) {
		this.occupationDetails = occupationDetails;
	}

	/**
	 * @return the chiefComplaints
	 */
	public String getChiefComplaints() {
		return chiefComplaints;
	}

	/**
	 * @param chiefComplaints the chiefComplaints to set
	 */
	public void setChiefComplaints(String chiefComplaints) {
		this.chiefComplaints = chiefComplaints;
	}

	/**
	 * @return the medicalHistory
	 */
	public String getMedicalHistory() {
		return medicalHistory;
	}

	/**
	 * @param medicalHistory the medicalHistory to set
	 */
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	/**
	 * @return the physioRx
	 */
	public String getPhysioRx() {
		return physioRx;
	}

	/**
	 * @param physioRx the physioRx to set
	 */
	public void setPhysioRx(String physioRx) {
		this.physioRx = physioRx;
	}

	/**
	 * @return the oralExamination
	 */
	public String getOralExamination() {
		return oralExamination;
	}

	/**
	 * @param oralExamination the oralExamination to set
	 */
	public void setOralExamination(String oralExamination) {
		this.oralExamination = oralExamination;
	}

	/**
	 * @return the habbits
	 */
	public String getHabbits() {
		return habbits;
	}

	/**
	 * @param habbits the habbits to set
	 */
	public void setHabbits(String habbits) {
		this.habbits = habbits;
	}
 

	/////////

	/**
	 * @return the painLevel
	 */
	public int getPainLevel() {
		return painLevel;
	}

	/**
	 * @param painLevel the painLevel to set
	 */
	public void setPainLevel(int painLevel) {
		this.painLevel = painLevel;
	}

	/**
	 * @return the numberOfSittings
	 */
	public int getNumberOfSittings() {
		return numberOfSittings;
	}

	/**
	 * @param numberOfSittings the numberOfSittings to set
	 */
	public void setNumberOfSittings(int numberOfSittings) {
		this.numberOfSittings = numberOfSittings;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * @return the doAndDont
	 */
	public String getDoAndDont() {
		return doAndDont;
	}

	/**
	 * @param doAndDont the doAndDont to set
	 */
	public void setDoAndDont(String doAndDont) {
		this.doAndDont = doAndDont;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the specialInstruction
	 */
	public String getSpecialInstruction() {
		return specialInstruction;
	}

	/**
	 * @param specialInstruction the specialInstruction to set
	 */
	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the currentIssue
	 */
	public String getCurrentIssue() {
		return currentIssue;
	}

	/**
	 * @param currentIssue the currentIssue to set
	 */
	public void setCurrentIssue(String currentIssue) {
		this.currentIssue = currentIssue;
	}

	/**
	 * @return the nextSession
	 */
	public String getNextSession() {
		return nextSession;
	}

	/**
	 * @param nextSession the nextSession to set
	 */
	public void setNextSession(String nextSession) {
		this.nextSession = nextSession;
	}
	private int sequenceNumber;
	
	public PatientModel(  )
	{
	
	}
	
	/**
	 * @return the sequenceNumber
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the paymentDetails
	 */
	public String getPaymentDetails() {
		return paymentDetails;
	}


	/**
	 * @param paymentDetails the paymentDetails to set
	 */
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}



	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the referencedFrom
	 */
	public String getReferencedBy() {
		return referencedBy;
	}
	/**
	 * @param referencedFrom the referencedFrom to set
	 */
	public void setReferencedBy(String referenced) {
		this.referencedBy = referenced;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the followupInstructions
	 */
	public String getFollowupInstructions() {
		return followupInstructions;
	}
	/**
	 * @param followupInstructions the followupInstructions to set
	 */
	public void setFollowupInstructions(String followupInstructions) {
		this.followupInstructions = followupInstructions;
	}
	
	
	/**
	 * @return the appointmentDetailsLastSessionId
	 */
	public int getAppointmentDetailsLastSessionId() {
		return AppointmentDetailsLastSessionId;
	}

	/**
	 * @param appointmentDetailsLastSessionId the appointmentDetailsLastSessionId to set
	 */
	public void setAppointmentDetailsLastSessionId(
			int appointmentDetailsLastSessionId) {
		AppointmentDetailsLastSessionId = appointmentDetailsLastSessionId;
	}

	/**
	 * @return the mAppointmentDetailsModel
	 */
	public List<AppointmentDetailsModel> getAppointmentDetailsModel() {
		return mAppointmentDetailsModelList;
	}

	/**
	 * @param mAppointmentDetailsModel the mAppointmentDetailsModel to set
	 */
	public void setmAppointmentDetailsModel(
			List<AppointmentDetailsModel> mAppointmentDetailsModel) {
		this.mAppointmentDetailsModelList = mAppointmentDetailsModel;
	}

}
