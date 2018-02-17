package com.example.taxitestapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taxitestapp.R;
import com.example.taxitestapp.network.ConnectionHelper;
import com.example.taxitestapp.network.ResponceParser;
import com.example.taxitestapp.service.LocationService;
import com.example.taxitestapp.ui.fragment.BaseFragment;
import com.example.taxitestapp.ui.fragment.EmptyFragment;
import com.example.taxitestapp.ui.fragment.OrderFragment;
import com.example.taxitestapp.ui.fragment.PreOrderFragment;
import com.example.taxitestapp.utils.PermissionHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 15;

    @BindView(R.id.tab_layout)
    TabLayout mTabs;
    @BindView(R.id.vp_content)
    ViewPager mContent;

    private ImageView mOrderImage;
    private ImageView mPreOrderImage;

    private ScreenSlidePagerAdapter mPagerAdapter;

    private OrderFragment mOrderFragment;
    private PreOrderFragment mPreOrderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mContent.setAdapter(mPagerAdapter);
        mTabs.setupWithViewPager(mContent);

        mOrderImage = makeTabIcon(R.drawable.ic_order, 0);
        makeTabTitle(R.string.order, 0);
        mPreOrderImage = makeTabIcon(R.drawable.ic_pre_order, 1);
        makeTabTitle(R.string.pre_order, 1);

        if (PermissionHelper.isLocationPermissionGranted(this)) {
            Intent intent = new Intent(this, LocationService.class);
            startService(intent);
        } else {
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
        }


    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, LocationService.class);
                    startService(intent);
                }
                break;
        }
    }

    private ImageView makeTabIcon(@DrawableRes int icon, int idx) {
        View view = getLayoutInflater().inflate(R.layout.item_tab, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab_icon);
        imageView.setImageResource(icon);
        mTabs.getTabAt(idx).setCustomView(view);
        return imageView;
    }

    private void makeTabTitle(@StringRes int title, int idx) {
        View view = getLayoutInflater().inflate(R.layout.item_tab, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab_title);
        textView.setText(title);
        mTabs.getTabAt(idx).setCustomView(view);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mOrderFragment == null) {
                        mOrderFragment = OrderFragment.newInstance();
                    }
                    return mOrderFragment;
                case 1:
                    if (mPreOrderFragment == null) {
                        mPreOrderFragment = PreOrderFragment.newInstance();
                    }
                    return mPreOrderFragment;
            }
            return EmptyFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
