package com.onedollar.util;

import com.example.onedollar.R;
import com.onedollar.base.App;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

public class DialogUtil {
	public static Dialog createProgressDialog(Activity activity) {
		Dialog dialog = new Dialog(activity);
		// dialog.setCancelable(false);
		dialog.show();
		return dialog;
	}

	public static Dialog showAlert(Activity activity, String title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, int title, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, int title, int msg,
			DialogInterface.OnClickListener confirmListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		if (confirmListener == null) {
			builder.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
		} else {
			builder.setPositiveButton(R.string.confirm, confirmListener);
		}

		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, int title, String msg,
			DialogInterface.OnClickListener confirmListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		if (confirmListener == null) {
			builder.setPositiveButton(R.string.confirm,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
		} else {
			builder.setPositiveButton(R.string.confirm, confirmListener);
		}

		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, int title, int msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, String title, int msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		builder.setCancelable(false);
		return builder.create();
	}

	public static Dialog showAlert(Activity activity, String msg,
			DialogInterface.OnClickListener confirmListener) {
		return showAlert(activity, R.string.tip, msg, confirmListener);
	}

	public static Dialog showAlert(Activity activity, int msg,
			DialogInterface.OnClickListener confirmListener) {
		return showAlert(activity, R.string.tip, msg, confirmListener);
	}

	public static Dialog showAlert(Activity activity, String msg) {
		return showAlert(activity, R.string.tip, msg);
	}

	public static Dialog showAlert(Activity activity, int msg) {
		return showAlert(activity, R.string.tip, msg);
	}

	public static Dialog createDialog(Activity activity, String msg,
			DialogInterface.OnClickListener confirmListener,
			DialogInterface.OnClickListener cancelListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(R.string.tip);
		builder.setMessage(msg);
		builder.setPositiveButton(R.string.confirm, confirmListener);
		builder.setNegativeButton(R.string.cancel, cancelListener);
		builder.setCancelable(false);
		return builder.create();
	}

	public static void showTip(Activity activity, int title, int content) {
		View view = View.inflate(activity, R.layout.alert_dialog_text_style,
				null);
		TextView titleTextView = (TextView) view
				.findViewById(R.id.alertdialog_title);
		TextView contentTextView = (TextView) view
				.findViewById(R.id.alertdialog_content);
		titleTextView.setText(title);
		contentTextView.setText(content);
		contentTextView
				.setMovementMethod(ScrollingMovementMethod.getInstance());
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final Dialog dialog = builder.create();
		dialog.show();
		view.findViewById(R.id.cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		dialog.setContentView(view);
		int width = App.getInstance().getResources().getDisplayMetrics().widthPixels * 20 / 32;
		dialog.getWindow().setLayout(width, width);
	}
	
	public static void showTip(Activity activity, String title, String content) {
		View view = View.inflate(activity, R.layout.alert_dialog_text_style,
				null);
		TextView titleTextView = (TextView) view
				.findViewById(R.id.alertdialog_title);
		TextView contentTextView = (TextView) view
				.findViewById(R.id.alertdialog_content);
		titleTextView.setText(title);
		contentTextView.setText(content);
		contentTextView
				.setMovementMethod(ScrollingMovementMethod.getInstance());
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		final Dialog dialog = builder.create();
		dialog.show();
		view.findViewById(R.id.cancel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
		dialog.setContentView(view);
		int width = App.getInstance().getResources().getDisplayMetrics().widthPixels * 20 / 32;
		dialog.getWindow().setLayout(width, width);
	}
}
