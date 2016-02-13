package clinic2.activlife.com.myapplication.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.db.PatientTable;
import clinic2.activlife.com.myapplication.helper.DatabaseHelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class HomeActivity extends Fragment {


	// Database Helper
	private DatabaseHelper db;
	private SimpleCursorAdapter dataAdapter;
	 private int ADD_EDIT_PATIENT = 1; 
	 private String PATIENT_ID = "PatientID";
	//UI controls.
	Button addPatientbutton;
	Button backupDatabutton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return   inflater.inflate(R.layout.activity_main_home, container, false);

	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		db = new DatabaseHelper(getActivity().getApplicationContext());

		// Creating tags
		// Don't forget to close database connection
		db.closeDB();

		addHandlers();
	}

	private void addHandlers() {
		addPatientbutton = (Button) getActivity().findViewById(R.id.addPatient);
		 
		addPatientbutton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				addEditPatient();
			}
 
		});
 
		backupDatabutton = (Button) getActivity().findViewById(R.id.BackupDB);
		backupDatabutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				backupDB();
			}
 
		});
	}

	protected void backupDB() {
		exportDB();		
	}

	protected void addEditPatient() {

		launchAddEditPatient(null);
	}

	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	public void onResume() {
		displayListView();
		// TODO Auto-generated method stub
		super.onResume();
	}

	
	
	private void exportDB() {
		File sd = Environment.getExternalStorageDirectory();
      	File data = Environment.getDataDirectory();
       FileChannel source=null;
       
       Log.d("SD path", sd.getName());
       Log.d("date path", data.getName());
       FileChannel destination=null;
       
       Time time = new Time();
       time.setToNow();
       String timeStamp = time.format2445();

       String currentDBPath = "data/"+ "com.activlife.clinic1" +"/databases/"+DatabaseHelper.DATABASE_NAME ;
       String backupDBPath = DatabaseHelper.DATABASE_NAME+ "_"+ timeStamp;
       File currentDB = new File(data, currentDBPath);
       Log.d("Current DB Path", "Current db path opened");
       File[] sdFiles = sd.listFiles();
       Log.d("SD List count", String.valueOf(sdFiles.length));
       for ( int i = 0 ; sdFiles != null && i < sdFiles.length ; i++) {
    	   Log.d("File Name", sdFiles[i].getName());
       }
    	   
       Log.d("Before backup", "Start....");
       
       File backupDB = new File(sd, backupDBPath);
       try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(getActivity(), "DB Exported!", Toast.LENGTH_LONG).show();
        } catch(IOException e) {
        	e.printStackTrace();
        }
	}
	
	private void launchAddEditPatient(String id) {
		Intent myIntent = new Intent(getActivity(), PatientsContainerActivity.class);
		Bundle bundle = new Bundle();
		if(id != null && !id.isEmpty()) {
			bundle.putString(PatientTable.KEY_ID, id);
			myIntent.putExtras(bundle);
		}
		this.startActivityForResult(myIntent, ADD_EDIT_PATIENT );
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == ADD_EDIT_PATIENT) {
	        // Make sure the request was successful
	       // if (resultCode == RESULT_OK) {
	        	dataAdapter.notifyDataSetChanged();
	            // The user picked a contact.
	            // The Intent's data Uri identifies which contact was selected.

	            // Do something with the contact here (bigger example below)
	       // }
	    }
	}
	
	private void displayListView() {
		 
		 db = new DatabaseHelper(getActivity().getApplicationContext());
		 
		  Cursor cursor = PatientTable.getPatients(getActivity());
		 
		  // The desired columns to be bound
		  String[] columns = new String[] {
				  PatientTable.KEY_NAME,
				  PatientTable.KEY_AGE,
				  PatientTable.KEY_PHONE,
				  PatientTable.KEY_ID
		  };
		 
		  // the XML defined views which the data will be bound to
		  int[] to = new int[] { 
		    R.id.name,
		    R.id.age,
		    R.id.phoneNum,
		  };
		 
		  // create the adapter using the cursor pointing to the desired data 
		  //as well as the layout information
		  dataAdapter = new SimpleCursorAdapter(
				  getActivity(), R.layout.patientmainlist,
		    cursor, 
		    columns, 
		    to,
		    0);
		 
		  ListView listView = (ListView) getActivity().findViewById(R.id.listView1);
		  // Assign adapter to ListView
		  listView.setAdapter(dataAdapter);
		 
		 
		  listView.setOnItemClickListener(new OnItemClickListener() {
		   @Override
		   public void onItemClick(AdapterView<?> listView, View view, 
		     int position, long id) {
		   // Get the cursor, positioned to the corresponding row in the result set
		   Cursor cursor = (Cursor) listView.getItemAtPosition(position);
		 
		   // Get the state's capital from this row in the database.
		   String Id = 
		    cursor.getString(cursor.getColumnIndexOrThrow("_id"));
		   launchAddEditPatient(Id);
		   //Toast.makeText(getApplicationContext(),
		    // countryCode, Toast.LENGTH_SHORT).show();
		 
		   }
		  });
		 
		  EditText myFilter = (EditText) getActivity().findViewById(R.id.myFilter);
		  myFilter.addTextChangedListener(new TextWatcher() {
		 
		   public void afterTextChanged(Editable s) {
		   }
		 
		   public void beforeTextChanged(CharSequence s, int start, 
		     int count, int after) {
		   }
		 
		   public void onTextChanged(CharSequence s, int start, 
		     int before, int count) {
		    dataAdapter.getFilter().filter(s.toString());
		   }
		  });
		   
		  dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
		         public Cursor runQuery(CharSequence constraint) {
		             return PatientTable.fetchPatientsByName(constraint.toString(), getActivity());
		         }
		     });
		 
		 }

}
