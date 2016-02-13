package clinic2.activlife.com.myapplication.ui;

import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.bl.PatientManager;
import clinic2.activlife.com.myapplication.bl.BlInterfaces.IClinicBaseManager;
import clinic2.activlife.com.myapplication.model.PatientModel;
import clinic2.activlife.com.myapplication.ui.UIInterfaces.IClinicBaseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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

public class PatientAddEdit extends Fragment implements IClinicBaseFragment {

	private ScrollView rootLayout;
	
	//UI controls.
	Button mSaveButton;
	private int mPainLevel = 0;
	
	String PAIN_LEVEL_LABEL = "Pain Level: ";
	
	private PatientManager mPatientManager = null;
	/* (non-Javadoc)
	 * 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootLayout = (ScrollView) inflater.inflate(R.layout.patientaddedit, container, false);
		
		return rootLayout;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    super.onViewCreated(view, savedInstanceState);
	    
		addBindings(view);
		addHandlers(view);
		setupDiagnosisMultiAutoComplete();

	    	loadValuesFromManager();
	    
	}
	
	//Get the current values from DB for patient with id.
	private void loadValuesFromManager() {
//		PatientManager mgr = new PatientManager();
		PatientModel patient = mPatientManager.getPatient();
		if(patient != null && patient.getId() != 0 ) {
			
			mName.setText(patient.getName());
			mAge.setText(String.valueOf(patient.getAge()));
			mPhone.setText(patient.getPhone());
			mNotes.setText(patient.getNotes());
			mPaymentDetails.setText(patient.getPaymentDetails());
			mFollowUp.setText(patient.getFollowupInstructions());

			if( patient.getReferencedBy() != null && patient.getReferencedBy().length() > 0 ) {
				@SuppressWarnings("unchecked")
				ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)mReferredBy.getAdapter();
				mReferredBy.setSelection(array_spinner.getPosition(patient.getReferencedBy()));
			}

			if( patient.getProfession().length() > 0 ) {
				@SuppressWarnings("unchecked")
				ArrayAdapter<String> array_spinner=(ArrayAdapter<String>)mProfession.getAdapter();
				mProfession.setSelection(array_spinner.getPosition(patient.getProfession()));
			}

			mDiagnosis.setText(patient.getDiagnosis());
			//mPainLevelLabel.setText(patient.getPainLevel());
			mPainLevel = patient.getPainLevel();
			mPainLevelSeekBar.setProgress(mPainLevel);
			mNumbeOfsittings.setText(String.valueOf(patient.getNumberOfSittings()));
			mDoDont.setText(patient.getDoAndDont());
			mSpecialInstructions.setText(patient.getSpecialInstruction());

			if (TextUtils.isEmpty(patient.getGender())) {
				mGender.clearCheck();
			}
			else if (patient.getGender().compareTo(getString(R.string.female_short)) == 0) {
				mGender.check(R.id.radioFemale);
			}
			 else {
					mGender.check(R.id.radioMale);
			}
			//DB version 2
			//private RadioGroup mMaritalStatus;

			if (TextUtils.isEmpty(patient.getMaritalStatus())) {
				mMaritalStatus.clearCheck();
			}
			else if (patient.getMaritalStatus().compareTo(getString(R.string.married_short)) == 0) {
				mMaritalStatus.check(R.id.radioMarried);
			}
			else {
				mMaritalStatus.check(R.id.radioNotMarried);
			}

			mOccupationDetails.setText(patient.getOccupationDetails());
			mChiefComplaints.setText(patient.getChiefComplaints());
			mMedicalHistory.setText(patient.getMedicalHistory());
			mPhysioRx.setText(patient.getPhysioRx());
			mOralExamination.setText(patient.getOralExamination());
			mHabbits.setText(patient.getHabbits());
		}
	}
	
	
	private EditText mName;
	private EditText mAge;
	private EditText mPhone;
	private EditText mNotes;
	private EditText mPaymentDetails;
	private EditText mFollowUp;
	
	private Spinner mReferredBy;
	private Spinner mProfession;
	private EditText mDiagnosis;
	private TextView mPainLevelLabel;
	private SeekBar mPainLevelSeekBar;
	private EditText mNumbeOfsittings;
	private EditText mDoDont;
	private EditText mSpecialInstructions;
	
	private RadioGroup mGender;
	private RadioGroup mMaritalStatus;
	private EditText mOccupationDetails;
	private EditText mChiefComplaints;
	private EditText mMedicalHistory;
	private EditText mPhysioRx;
	private EditText mOralExamination;
	private EditText mHabbits;
	
	
	private void addBindings(View view) {
		mName = (EditText)view.findViewById(R.id.patientName);
		mAge = (EditText)view.findViewById(R.id.patientAge);
		mPhone = (EditText)view.findViewById(R.id.phone);
		mNotes = (EditText)view.findViewById(R.id.notes);
		mPaymentDetails = (EditText)view.findViewById(R.id.payments);
		
		mFollowUp = (EditText)view.findViewById(R.id.followUp);
		mReferredBy = (Spinner)view.findViewById(R.id.spinnerReferredBy);
		mProfession = (Spinner)view.findViewById(R.id.spinnerProfession);
		mDiagnosis = (EditText)view.findViewById(R.id.multiAutoCompleteTextViewDiagnosis);
		mPainLevelLabel = (TextView)view.findViewById(R.id.labelPain);
		mPainLevelSeekBar = (SeekBar) view.findViewById(R.id.seekBarPain);
		mNumbeOfsittings = (EditText)view.findViewById(R.id.numberOfSittings);
		mDoDont = (EditText)view.findViewById(R.id.doAndDont);
		mSpecialInstructions = (EditText)view.findViewById(R.id.specialInstruction);		
		
		mGender = (RadioGroup)view.findViewById(R.id.radioSex);		
		mMaritalStatus = (RadioGroup)view.findViewById(R.id.radioMaritalStatus);		
		mOccupationDetails = (EditText)view.findViewById(R.id.editOccupationDetails);		
		mChiefComplaints = (EditText)view.findViewById(R.id.editChiefComplaint);		
		mMedicalHistory = (EditText)view.findViewById(R.id.editMedicalHistory);		
		mPhysioRx = (EditText)view.findViewById(R.id.editPhysioRx);		
		mOralExamination = (EditText)view.findViewById(R.id.editOralExamination);		
		mHabbits = (EditText)view.findViewById(R.id.editHabbits);		
		
		
	}

	private void addHandlers(View view) {
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
	       
	}

	protected void savePatient() {
		
		Log.i("PatientAddEdit", "Save clicked");
		PatientModel patient = mPatientManager.getPatient();
		
		String age = mAge.getText().toString();
		patient.setName(mName.getText().toString());
		if(age != null && !age.isEmpty()) {
			patient.setAge(Integer.parseInt(age));
		}
		patient.setDiagnosis( mFollowUp.getText().toString());
		patient.setNotes(mNotes.getText().toString());
		patient.setPhone(mPhone.getText().toString() );
		patient.setPaymentDetails( mPaymentDetails.getText().toString());
		patient.setReferencedBy( mReferredBy.getSelectedItem().toString());
		patient.setProfession( mProfession.getSelectedItem().toString());
		patient.setDiagnosis( mDiagnosis.getText().toString());
		patient.setFollowupInstructions( mFollowUp.getText().toString());
		patient.setPainLevel( mPainLevel);
		
		
		String sitting = mNumbeOfsittings.getText().toString();
		if( sitting != null && !sitting.isEmpty()) {
			patient.setNumberOfSittings( Integer.parseInt(sitting));
		}
		patient.setDoAndDont( mDoDont.getText().toString());
		patient.setSpecialInstruction( mSpecialInstructions.getText().toString());
		
		//private RadioGroup mGender;
		switch(mGender.getCheckedRadioButtonId() )
		{
			case R.id.radioMale:
				patient.setGender(getString(R.string.male_short));
				break;
			case R.id.radioFemale:
				patient.setGender(getString(R.string.female_short));
				break;
			default:
				patient.setGender("");
				break;
		}
		//DB version 2
		//private RadioGroup mMaritalStatus;
		switch(mMaritalStatus.getCheckedRadioButtonId() )
		{
			case R.id.radioMarried:
				patient.setMaritalStatus(getString(R.string.married_short));
				break;
			case R.id.radioNotMarried:
				patient.setMaritalStatus(getString(R.string.notmarried_short));
				break;
			default:
				patient.setMaritalStatus("");
				break;
		}

		patient.setOccupationDetails( mOccupationDetails.getText().toString());
		patient.setChiefComplaints( mChiefComplaints.getText().toString());
		patient.setMedicalHistory( mMedicalHistory.getText().toString());
		patient.setPhysioRx( mPhysioRx.getText().toString());
		patient.setOralExamination( mOralExamination.getText().toString());
		patient.setHabbits( mHabbits.getText().toString());
		
		try {
			mPatientManager.savePatient(getActivity());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			Log.e("PatientAddEdit", ex.toString());
		}
		getActivity().finish();
		
	}

	
/*	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	*/
	
	private void setupDiagnosisMultiAutoComplete() {
		/*MultiAutoCompleteTextView*/
		MultiAutoCompleteTextView textView1 = (MultiAutoCompleteTextView)
		getActivity().findViewById(R.id.multiAutoCompleteTextViewDiagnosis);
		textView1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		String[] friends= new String[]{"Knee Pain","Arthritis","back Pain","spasm","parkinson's","Ankle pain","Chronic Bronchitis","Chronic Inflammatory Demyelinating Polyneuropathy",
		"Guillain-Barre Syndrome","Lumbago","Spondylosis","Torticollis"};
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(),
		android.R.layout.simple_dropdown_item_1line, friends);
		textView1.setThreshold(1);
		textView1.setAdapter(adapter2);
	}

	@Override
	public void setManager(IClinicBaseManager mgr) {
		mPatientManager = (PatientManager) mgr;
		
	}
}
