package net.hogelab.android.AppDetailsSettings.Presenter.Action;

import java.util.List;

import net.hogelab.android.AppDetailsSettings.AppDetailsSettingsApplication;
import net.hogelab.android.AppDetailsSettings.Entity.PackageInfoEntity;
import net.hogelab.android.AppDetailsSettings.Model.ListSettingsModel;
import net.hogelab.android.AppDetailsSettings.Model.PackageListModel;
import net.hogelab.android.PFW.PFWAction;


//--------------------------------------------------
// class LoadPackageListAction

public class LoadPackageListAction extends PFWAction {

	public LoadPackageListAction(PFWActionListener listener, Object tag) {
		super(listener, tag);
	}


	@Override
	protected boolean doAction() {
		ListSettingsModel lsm = AppDetailsSettingsApplication.getApplication().getListSettingsModel();
		PackageListModel plm = AppDetailsSettingsApplication.getApplication().getPackageListModel();

		List<PackageInfoEntity> list = null;

		String listType = lsm.getPackageListListingType();
		if (listType.equals("0")) {
			list = plm.selectSystemPackages();
		} else if (listType.equals("1")) {
			list = plm.selectDownloadedPackages();
		} else if (listType.equals("2")) {
			list = plm.selectBothPackages();
		}

		if (list != null) {
			mResult = list;

			return true;
		}

		return false;
	}
}
