package com.example.onedollar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.onedollar.base.BaseActivity;
import com.onedollar.fragment.*;
import com.onedollar.base.BaseFragment;
import com.onedollar.data.Preferences;
import com.example.onedollar.R;

public class MainActivity extends BaseActivity {
	private DrawerLayout mSlidingMenu = null;
	private BaseFragment mCurrentFragment = null;
	private BaseFragment[] mFragments = null;
	private View mMenu = null;
	private LinearLayout mLinearLayout = null;

	private boolean isFirstStart = false;
	private int mCurrentRadioId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startNormalView();
		// isFirstStart = Preferences.getIsFirst();
		// if (savedInstanceState != null) {
		// mCurrentRadioId = savedInstanceState.getInt("id", -1);
		// }
		// if (isFirstStart) {
		// isFirstStart = false;
		// Preferences.saveIsFirst(isFirstStart);
		// startViewPage();
		// } else {
		// startNormalView();
		// }
	}

	private void startNormalView() {
		setContentView(R.layout.activity_main);
		findview();
		setListener();

		mFragments = new BaseFragment[] { new FantasyFragment(),
				new UpcomingFragment(), new WinnersFragment(),
				new CartFragment(), new MeFragment() };
		if (mCurrentRadioId == -1) {
			mCurrentRadioId = R.id.shopping_fantasy;
		}
		findViewById(mCurrentRadioId).performClick();
	}

	private void setListener() {
		findViewById(R.id.menu_my_answer).setOnClickListener(this);
		findViewById(R.id.menu_my_introduce).setOnClickListener(this);
		findViewById(R.id.menu_my_msg).setOnClickListener(this);
		findViewById(R.id.menu_my_question).setOnClickListener(this);
		findViewById(R.id.menu_my_follow).setOnClickListener(this);
		findViewById(R.id.menu_setting).setOnClickListener(this);
		findViewById(R.id.menu_koubei).setOnClickListener(this);

		findViewById(R.id.shopping_fantasy).setOnClickListener(this);
		findViewById(R.id.shopping_upcoming).setOnClickListener(this);
		findViewById(R.id.shopping_winner).setOnClickListener(this);
		findViewById(R.id.shopping_cart).setOnClickListener(this);
		findViewById(R.id.user_setting).setOnClickListener(this);
		mMenu.setOnTouchListener(new OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return true;
			}

		});
		mSlidingMenu.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {

			}

			@Override
			public void onDrawerSlide(View arg0, float arg1) {

			}

			@Override
			public void onDrawerOpened(View arg0) {
				if (Preferences.getIsLogin()) {
					// mPersonInfo = Preferences.getLocalUserInfo();
				} else {
					// mPersonInfo = null;
				}
			}

			@Override
			public void onDrawerClosed(View arg0) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		onClickViewId(v.getId());
	}

	private void onClickViewId(final int id) {
		switch (id) {
		case R.id.menu_my_answer:
			// startActivity();
			return;
		case R.id.menu_my_question:
			// startActivity();
			return;
		case R.id.menu_my_follow:
			// startActivity();
			return;
		case R.id.menu_my_msg:
			// startActivity();
			return;
		case R.id.menu_my_introduce:
			// startActivity();
			return;
		case R.id.menu_setting:
			// startActivity();
			return;
		case R.id.shopping_upcoming:
			switchFragment(mFragments[1]);
			mCurrentRadioId = id;
			return;
		case R.id.shopping_winner:
			switchFragment(mFragments[2]);
			mCurrentRadioId = id;
			return;
		case R.id.shopping_fantasy:
			switchFragment(mFragments[0]);
			mCurrentRadioId = id;
			return;
		case R.id.shopping_cart:
			switchFragment(mFragments[3]);
			mCurrentRadioId = id;
			return;
		case R.id.user_setting:
			switchFragment(mFragments[4]);
			mCurrentRadioId = id;
			return;

		default:
			return;
		}
	}

	private void switchFragment(BaseFragment fragment) {
		if (fragment == null) {
			fragment = mFragments[0];
		}

		if (fragment.equals(mCurrentFragment)) {
			return;
		}

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		if (mCurrentFragment != null) {
			transaction.hide(mCurrentFragment);
			mCurrentFragment.onPause();
		}
		if (!fragment.isAdded()) {
			transaction.add(R.id.fragment_container, fragment)
					.commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
		} else {
			transaction.show(fragment).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
		}
		mCurrentFragment = fragment;
		mCurrentFragment.onResume();
	}

	private void findview() {
		mSlidingMenu = (DrawerLayout) findViewById(R.id.drawer_layout);
		mMenu = findViewById(R.id.left_drawer);
	}

	private void startViewPage() {
		// TODO Auto-generated method stub

	}

	private long exitTime = 0;

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
