/**
 * 
 */
package clinic2.activlife.com.myapplication.ui;

import java.util.List;

import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.bl.PatientManager;
import clinic2.activlife.com.myapplication.model.AppointmentDetailsModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * @author vtandasi
 *
 */
public class PatientAppointmentsListAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private PatientManager mPatientManager = null;
	List<AppointmentDetailsModel> mAppointmentDetailsModelList = null;
	
	public PatientAppointmentsListAdapter(Context context , PatientManager manager) {
		mContext = context;
		mPatientManager = manager;
		computeDataCounts();
		
	}
	
	private void computeDataCounts() {
		if( mPatientManager.getPatient() != null) {
			mAppointmentDetailsModelList = mPatientManager.getPatient().getAppointmentDetailsModel();
			//Rough count. Need to get the groupwise size.
			mAppointmentDetailsModelList.size();
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		if( null != mAppointmentDetailsModelList) {
			return mAppointmentDetailsModelList.get(childPosition);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	  public View getChildView(int groupPosition, final int childPosition,
	            boolean isLastChild, View convertView, ViewGroup parent) {
	    if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.appointmentlistrow_details, null);
        }
	    AppointmentDetailsModel dataModel = (AppointmentDetailsModel) getChild(groupPosition, childPosition);
	    TextView txtListChild = (TextView) convertView.findViewById(R.id.textDateTime);
	    
	    txtListChild.setText(dataModel.getCreatedTime().toString());
	    
	    txtListChild = (TextView) convertView.findViewById(R.id.textTreatment);
	    txtListChild.setText(dataModel.getSessionTreatment());

	    return convertView;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		if (mAppointmentDetailsModelList != null) {
			return mAppointmentDetailsModelList.size();
		}
		else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 */
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 */
	@Override
	public long getGroupId(int groupId) {
		// TODO Auto-generated method stub
		return groupId;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.appointmentlistrow_group, null);
        }
		return convertView;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 */
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
	 */
	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}

}
