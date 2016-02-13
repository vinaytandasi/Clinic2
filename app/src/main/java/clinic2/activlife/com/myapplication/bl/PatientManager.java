package clinic2.activlife.com.myapplication.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import clinic2.activlife.com.myapplication.Utils.DateTimeUtils;
import clinic2.activlife.com.myapplication.bl.BlInterfaces.IClinicBaseManager;
import clinic2.activlife.com.myapplication.db.AppointmentDetailsTable;
import clinic2.activlife.com.myapplication.db.PatientTable;
import clinic2.activlife.com.myapplication.db.baseDB;
import clinic2.activlife.com.myapplication.model.AppointmentDetailsModel;
import clinic2.activlife.com.myapplication.model.PatientModel;
import clinic2.activlife.com.myapplication.model.baseModel.ObjectStatus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class PatientManager implements IClinicBaseManager {
	
	PatientModel mPatient = new PatientModel();
	/**
	 * @return the mPatient
	 */
	public PatientModel getPatient() {
		return mPatient;
	}

	public void savePatient(Context context) throws Exception {
		//moved to the patienttable layer to support nested transactions.
		
/*		ContentValues values = new ContentValues(50);
		if(mPatient.getId() != 0 ) {
			values.put(PatientTable.KEY_ID, mPatient.getId());		
		}
		values.put(PatientTable.KEY_NAME, mPatient.getName());
		values.put(PatientTable.KEY_AGE, mPatient.getAge());
		values.put(PatientTable.KEY_NOTES, mPatient.getNotes());
		values.put(PatientTable.KEY_GENDER, mPatient.getGender());
		values.put(PatientTable.KEY_PHONE, mPatient.getPhone() );
		values.put(PatientTable.KEY_PAYMENT_DETAILS, mPatient.getPaymentDetails());
		values.put(PatientTable.KEY_FOLLOWUP_INSTRUCTIONS, mPatient.getFollowupInstructions());

		values.put(PatientTable.KEY_REFERRED_BY, mPatient.getReferencedBy());
		values.put(PatientTable.KEY_PROFESSION, mPatient.getProfession());
		values.put(PatientTable.KEY_DIAGNOSIS, mPatient.getDiagnosis());
		values.put(PatientTable.KEY_PAIN_LEVEL, mPatient.getPainLevel());
		values.put(PatientTable.KEY_NUMBER_OF_SITTINGS, mPatient.getNumberOfSittings());
		values.put(PatientTable.KEY_DO_DONT, mPatient.getDoAndDont());
		values.put(PatientTable.KEY_SPECIAL_INSTRUCTIONS, mPatient.getSpecialInstruction());
				
		//DB version 2
		values.put(PatientTable.KEY_MARITAL_STATUS, mPatient.getMaritalStatus());
		values.put(PatientTable.KEY_OCCUPATION_DETAILS, mPatient.getOccupationDetails());
		values.put(PatientTable.KEY_CHIEF_COMPLAINTS, mPatient.getChiefComplaints());
		values.put(PatientTable.KEY_MEDICAL_HISTORY, mPatient.getMedicalHistory());
		values.put(PatientTable.KEY_PHYSIO_RX, mPatient.getPhysioRx());
		values.put(PatientTable.KEY_ORAL_EXAMINATION, mPatient.getOralExamination());
		values.put(PatientTable.KEY_HABBITS, mPatient.getHabbits());

		PatientTable.insertPatient(context, values);*/
		
		PatientTable.insertPatient(context, mPatient, null);
	}
	
	public PatientModel readPatientById(Context context, String id) {
		
		Cursor cur = PatientTable.fetchPatientById(id, context);
		if(cur != null && cur.moveToFirst()) {
			 mPatient = new PatientModel();
			
			mPatient.setId(cur.getInt(cur.getColumnIndex(baseDB.KEY_ID)));
			mPatient.setName(cur.getString(cur.getColumnIndex(PatientTable.KEY_NAME)));
			mPatient.setAge(cur.getInt(cur.getColumnIndex(PatientTable.KEY_AGE)));
			mPatient.setGender(cur.getString(cur.getColumnIndex(PatientTable.KEY_GENDER)));
			mPatient.setPhone(cur.getString(cur.getColumnIndex(PatientTable.KEY_PHONE)));
			mPatient.setNotes(cur.getString(cur.getColumnIndex(PatientTable.KEY_NOTES)));
			mPatient.setPaymentDetails(cur.getString(cur.getColumnIndex(PatientTable.KEY_PAYMENT_DETAILS)));
			mPatient.setFollowupInstructions(cur.getString(cur.getColumnIndex(PatientTable.KEY_FOLLOWUP_INSTRUCTIONS)));

			mPatient.setReferencedBy(cur.getString(cur.getColumnIndex(PatientTable.KEY_REFERRED_BY)));
			mPatient.setProfession(cur.getString(cur.getColumnIndex(PatientTable.KEY_PROFESSION)));
			mPatient.setDiagnosis(cur.getString(cur.getColumnIndex(PatientTable.KEY_DIAGNOSIS)));
			mPatient.setPainLevel(cur.getInt(cur.getColumnIndex(PatientTable.KEY_PAIN_LEVEL)));
			mPatient.setNumberOfSittings(cur.getInt(cur.getColumnIndex(PatientTable.KEY_NUMBER_OF_SITTINGS)));
			mPatient.setDoAndDont(cur.getString(cur.getColumnIndex(PatientTable.KEY_DO_DONT)));
			mPatient.setSpecialInstruction(cur.getString(cur.getColumnIndex(PatientTable.KEY_SPECIAL_INSTRUCTIONS)));
			
			//DB version 2
			mPatient.setMaritalStatus(cur.getString(cur.getColumnIndex(PatientTable.KEY_MARITAL_STATUS)));
			mPatient.setOccupationDetails(cur.getString(cur.getColumnIndex(PatientTable.KEY_OCCUPATION_DETAILS)));
			mPatient.setChiefComplaints(cur.getString(cur.getColumnIndex(PatientTable.KEY_CHIEF_COMPLAINTS)));
			mPatient.setMedicalHistory(cur.getString(cur.getColumnIndex(PatientTable.KEY_MEDICAL_HISTORY)));
			mPatient.setPhysioRx(cur.getString(cur.getColumnIndex(PatientTable.KEY_PHYSIO_RX)));
			mPatient.setOralExamination(cur.getString(cur.getColumnIndex(PatientTable.KEY_ORAL_EXAMINATION)));
			mPatient.setHabbits(cur.getString(cur.getColumnIndex(PatientTable.KEY_HABBITS)));

			cur.close();
			
			loadAppointmentDetails(context,id);
			
		}
		
		
		return mPatient;
	}

	private void loadAppointmentDetails(Context context, String id) {
		
		Cursor cur = AppointmentDetailsTable.fetchAppointmentsByPatientId(null, id, context);
		if(cur != null ) {
			List<AppointmentDetailsModel> appointments = mPatient.getAppointmentDetailsModel();
			SimpleDateFormat simpleDateFormat = DateTimeUtils.getSimpleDateFormat();
			Date date = null;

			while ( cur.moveToNext()) {			
				AppointmentDetailsModel appointment = new AppointmentDetailsModel();

				appointment.setId(cur.getInt(cur.getColumnIndex(baseDB.KEY_ID)));
				
				appointment.setObjectStatus(ObjectStatus.SAVED); // as this object is already in DB, set the status as saved.
				
				//keep track of the lastSessionId;
				int sessionId = cur.getInt(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_ID));
				appointment.setSessionId(sessionId);
				if(sessionId > mPatient.getAppointmentDetailsLastSessionId()) {
					mPatient.setAppointmentDetailsLastSessionId(sessionId);
				}
				
				appointment.setPatientId(cur.getInt(cur.getColumnIndex(AppointmentDetailsTable.KEY_PATIENT_ID)));
				appointment.setSessionStatus(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_STATUS)));
				
				try {
					date = simpleDateFormat.parse(cur.getString(cur.getColumnIndex(baseDB.KEY_CREATED_TIME)));
					appointment.setCreatedTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					date = simpleDateFormat.parse(cur.getString(cur.getColumnIndex(baseDB.KEY_LAST_UPDATED_TIME)));
					appointment.setLastUpdatedTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					date = simpleDateFormat.parse(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_START_TIME)));
					appointment.setSessionStartTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					date = simpleDateFormat.parse(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_END_TIME)));
					appointment.setSessionEndTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				appointment.setSessionAmount(cur.getDouble(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_AMOUNT)));
				appointment.setSessionAmount(cur.getDouble(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_PENDING_AMOUNT)));
				appointment.setSessionTreatment(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_TREATMENT)));
				appointment.setSessionNotes(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_SESSION_NOTES)));
				appointment.setReminderSent(cur.getString(cur.getColumnIndex(AppointmentDetailsTable.KEY_REMINDER_SENT)));
				
				appointments.add(appointment);
				
			}
			cur.close();
		}
		
	}
	
}
