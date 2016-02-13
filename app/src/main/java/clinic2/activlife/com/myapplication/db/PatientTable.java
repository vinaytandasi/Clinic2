package clinic2.activlife.com.myapplication.db;

import clinic2.activlife.com.myapplication.Utils.DBUtils;
import clinic2.activlife.com.myapplication.helper.DatabaseHelper;
import clinic2.activlife.com.myapplication.model.PatientModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PatientTable extends baseDB{
	
	public static String TABLE_TAG = "patient";

	public static String KEY_NAME = "name";
	public static String KEY_AGE = "age";
	public static String KEY_PHONE = "phone";
	public static String KEY_EMAILID = "email";
	public static String KEY_NOTES = "notes";
	public static String KEY_PAYMENT_DETAILS = "paymentDetails";
	public static String KEY_FOLLOWUP_INSTRUCTIONS = "followupInstructions";
	public static String KEY_SEQUENCE_NUM = "sequenceNumber";
	public static String KEY_GENDER = "gender";
	public static String KEY_CURRENT_ISSUE = "currentIssue";
	public static String KEY_NEXT_SESSION = "nextSession";
	
	public static String KEY_REFERRED_BY = "referredBy";
	public static String KEY_PROFESSION = "profession";
	public static String KEY_DIAGNOSIS = "diagnosis";
	public static String KEY_PAIN_LEVEL = "painLevel";
	public static String KEY_NUMBER_OF_SITTINGS = "numberOfSittings";
	public static String KEY_DO_DONT = "doDont";
	public static String KEY_SPECIAL_INSTRUCTIONS = "specialInstructions";

	//DB version 2
	public static String KEY_MARITAL_STATUS = "maritalStatus";
	public static String KEY_OCCUPATION_DETAILS = "occupationDetails";
	public static String KEY_CHIEF_COMPLAINTS = "chiefComplaints";
	public static String KEY_MEDICAL_HISTORY = "medicalHistory";
	public static String KEY_PHYSIO_RX = "physioRx";
	public static String KEY_ORAL_EXAMINATION = "oralExamination";
	public static String KEY_HABBITS = "habbits";

	// to be used later to support different clinic locations.
	public static String KEY_LOCATION_ID = "locationId";

	
	private static final String CREATE_TABLE_PATIENT = "CREATE TABLE "
			+ TABLE_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY," + 
			KEY_NAME  + " TEXT," + 
			KEY_AGE + " INTEGER," + 
			KEY_PHONE + " TEXT," + 
			KEY_EMAILID + " TEXT," + 
			KEY_GENDER + " TEXT," + 
			KEY_CURRENT_ISSUE + " TEXT," + 
			KEY_NEXT_SESSION + " TEXT," + 
			KEY_NOTES + " TEXT," + 
			KEY_PAYMENT_DETAILS + " TEXT," + 
			KEY_FOLLOWUP_INSTRUCTIONS + " TEXT," + 
			KEY_PAIN_LEVEL + " INTEGER," + 
			KEY_REFERRED_BY + " TEXT," + 
			KEY_PROFESSION + " TEXT," + 
			KEY_DIAGNOSIS + " TEXT," + 
			KEY_NUMBER_OF_SITTINGS + " INTEGER," + 
			KEY_DO_DONT + " TEXT," + 
			KEY_SPECIAL_INSTRUCTIONS + " TEXT," + 

			KEY_MARITAL_STATUS + " TEXT," + 
			KEY_OCCUPATION_DETAILS + " TEXT," + 
			KEY_CHIEF_COMPLAINTS + " TEXT," + 
			KEY_MEDICAL_HISTORY + " TEXT," + 
			KEY_PHYSIO_RX + " TEXT," + 
			KEY_ORAL_EXAMINATION + " TEXT," + 
			KEY_HABBITS + " TEXT," + 
			
			KEY_CREATED_TIME  + " DATETIME," +
			KEY_LAST_UPDATED_TIME  + " DATETIME" + ")";
	
	public static void createTable(Context context, SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PATIENT);
	}
	
	public static void upgradeTable(Context context, SQLiteDatabase db, int oldVersion, int newVersion )  {
		if(oldVersion < 2) {
			String alterCommand = "ALTER TABLE " + TABLE_TAG ;
			String dbCommand;
			dbCommand = alterCommand + ADD_COLUMN + KEY_MARITAL_STATUS + " TEXT" + ";"; 
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_OCCUPATION_DETAILS + " TEXT" + ";"; 
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_CHIEF_COMPLAINTS + " TEXT" + ";"; 
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_MEDICAL_HISTORY + " TEXT" + ";"; 
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_PHYSIO_RX + " TEXT" + ";";
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_ORAL_EXAMINATION + " TEXT" + ";"; 
			db.execSQL(dbCommand);
			dbCommand = alterCommand + ADD_COLUMN + KEY_HABBITS + " TEXT" + ";";
			db.execSQL(dbCommand);
		}

	}

	public static void insertPatient(Context context, PatientModel patient , SQLiteDatabase outerDBSession) throws Exception {

		SQLiteDatabase db = null;
		try {
			if( outerDBSession == null) {
			DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
			
			 db = dbHelper.getWritableDatabase();
			 db.beginTransaction();
			}
			else {
				db = outerDBSession;
			}

		ContentValues values = new ContentValues(50);
		if(patient.getId() != 0 ) {
			values.put(PatientTable.KEY_ID, patient.getId());		
		}
		values.put(PatientTable.KEY_NAME, patient.getName());
		values.put(PatientTable.KEY_AGE, patient.getAge());
		values.put(PatientTable.KEY_NOTES, patient.getNotes());
		values.put(PatientTable.KEY_GENDER, patient.getGender());
		values.put(PatientTable.KEY_PHONE, patient.getPhone() );
		values.put(PatientTable.KEY_PAYMENT_DETAILS, patient.getPaymentDetails());
		values.put(PatientTable.KEY_FOLLOWUP_INSTRUCTIONS, patient.getFollowupInstructions());

		values.put(PatientTable.KEY_REFERRED_BY, patient.getReferencedBy());
		values.put(PatientTable.KEY_PROFESSION, patient.getProfession());
		values.put(PatientTable.KEY_DIAGNOSIS, patient.getDiagnosis());
		values.put(PatientTable.KEY_PAIN_LEVEL, patient.getPainLevel());
		values.put(PatientTable.KEY_NUMBER_OF_SITTINGS, patient.getNumberOfSittings());
		values.put(PatientTable.KEY_DO_DONT, patient.getDoAndDont());
		values.put(PatientTable.KEY_SPECIAL_INSTRUCTIONS, patient.getSpecialInstruction());
				
		//DB version 2
		values.put(PatientTable.KEY_MARITAL_STATUS, patient.getMaritalStatus());
		values.put(PatientTable.KEY_OCCUPATION_DETAILS, patient.getOccupationDetails());
		values.put(PatientTable.KEY_CHIEF_COMPLAINTS, patient.getChiefComplaints());
		values.put(PatientTable.KEY_MEDICAL_HISTORY, patient.getMedicalHistory());
		values.put(PatientTable.KEY_PHYSIO_RX, patient.getPhysioRx());
		values.put(PatientTable.KEY_ORAL_EXAMINATION, patient.getOralExamination());
		values.put(PatientTable.KEY_HABBITS, patient.getHabbits());
		
		String createTime = DBUtils.getDateTime();
		values.put(KEY_CREATED_TIME,createTime );

		//If the id is present then it means the row is being edited. 
		String id = null;
		if( patient.getId() != 0 ) {
			id = String.valueOf(patient.getId());
		}
		if(id == null || id.isEmpty()) {
			// insert row
			values.put(KEY_CREATED_TIME,createTime );
			values.put(KEY_LAST_UPDATED_TIME, createTime);
			db.insert(TABLE_TAG, null, values);
		}
		else {
			values.put(KEY_LAST_UPDATED_TIME, createTime);
			db.update(TABLE_TAG,  values, "_id = ?", new String[] {id} );			
		}

		//todo : save the appointment details 
		
		
		//Commit and close the connection only if it was connected here. If not let the outer layer manage the DB session.
		if(outerDBSession == null) {
			db.setTransactionSuccessful();
		}
		
		}
		catch(Exception ex) {
			Log.e("PatientTable", ex.toString());
			throw ex;
		}
		finally {
			if(outerDBSession == null && db != null) {
				db.endTransaction();
				db.close();
			}
		}
		
	} 	
	
	
	public static void insertPatient(Context context, ContentValues values) throws Exception {
		try {
		DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		String createTime = DBUtils.getDateTime();
		values.put(KEY_CREATED_TIME,createTime );

		//If the id is present then it means the row is being edited. 
		String id = null;
		Integer obj = (Integer)values.get(KEY_ID);
		if( obj != null && obj > 0) {
			id = values.get(KEY_ID).toString();
		}
		if(id == null || id.isEmpty()) {
			// insert row
			values.put(KEY_CREATED_TIME,createTime );
			values.put(KEY_LAST_UPDATED_TIME, createTime);
			db.insert(TABLE_TAG, null, values);
		}
		else {
			values.put(KEY_LAST_UPDATED_TIME, createTime);
			db.update(TABLE_TAG,  values, "_id = ?", new String[] {id} );			
		}
		
		db.close();
		
		}
		catch(Exception ex) {
			Log.e("PatientTable", ex.toString());
			throw ex;
		}
	}
	
	public static Cursor getPatients(Context context) {
		DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		return db.query(TABLE_TAG, null,
			     null, null, null, null, KEY_LAST_UPDATED_TIME);
		
	}


	public static Cursor fetchPatientsByName(String patientName, Context context) {
		DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		String filter = "name LIKE '%"+ patientName +"%'";
		return db.query(TABLE_TAG, null,
				filter, null, null, null, KEY_LAST_UPDATED_TIME);
	}

	public static Cursor fetchPatientById(String id, Context context) {
		DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		return db.query(TABLE_TAG, null,
				KEY_ID + " = ?", new String[] {id}, null, null, null);
	}

}
