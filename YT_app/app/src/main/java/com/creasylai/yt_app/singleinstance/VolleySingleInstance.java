package com.creasylai.yt_app.singleinstance;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.creasylai.yt_app.common.framework.LruBitmapCache;

/**
 * Created by laicreasy on 15/11/15.
 */
public class VolleySingleInstance {
	private static VolleySingleInstance mInstance;
	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;
	private Context mCtx;
	private static Object object = new Object();

	private VolleySingleInstance(Context context) {
		// getApplicationContext() is key, it keeps you from leaking the
		// Activity or BroadcastReceiver if someone passes one in.
		mCtx = context.getApplicationContext();
		mRequestQueue = getRequestQueue();
		mImageLoader = new ImageLoader(mRequestQueue,new LruBitmapCache(LruBitmapCache.getCacheSize(mCtx)));
	}

	public static synchronized VolleySingleInstance getInstance(Context context) {
		if (mInstance == null) {
			synchronized (object){
				if (mInstance == null) {
					mInstance = new VolleySingleInstance(context);
				}
			}
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mCtx);
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
}
