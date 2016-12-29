package com.onedollar.base;

import com.onedollar.util.DialogUtil;
import com.onedollar.util.Util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class BaseFragment extends Fragment implements OnClickListener,
		OnFocusChangeListener, OnItemClickListener {
	protected String TAG = getClass().getSimpleName();
	protected ProgressDialog mProgressDialog = null;
	protected Dialog mAlertDialog = null;
	protected Handler mHandler = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {

	}

	/**
	 * 显示 mProgressDialog
	 */
	public void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = Util.createProgressDialog(getActivity());
		}
		dismissProgressDialog();
		mProgressDialog.setMessage("loading");
		mProgressDialog.show();
	}

	/**
	 * 隐藏mProgressDialog
	 */
	public void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public void showToast(int strId) {
		Toast.makeText(App.getInstance(), strId, Toast.LENGTH_SHORT).show();
	}

	public void showToast(String msg) {
		Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
	}

	public void showAlert(String title, String msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), title, msg);
		mAlertDialog.show();
	}

	public void showAlert(int title, int msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), title, msg);
		mAlertDialog.show();
	}

	public void showAlert(String title, int msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), title, msg);
		mAlertDialog.show();
	}

	public void showAlert(int title, String msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), title, msg);
		mAlertDialog.show();
	}

	public void showAlert(String msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), msg);
		mAlertDialog.show();
	}

	public void showAlert(int msg) {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
		mAlertDialog = DialogUtil.showAlert(getActivity(), msg);
		mAlertDialog.show();
	}

	public void dismissSoftInput() {
		InputMethodManager imm = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()
				&& getActivity().getWindow().getDecorView() != null
				&& getActivity().getWindow().getDecorView().getWindowToken() != null) {
			imm.hideSoftInputFromWindow(getActivity().getWindow()
					.getDecorView().getWindowToken(), 0);
		}
	}

	public BaseFragment getPreFragment() {
		return null;
	}

	public void setPreFragment(BaseFragment fragment) {

	}

	@Override
	public void onFocusChange(View arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// 销毁 mProgressDialog
		if (mProgressDialog != null) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			mProgressDialog = null;
		}
		if (mAlertDialog != null) {
			if (mAlertDialog.isShowing()) {
				mAlertDialog.dismiss();
			}
			mAlertDialog = null;
		}
		if (mHandler != null) {
			mHandler.removeCallbacks(null);
		}
		super.onDestroy();
	}

	public void notifyDataSetChanged(Object object) {

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	public void onReceiveData(Object object) {

	}

	public void refreshData() {

	}
}
