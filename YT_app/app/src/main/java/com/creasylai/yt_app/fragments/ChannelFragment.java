package com.creasylai.yt_app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creasylai.yt_app.R;

/**
 * channel_fragment
 */
public class ChannelFragment extends Fragment {

	public ChannelFragment() {
		// Required empty public constructor
	}

	public static ChannelFragment newInstance() {
		ChannelFragment fragment = new ChannelFragment();
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_channel, container, false);
	}

}
