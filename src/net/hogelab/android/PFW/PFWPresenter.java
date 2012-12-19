package net.hogelab.android.PFW;


//--------------------------------------------------
// class PFWPresenter

public class PFWPresenter {

	@SuppressWarnings("unused")
	private static final String TAG = PFWPresenter.class.getSimpleName();


	private PFWPassiveView presentView;


	//--------------------------------------------------
	// interfaces

	public interface PFWPassiveView {
	}


	//--------------------------------------------------
	// public functions

	public PFWPresenter(PFWPassiveView view) {
		presentView = view;
	}
}
