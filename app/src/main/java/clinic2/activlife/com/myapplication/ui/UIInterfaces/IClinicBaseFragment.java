package clinic2.activlife.com.myapplication.ui.UIInterfaces;

import clinic2.activlife.com.myapplication.bl.BlInterfaces.IClinicBaseManager;

public interface IClinicBaseFragment {

	IClinicBaseManager mClinicBaseManager = null;
	public void setManager(IClinicBaseManager mgr);
}
