package com.creasylai.yt_app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.creasylai.yt_app.R;
import com.squareup.picasso.Picasso;

/**
 *
 */
public class HomeFragment extends Fragment {

	private ImageView iv_test;

	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		return fragment;
	}

	public HomeFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		iv_test = (ImageView) view.findViewById(R.id.iv_test);
		Picasso.with(this.getContext().getApplicationContext()).load("http://images.bookdao.com/bk/121602/1/323c5ae3-1c2a-4423-98e7-43dc4ee10c6e.jpg")
				.error(R.mipmap.icon_youku).into(iv_test);
		return view;
	}

}
