package clinic2.activlife.com.myapplication.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.bl.BlInterfaces.IClinicBaseManager;
import clinic2.activlife.com.myapplication.bl.PatientManager;
import clinic2.activlife.com.myapplication.model.PatientModel;
import clinic2.activlife.com.myapplication.ui.UIInterfaces.IClinicBaseFragment;

public class AppointmentAddEdit extends Fragment implements IClinicBaseFragment {

	private ScrollView rootLayout;
	
	//UI controls.
	Button mSaveButton;

	private PatientManager mPatientManager = null;
	/* (non-Javadoc)
	 * 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootLayout = (ScrollView) inflater.inflate(R.layout.appointment_addedit, container, false);
		
		return rootLayout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    super.onViewCreated(view, savedInstanceState);
	    
/*
		addBindings(view);
		addHandlers(view);
		setupDiagnosisMultiAutoComplete();

	    	loadValuesFromManager();
*/

	}
	
	//Get the current values from DB for patient with id.
	private void loadValuesFromManager() {
//		PatientManager mgr = new PatientManager();
		PatientModel patient = mPatientManager.getPatient();
		if(patient != null && patient.getId() != 0 ) {
			

		}
	}
	
	

	
	private void addBindings(View view) {

/*
		mFollowUp = (EditText)view.findViewById(R.id.followUp);
		mReferredBy = (Spinner)view.findViewById(R.id.spinnerReferredBy);
		mProfession = (Spinner)view.findViewById(R.id.spinnerProfession);
		mDiagnosis = (EditText)view.findViewById(R.id.multiAutoCompleteTextViewDiagnosis);
		mPainLevelLabel = (TextView)view.findViewById(R.id.labelPain);
		mPainLevelSeekBar = (SeekBar) view.findViewById(R.id.seekBarPain);
		mNumbeOfsittings = (EditText)view.findViewById(R.id.numberOfSittings);
		mDoDont = (EditText)view.findViewById(R.id.doAndDont);
		mSpecialInstructions = (EditText)view.findViewById(R.id.specialInstruction);		
*/


	}

	private void addHandlers(View view) {
/*
		mSaveButton = (Button) getView().findViewById(R.id.savePatient);
		 
		mSaveButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				savePatient();
			}
		});
 
		mPainLevelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
	        //Update textview value
	        	mPainLevel = progress;
	        	mPainLevelLabel.setText( PAIN_LEVEL_LABEL + String.valueOf(progress));
	        }
	            public void onStartTrackingTouch(SeekBar arg0) {
	            // TODO Auto-generated method stub
	            }
	            public void onStopTrackingTouch(SeekBar seekBar) {
	            // TODO Auto-generated method stub
	            }
	        });
	       
*/
	}

	protected void savePatient() {
		
/*
		Log.i("PatientAddEdit", "Save clicked");
		PatientModel patient = mPatientManager.getPatient();
		
		String age = mAge.getText().toString();
		patient.setName(mName.getText().toString());
		if(age != null && !age.isEmpty()) {
			patient.setAge(Integer.parseInt(age));
		}
		patient.setDiagnosis( mFollowUp.getText().toString());
	try {
			mPatientManager.savePatient(getActivity());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			Log.e("PatientAddEdit", ex.toString());
		}
		getActivity().finish();
*/

	}

	@Override
	public void setManager(IClinicBaseManager mgr) {
		mPatientManager = (PatientManager) mgr;
		
	}
}
