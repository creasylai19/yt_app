package com.creasylai.yt_app.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.creasylai.yt_app.R;

/**
 * Created by laicreasy on 16/4/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

	private ProgressDialog pDialog = null;

	public void showProgress() {
		pDialog = new ProgressDialog(this);
		pDialog.setMessage(getString(R.string.loading));
		pDialog.show();
	}

	public void showProgress(String progressTip) {
		pDialog = new ProgressDialog(this);
		if( TextUtils.isEmpty(progressTip) ) {
			showProgress();
		} else {
			pDialog.setMessage(getString(R.string.loading));
			pDialog.show();
		}
	}

	public void dismissProgress() {
		pDialog.dismiss();
	}

}
