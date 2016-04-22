package com.creasylai.yt_app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

/**
 * Created by laicreasy on 15/11/11.
 */
public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
	}

	/**
	 * 获得当前进程的名字
	 *
	 * @param context
	 * @return 进程号
	 */
	public static String getCurProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}
}
