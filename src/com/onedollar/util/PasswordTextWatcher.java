package com.onedollar.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class PasswordTextWatcher implements TextWatcher {

	String tmp = "";
	String digits = "@#$%^&* ";
	EditText mEditText = null;

	public PasswordTextWatcher(EditText editText) {
		mEditText = editText;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		mEditText.setSelection(s.length());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		tmp = s.toString();
	}

	@Override
	public void afterTextChanged(Editable s) {
		String str = s.toString();
		if (str.equals(tmp)) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (digits.indexOf(str.charAt(i)) < 0) {
				sb.append(str.charAt(i));
			}
		}
		tmp = sb.toString();
		mEditText.setText(tmp);
	}

}