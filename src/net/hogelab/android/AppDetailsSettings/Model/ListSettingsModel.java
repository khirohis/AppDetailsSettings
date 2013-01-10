package net.hogelab.android.AppDetailsSettings.Model;

import net.hogelab.android.AppDetailsSettings.ApplicationSettings;
import net.hogelab.android.PFW.PFWModel;


//--------------------------------------------------
// class PackageInfoModel

public class ListSettingsModel extends PFWModel {

	@SuppressWarnings("unused")
	private static final String TAG = ListSettingsModel.class.getSimpleName();


	//--------------------------------------------------
	// public functions

	public ListSettingsModel() {
	}


	public String getPackageListListingType() {
		return ApplicationSettings.getPackageListListingTypeSetting();
	}

	public void setPackageListListingType(String value) {
		ApplicationSettings.setPackageListListingTypeSetting(value);

		updated();
	}


	public boolean getLabelColorVisible(int colorIndex) {
		return ApplicationSettings.getLabelColorVisibleSetting(colorIndex);
	}

	public void setLabelColorVisible(int colorIndex, boolean value) {
		ApplicationSettings.setLabelColorVisibleSetting(colorIndex, value);

		updated();
	}
}
