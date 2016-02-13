package clinic2.activlife.com.myapplication.db;

import clinic2.activlife.com.myapplication.helper.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AppointmentDetailsTable extends baseDB {

	//DB version 2
	public static String TABLE_TAG = "appointmentDetails";
	public static String KEY_PATIENT_ID = "patientId";
	
	// to be used later to support different treatment sessions for same patient.
	public static String KEY_SESSION_GROUP_ID = "sessionGroupId"; 
	public static String KEY_SESSION_GROUP_STATUS = "groupSessionStatus";

	// to be used later to support different clinic locations.
	public static String KEY_LOCATION_ID = "locationId";

	public static String KEY_SESSION_ID = "sessionId"; //running number of sessions.
	public static String KEY_SESSION_STATUS = "sessionStatus"; // Scheduled, Treatment Done, Payment Received.
	public static String KEY_SESSION_START_TIME = "sessionStartTime";
	public static String KEY_SESSION_END_TIME = "sessionEndTime";
	public static String KEY_SESSION_AMOUNT = "sessionAmount";
	public static String KEY_SESSION_PENDING_AMOUNT = "sessionPendingAmount";

	public static String KEY_SESSION_TREATMENT = "sessionTreatment"; // What is the treatment ? Eg. SWT for back .
	public static String KEY_SESSION_NOTES = "sessionNotes";
	public static String KEY_REMINDER_SENT = "reminderSent";

	
	// Create the new table.
	private static final String CREATE_TABLE_APPOINTMENT_DETAILS = "CREATE TABLE "
			+ TABLE_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY," + 
			KEY_CREATED_TIME  + " DATETIME," +
			KEY_LAST_UPDATED_TIME  + " DATETIME, " +
			
			KEY_PATIENT_ID  + " INTEGER," + 
			
			/////////// to be used later.
			KEY_SESSION_GROUP_ID  + " INTEGER," + 
			KEY_SESSION_GROUP_STATUS + " TEXT," + 
			KEY_LOCATION_ID  + " INTEGER," + 
			//////////

			KEY_SESSION_ID  + " INTEGER," + 
			KEY_SESSION_STATUS + " TEXT," + 
			KEY_SESSION_START_TIME + " TEXT," + 
			KEY_SESSION_END_TIME + " TEXT," + 
			KEY_SESSION_AMOUNT  + " REAL," +
			KEY_SESSION_PENDING_AMOUNT  + " REAL," +   // to be used later.
			KEY_SESSION_TREATMENT + " TEXT," +
			KEY_SESSION_NOTES + " TEXT," + 
			KEY_REMINDER_SENT + " TEXT" +  
			
			" )";
	
	//Create the table in a new DB.
	public static void createTable(Context context, SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_APPOINTMENT_DETAILS);
	}


	public static void upgradeTable(Context context, SQLiteDatabase db, int oldVersion, int newVersion ) throws Exception  {
		
		if(oldVersion > 2) { // check this condition and update when the schema version changes.
			throw new Exception("upgrade script for AppointmentDetails missing");
		}
		else {// The table gets created in DB version = 2.
			createTable(context, db);
		}
	}
	
	public static Cursor fetchAppointmentsByPatientId(String[] selectColumns, String patientId, Context context) {
		DatabaseHelper dbHelper = new DatabaseHelper(context.getApplicationContext());
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();

		return db.query(TABLE_TAG, selectColumns,
				KEY_PATIENT_ID + " = ?", new String[] {patientId}, null, null, null);
	}	
}
