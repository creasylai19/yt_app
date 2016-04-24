package com.creasylai.yt_app.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.creasylai.yt_app.R;
import com.creasylai.yt_app.adapters.ImageAdapterForRecyclerView;
import com.creasylai.yt_app.consts.AppConst;
import com.creasylai.yt_app.singleinstance.VolleySingleInstance;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

	private TempHandler tempHandler;
	private LoopRecyclerViewPager mRecyclerViewPager;
	private ArrayList<String> mDataset = new ArrayList<>();
	private ImageAdapterForRecyclerView imageAdapterForRecyclerView;

	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		return fragment;
	}

	public HomeFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		tempHandler = new TempHandler(this);
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		mRecyclerViewPager = (LoopRecyclerViewPager) view.findViewById(R.id.viewpager);

		LinearLayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
		mRecyclerViewPager.setTriggerOffset(0.15f);
		mRecyclerViewPager.setFlingFactor(0.25f);
		mRecyclerViewPager.setLayoutManager(layout);

		mRecyclerViewPager.setHasFixedSize(true);
		mRecyclerViewPager.setLongClickable(true);
		mRecyclerViewPager.setSinglePageFling(true);
		mRecyclerViewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						stopAutoScroll();
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
						startAutoScroll();
				}
				return false;
			}
		});
		mDataset = new ArrayList<>();
		mDataset.add("http://r1.ykimg.com/05100000571CC9836714C007F504FD62");
		mDataset.add("http://r2.ykimg.com/05100000571C88B16714C0081600C739");
		mDataset.add("http://r1.ykimg.com/05100000571C88E06714C00764055BC6");
		mDataset.add("http://r4.ykimg.com/05100000571C89126714C007DB074A21");
		mDataset.add("http://r2.ykimg.com/05100000571CC0256714C0086F0D461A");
		mDataset.add("http://r3.ykimg.com/05100000571CC86A6714C007D10462B0");
		imageAdapterForRecyclerView = new ImageAdapterForRecyclerView(mDataset, getContext());
		mRecyclerViewPager.setAdapter(imageAdapterForRecyclerView);
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://192.168.0.101:8001", null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					mDataset = new ArrayList<>();
					JSONArray jsonArray = response.getJSONArray("img_urls");
					for (int i = 0; i < jsonArray.length(); i++) {
						mDataset.add(jsonArray.getString(i));
					}
					if( mDataset.size() > 0 ) {
						imageAdapterForRecyclerView.setDataset(mDataset);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			}
		});
		VolleySingleInstance.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
		return view;
	}

	private void startAutoScroll(){
		tempHandler.sendEmptyMessageDelayed(AppConst.STATIC_INT_KEY.COMMON_KEY_0, 5*1000);
	}

	private void stopAutoScroll(){
		tempHandler.removeCallbacksAndMessages(null);
	}

	@Override
	public void onResume() {
		super.onResume();
		startAutoScroll();
	}

	@Override
	public void onPause() {
		super.onPause();
		stopAutoScroll();
	}

	private void startNext() {
		mRecyclerViewPager.smoothScrollToPosition(mRecyclerViewPager.getCurrentPosition()+1);
	}

	static class TempHandler extends Handler{
		// 内部声明一个弱引用，引用外部类，防止内存泄露
		private WeakReference<HomeFragment> activityWeakReference;
		public TempHandler(HomeFragment homeFragment) {
			activityWeakReference= new WeakReference<>(homeFragment);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case AppConst.STATIC_INT_KEY.COMMON_KEY_0:
					activityWeakReference.get().startNext();
//					this.getLooper().getQueue().
					this.sendEmptyMessageDelayed(AppConst.STATIC_INT_KEY.COMMON_KEY_0, 5*1000);
					break;
			}
		}
	}

}
