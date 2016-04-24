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

import com.creasylai.yt_app.R;
import com.creasylai.yt_app.adapters.ImageAdapterForRecyclerView;
import com.creasylai.yt_app.consts.AppConst;
import com.lsjwzh.widget.recyclerviewpager.LoopRecyclerViewPager;

import java.lang.ref.WeakReference;

/**
 *
 */
public class HomeFragment extends Fragment {

	private TempHandler tempHandler;
	private LoopRecyclerViewPager mRecyclerViewPager;

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
		mRecyclerViewPager.setAdapter(new ImageAdapterForRecyclerView(new String[]{
				"http://h.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11dd1855cebd12c8fcc2ce2db5.jpg",
				"http://c.hiphotos.baidu.com/image/pic/item/d1160924ab18972bce4df718e2cd7b899f510ae8.jpg",
				"http://d.hiphotos.baidu.com/image/pic/item/562c11dfa9ec8a13f075f10cf303918fa1ecc0eb.jpg",
				"http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11daf25f19bc12c8fcc3ce2d46.jpg",
				"http://e.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600be63e95eed50352ac75cb7ae.jpg"
		}, getContext()));
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
