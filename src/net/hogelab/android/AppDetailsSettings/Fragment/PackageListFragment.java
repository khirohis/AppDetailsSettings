package net.hogelab.android.AppDetailsSettings.Fragment;

import java.util.List;

import net.hogelab.android.AppDetailsSettings.ApplicationSettings;
import net.hogelab.android.AppDetailsSettings.Dialog.LabelColorDialog;
import net.hogelab.android.AppDetailsSettings.ListAdapter.PackageListAdapter;
import net.hogelab.android.AppDetailsSettings.Model.Entity.PackageInfoEntity;
import net.hogelab.android.AppDetailsSettings.Presenter.PackageListPresenter;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;


//--------------------------------------------------
// class PackageListFragment

public class PackageListFragment extends ListFragment
		implements LabelColorDialog.LabelColorDialogOnClickListener,
			PackageListPresenter.PackageListPresentView {

	private static final String TAG = PackageListFragment.class.getSimpleName();

	private static final String APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";


	private PackageListPresenter mPresenter;
	private String				mCurrentListingApplicationType = null;
	private int					mCurrentListPosition = -1;


	@Override
	public void onAttach(Activity activity) {
		// from Fragment is added
		// to onCreate
		Log.v(TAG, "onAttach");
		super.onAttach(activity);
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// from onAttach
		// to onCreateView
		Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// from onCreate, onDestroyView
		// to onActivityCreated
		Log.v(TAG, "onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
		// from onCreateView
		// to onStart
		Log.v(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);

        mCurrentListingApplicationType = ApplicationSettings.getPackageListListingTypeSetting();

		setListViewOnItemLongClickListener();

        mPresenter = new PackageListPresenter();
		mPresenter.onPresentViewCreate(getActivity(), this);
        mPresenter.loadContent(null);
    }


	@Override
	public void onStart() {
		// from onActivityCreated
		// to onResume
		Log.v(TAG, "onStart");
		super.onStart();
	}


	@Override
	public void onResume() {
		// from onStart
		// to Fragment is active
		Log.v(TAG, "onResume");
		super.onResume();

		mPresenter.onPresentViewShow();
	}


	@Override
	public void onPause() {
		// from Fragment is active
		// to onStop
		Log.v(TAG, "onPause");
		super.onPause();

		mPresenter.onPresentViewHide();
	}


	@Override
	public void onStop() {
		// from onPause
		// to onDestroyView
		Log.v(TAG, "onStop");
		super.onStop();
	}


	@Override
	public void onDestroyView() {
		// from onStop
		// to onDestroy
		Log.v(TAG, "onDestroyView");
		super.onDestroyView();
	}


	@Override
	public void onDestroy() {
		// from onDestroyView
		// to onDetach
		Log.v(TAG, "onDestroy");
		super.onDestroy();

		mPresenter.onPresentViewDestroy();
		mPresenter = null;
	}


	@Override
	public void onDetach() {
		// from onDestroy
		// to Fragment is destroyed
		Log.v(TAG, "onDetach");
		super.onDetach();
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		PackageInfoEntity info = (PackageInfoEntity)getListAdapter().getItem(position);
		if (info != null) {
			Uri uri = Uri.parse("package:" + info.getInfo().packageName);
			Intent intent = new Intent(APPLICATION_DETAILS_SETTINGS, uri);
			startActivityForResult(intent, 0);
		}
    }


	@Override
	public void onClickLabelColorDialogList(int position) {
		if (mCurrentListPosition < 0 || mCurrentListPosition >= getListAdapter().getCount()) {
			return;
		}

		PackageInfoEntity info = (PackageInfoEntity)getListAdapter().getItem(mCurrentListPosition);
		mPresenter.setLabelColor(info.getInfo().packageName, position);

		// 通知の前にデータの取り直しを？
		PackageListAdapter adapter = (PackageListAdapter)getListAdapter();
		adapter.notifyDataSetChanged();

		mCurrentListPosition = -1;
	}


	@Override
	public void onContentLoadingStart(Object tag, int progressMax) {
		Log.v(TAG, "onStartContentLoading:progressMax=" + Integer.toString(progressMax));
	}

	@Override
	public void onContentLoadingProgress(Object tag, int currentProgress) {
		Log.v(TAG, "onProgressContentLoading:currentProgress=" + Integer.toString(currentProgress));
	}


	@Override
	public void onContentLoaded(Object tag, Object content) {
		Log.v(TAG, "onContentLoaded");

		PackageListAdapter adapter = new PackageListAdapter(getActivity(), contentToList(content));
		setListAdapter(adapter);
	}


	@Override
	public void onContentLoadError(Object tag, Object error) {
		Log.v(TAG, "onContentLoadError");
	}


	@Override
	public void onContentUpdated(Object tag) {
		Log.v(TAG, "onContentUpdated");

		String setupApplicationType = ApplicationSettings.getPackageListListingTypeSetting();
		if (mCurrentListingApplicationType != setupApplicationType) {
			mCurrentListingApplicationType = setupApplicationType;
	        mPresenter.loadContent(null);
		}
	}


	//--------------------------------------------------
	// private functions

	private void setListViewOnItemLongClickListener() {
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
				mCurrentListPosition = position;

				LabelColorDialog dialog = LabelColorDialog.newInstance(PackageListFragment.this);
                dialog.show(getFragmentManager(), "dialog");

                return true;
			}
		});
	}


	@SuppressWarnings("unchecked")
	private List<PackageInfoEntity> contentToList(Object content) {
		return (List<PackageInfoEntity>)content;
	}
}
