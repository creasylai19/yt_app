package com.creasylai.yt_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.creasylai.yt_app.R;
import com.creasylai.yt_app.consts.AppConst;

import java.lang.ref.WeakReference;

public class StartAdvertisementActivity extends BaseActivity {

	private TextView tv_skip_ad;
	private ImageView img_bg;
	private TempHandler mHandler;

	static class TempHandler extends Handler{

		// 内部声明一个弱引用，引用外部类，防止内存泄露
		private WeakReference<StartAdvertisementActivity > activityWeakReference;
		public TempHandler(StartAdvertisementActivity activity) {
			activityWeakReference= new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case AppConst.STATIC_INT_KEY.COMMON_KEY_0:
					activityWeakReference.get().goToHomePage();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_start_advertisement);

		tv_skip_ad = (TextView)findViewById(R.id.tv_skip_ad);
		img_bg = (ImageView) findViewById(R.id.img_bg);

		tv_skip_ad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goToHomePage();
			}
		});
		img_bg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goToWebview();
			}
		});
		mHandler = new TempHandler(this);
	}

	private void goToWebview() {

	}

	private void goToHomePage() {
		startActivity(new Intent(this, HomePageActivity.class));
		this.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mHandler.sendEmptyMessageDelayed(AppConst.STATIC_INT_KEY.COMMON_KEY_0, AppConst.GO_HOME_DELAY);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHandler.removeCallbacksAndMessages(null);
	}
}
