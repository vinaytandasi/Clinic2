package clinic2.activlife.com.myapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.bl.PatientManager;
import clinic2.activlife.com.myapplication.bl.BlInterfaces.IClinicBaseManager;
import clinic2.activlife.com.myapplication.db.PatientTable;
import clinic2.activlife.com.myapplication.model.PatientModel;
import clinic2.activlife.com.myapplication.ui.UIInterfaces.IClinicBaseFragment;


/*
 * Class to handle the a patients appointments. It shows the list of appointments and allows to add new appointments.
 */
public class PatientAppointmentsList extends Fragment implements
		IClinicBaseFragment {
	
	private LinearLayout rootLayout;
	private PatientManager mPatientManager = null;
	private PatientAppointmentsListAdapter mPatientAppointmentsListAdapter = null;
	ExpandableListView mExpandableListPatientAppointmentsList;
	Button addAppointmentButton;

	int APPOINTMENT_ADD_EDIT = 1;

	@Override
	public void setManager(IClinicBaseManager mgr) {
		mPatientManager = (PatientManager) mgr;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		rootLayout = (LinearLayout) inflater.inflate(R.layout.patient_appointmentslist, container, false);
		
		return rootLayout;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    super.onViewCreated(view, savedInstanceState);
	    
		addBindings(view);
		addHandlers(view);

		loadValuesFromManager(view);
	    
	}

	private void loadValuesFromManager(View view) {
		mPatientAppointmentsListAdapter = new PatientAppointmentsListAdapter(this.getActivity(), mPatientManager);
		
		mExpandableListPatientAppointmentsList = (ExpandableListView) view.findViewById(R.id.listViewAppointments);

		mExpandableListPatientAppointmentsList.setAdapter(mPatientAppointmentsListAdapter);
		
	}

	private void addHandlers(View view) {

		// TODO Auto-generated method stub

		addAppointmentButton = (Button) getActivity().findViewById(R.id.addAppointment);

		addAppointmentButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addEditAppointment();
			}
		});
	}

	public void addEditAppointment() {
		Intent myIntent = new Intent(getActivity(), AppointmentAddEditContainer.class);
/*
		Bundle bundle = new Bundle();
		if(id != null && !id.isEmpty()) {
			bundle.putString(PatientTable.KEY_ID, id);
			myIntent.putExtras(bundle);
		}
*/
		this.startActivityForResult(myIntent, APPOINTMENT_ADD_EDIT );


	}

	private void addBindings(View view) {
		PatientModel patient = mPatientManager.getPatient();
		
	}
}
