package com.creasylai.yt_app.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creasylai.yt_app.R;

/**
 * 底部导航栏
 * TODO 待优化
 */
public class NavigationBar extends LinearLayout implements View.OnClickListener {

	private TextView tv_navigation_home;
	private TextView tv_navigation_channel;
	private TextView tv_navigation_subscribe;
	private TextView tv_navigation_vip;
	private TextView tv_navigation_user;
	private OnItemClickListener onItemClickListener;

	public NavigationBar(Context context) {
		super(context);
		init(null, 0);
	}

	public NavigationBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public NavigationBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
//		final TypedArray a = getContext().obtainStyledAttributes(
//				attrs, R.styleable.NavigationBar, defStyle, 0);
//		a.recycle();
		LayoutInflater.from(getContext()).inflate(R.layout.custom_navigation_bar, this);
		tv_navigation_home = (TextView) findViewById(R.id.tv_navigation_home);
		tv_navigation_channel = (TextView) findViewById(R.id.tv_navigation_channel);
		tv_navigation_subscribe = (TextView) findViewById(R.id.tv_navigation_subscribe);
		tv_navigation_vip = (TextView) findViewById(R.id.tv_navigation_vip);
		tv_navigation_user = (TextView) findViewById(R.id.tv_navigation_user);
//		this.setOrientation(LinearLayout.HORIZONTAL);
		tv_navigation_home.setOnClickListener(this);
		tv_navigation_channel.setOnClickListener(this);
		tv_navigation_subscribe.setOnClickListener(this);
		tv_navigation_vip.setOnClickListener(this);
		tv_navigation_user.setOnClickListener(this);
		this.tv_navigation_home.setSelected(true);
	}

	private void resumeNormalState(){
		this.tv_navigation_home.setSelected(false);
		this.tv_navigation_channel.setSelected(false);
		this.tv_navigation_subscribe.setSelected(false);
		this.tv_navigation_vip.setSelected(false);
		this.tv_navigation_user.setSelected(false);
	}

	@Override
	public void onClick(View v) {
		resumeNormalState();
		switch (v.getId()) {
			case R.id.tv_navigation_home:
				this.tv_navigation_home.setSelected(true);
				if( null != this.onItemClickListener ) {
					this.onItemClickListener.onHomeItemClick();
				}
				break;
			case R.id.tv_navigation_channel:
				this.tv_navigation_channel.setSelected(true);
				if( null != this.onItemClickListener ) {
					this.onItemClickListener.onChannelItemClick();
				}
				break;
			case R.id.tv_navigation_subscribe:
				this.tv_navigation_subscribe.setSelected(true);
				if( null != this.onItemClickListener ) {
					this.onItemClickListener.onSubcribeItemClick();
				}
				break;
			case R.id.tv_navigation_vip:
				this.tv_navigation_vip.setSelected(true);
				if( null != this.onItemClickListener ) {
					this.onItemClickListener.onVipItemClick();
				}
				break;
			case R.id.tv_navigation_user:
				this.tv_navigation_user.setSelected(true);
				if( null != this.onItemClickListener ) {
					this.onItemClickListener.onUserItemClick();
				}
				break;
			default:break;
		}
//		LogUtils.debug("creasylai", this.tv_navigation_user.isSelected()+"");
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener){
		this.onItemClickListener = onItemClickListener;
	}

	public interface OnItemClickListener {
		void onHomeItemClick();
		void onChannelItemClick();
		void onSubcribeItemClick();
		void onVipItemClick();
		void onUserItemClick();
	}

}
