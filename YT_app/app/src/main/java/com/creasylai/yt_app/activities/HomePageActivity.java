package com.creasylai.yt_app.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.creasylai.yt_app.R;
import com.creasylai.yt_app.common.NavigationBar;
import com.creasylai.yt_app.consts.AppConst;
import com.creasylai.yt_app.fragments.ChannelFragment;
import com.creasylai.yt_app.fragments.HomeFragment;

public class HomePageActivity extends BaseActivity {

	private SparseArray<Fragment> sparseArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
		setSupportActionBar(myToolbar);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setLogo(R.mipmap.icon_youku);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		FrameLayout fl_fragment_container = (FrameLayout)findViewById(R.id.fl_fragment_container);
		NavigationBar navigationBar = (NavigationBar)findViewById(R.id.navigationbar_home_page);
		if( null != navigationBar ) {
			navigationBar.setOnItemClickListener(new NavigationBar.OnItemClickListener() {
				@Override
				public void onHomeItemClick() {
					showFragment(AppConst.STATIC_INT_KEY.HOME_FRAGMENT);
				}

				@Override
				public void onChannelItemClick() {
					showFragment(AppConst.STATIC_INT_KEY.CHANNEL_FRAGMENT);
				}

				@Override
				public void onSubcribeItemClick() {
					showFragment(AppConst.STATIC_INT_KEY.SUBCRIBE_FRAGMENT);
				}

				@Override
				public void onVipItemClick() {
					showFragment(AppConst.STATIC_INT_KEY.VIP_FRAGMENT);
				}

				@Override
				public void onUserItemClick() {
					showFragment(AppConst.STATIC_INT_KEY.USER_FRAGMENT);
				}
			});
		}
		sparseArray = new SparseArray<>();

		// Check that the activity is using the layout version with
		// the fragment_container FrameLayout
		if (null != fl_fragment_container) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}
			showFragment(AppConst.STATIC_INT_KEY.HOME_FRAGMENT);

//			// Create a new Fragment to be placed in the activity layout
//			HomeFragment homeFragment = HomeFragment.newInstance();
//
//			getSupportFragmentManager().putFragment(getIntent().getExtras(), AppConst.STATIC_STRING_KEY.HOME_FRAGMENT, homeFragment);
//			// Add the fragment to the 'fragment_container' FrameLayout
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.fl_fragment_container, homeFragment).commit();
		}
	}

	private void showFragment(int fragment_key) {
		Fragment fragment = sparseArray.get(fragment_key);
		if( null == fragment ) {
			switch (fragment_key) {
				case AppConst.STATIC_INT_KEY.HOME_FRAGMENT:
					fragment = HomeFragment.newInstance();
					break;
				case AppConst.STATIC_INT_KEY.CHANNEL_FRAGMENT:
					fragment = ChannelFragment.newInstance();
					break;
				case AppConst.STATIC_INT_KEY.SUBCRIBE_FRAGMENT:
					break;
				case AppConst.STATIC_INT_KEY.VIP_FRAGMENT:
					break;
				case AppConst.STATIC_INT_KEY.USER_FRAGMENT:
					break;
			}
			if( null != fragment ) {
				sparseArray.put(fragment_key, fragment);
			}
		}
		if( null != fragment ) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

			// Replace whatever is in the fragment_container view with this fragment,
			// and add the transaction to the back stack so the user can navigate back
			transaction.replace(R.id.fl_fragment_container, fragment);
//				transaction.addToBackStack(null);

			// Commit the transaction
			transaction.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_search:
				// User chose the "Settings" item, show the app settings UI...
				return true;

			case R.id.action_game:
				// User chose the "Favorite" action, mark the current item
				// as a favorite...
				return true;
			case R.id.action_history:
				// User chose the "Favorite" action, mark the current item
				// as a favorite...
				return true;
			case R.id.action_other:
				// User chose the "Favorite" action, mark the current item
				// as a favorite...
				return true;
			default:
				// If we got here, the user's action was not recognized.
				// Invoke the superclass to handle it.
				return super.onOptionsItemSelected(item);
		}
	}
}
