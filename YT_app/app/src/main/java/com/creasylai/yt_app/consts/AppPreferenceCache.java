package com.creasylai.yt_app.consts;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by laicreasy on 15/11/25.
 */
public class AppPreferenceCache {

	private static AppPreferenceCache mInstance;
	private Context mCtx;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;

	private AppPreferenceCache(Context context) {
		// getApplicationContext() is key, it keeps you from leaking the
		// Activity or BroadcastReceiver if someone passes one in.
		mCtx = context.getApplicationContext();
		sharedPreferences = mCtx.getSharedPreferences(AppConst.STATIC_STRING_KEY.APP_SHARE_PREFERENCE, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	public static synchronized AppPreferenceCache getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new AppPreferenceCache(context);
		}
		return mInstance;
	}

}
