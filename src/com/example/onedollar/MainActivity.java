package com.example.onedollar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.onedollar.base.BaseActivity;
import com.onedollar.fragment.*;
import com.onedollar.base.BaseFragment;
import com.example.onedollar.R;

public class MainActivity extends BaseActivity {
	private BaseFragment mCurrentFragment = null;
	private BaseFragment[] mFragments = null;
	private int mCurrentRadioId = -1;
	private ImageView mCategoryImg = null;
	private TextView mTitleTextView = null;
	private RelativeLayout mTitleRelativeLayout = null;

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

	private void findview() {
		mCategoryImg = (ImageView)findViewById(R.id.btn_category);
		mTitleTextView = (TextView)findViewById(R.id.title_content);
		mTitleRelativeLayout = (RelativeLayout)findViewById(R.id.title);
	}

	private void setListener() {
		findViewById(R.id.shopping_fantasy).setOnClickListener(this);
		findViewById(R.id.shopping_upcoming).setOnClickListener(this);
		findViewById(R.id.shopping_winner).setOnClickListener(this);
		findViewById(R.id.shopping_cart).setOnClickListener(this);
		findViewById(R.id.user_setting).setOnClickListener(this);
		findViewById(R.id.btn_category).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		onClickViewId(v.getId());
	}

	private void onClickViewId(final int id) {
		switch (id) {
		case R.id.shopping_upcoming:
			switchFragment(mFragments[1]);
			mCurrentRadioId = id;
			mCategoryImg.setVisibility(View.GONE);
			mTitleTextView.setText(R.string.shopping_upcoming);
			mTitleRelativeLayout.setVisibility(View.VISIBLE);
			return;
		case R.id.shopping_winner:
			switchFragment(mFragments[2]);
			mCurrentRadioId = id;
			mCategoryImg.setVisibility(View.GONE);
			mTitleTextView.setText(R.string.winners);
			mTitleRelativeLayout.setVisibility(View.VISIBLE);
			return;
		case R.id.shopping_fantasy:
			switchFragment(mFragments[0]);
			mCurrentRadioId = id;
			mCategoryImg.setVisibility(View.VISIBLE);
			mTitleTextView.setText(R.string.fantasy_title);
			mTitleRelativeLayout.setVisibility(View.VISIBLE);
			return;
		case R.id.shopping_cart:
			switchFragment(mFragments[3]);
			mCurrentRadioId = id;
			mCategoryImg.setVisibility(View.GONE);
			mTitleTextView.setText(R.string.cart_title);
			mTitleRelativeLayout.setVisibility(View.VISIBLE);
			return;
		case R.id.user_setting:
			switchFragment(mFragments[4]);
			mCurrentRadioId = id;
			mTitleRelativeLayout.setVisibility(View.GONE);
			return;
		
		case R.id.btn_category:
			
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
