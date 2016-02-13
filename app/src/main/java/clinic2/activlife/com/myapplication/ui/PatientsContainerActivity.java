package clinic2.activlife.com.myapplication.ui;


import clinic2.activlife.com.myapplication.R;
import clinic2.activlife.com.myapplication.bl.PatientManager;
import clinic2.activlife.com.myapplication.db.PatientTable;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class PatientsContainerActivity extends AppCompatActivity {
    static final int NUM_ITEMS = 2;

    MyAdapter mAdapter;

    ViewPager mPager;
	private String mId = null;
	private PatientManager mPatientManager = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_fragment_pager);

		String value = null;
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		if(extras != null) {
			value = extras.getString(PatientTable.KEY_ID);
		}
		if(value != null && !value.isEmpty()) {
			mId = value; 
		}

        loadData();
		
        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        final ActionBar actionBar = getSupportActionBar();

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

            }
        };

            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Patient Details")
                            .setTabListener(tabListener));
        actionBar.addTab(
                actionBar.newTab()
                        .setText("Sessions")
                        .setTabListener(tabListener));
    
    }

    private void loadData() {
    	
    	mPatientManager = new PatientManager();
		if(!TextUtils.isEmpty(mId)) {
			mPatientManager.readPatientById(this, mId);			
		}
		
	}

	public  class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        /*
         * (non-Javadoc)
         * Return the fragment to be used.
         * 0 - PatientAddEdit to edit 1 patient details.
         * 1 - PatientAppointmentsList to show list of appointments.
         * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
         */
        @Override
        public Fragment getItem(int position) {
        	if( position == 0 ) {
	            PatientAddEdit patientEdit = new PatientAddEdit();
	            patientEdit.setManager(mPatientManager);
	            return patientEdit;
        	}
        	else {
	        	PatientAppointmentsList patientAppointments = new PatientAppointmentsList();
	            patientAppointments.setManager(mPatientManager);
	            return patientAppointments;
        	}        	
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.patientdetails, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(this, "Example action.", Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
