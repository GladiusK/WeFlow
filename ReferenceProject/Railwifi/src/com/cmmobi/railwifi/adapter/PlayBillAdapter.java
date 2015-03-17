package com.cmmobi.railwifi.adapter;

import java.util.ArrayList;

import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.fragment.PlayBillFragment;
import com.imbryk.viewPager.LoopViewPager;
import com.nostra13.universalimageloader.api.MyImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayBillAdapter extends FragmentPagerAdapter {

	public PlayBillAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		imageLoader = MyImageLoader.getInstance();

		imageLoaderOptions = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.bitmapConfig(Bitmap.Config.RGB_565)
//				.displayer(new SimpleBitmapDisplayer())
				// .displayer(new CircularBitmapDisplayer()) 圆形图片
				.displayer(new RoundedBitmapDisplayer(20))// 圆角图片
				.build();
	}

	ArrayList<String> playBillImageSites = new ArrayList<String>();
	Activity act;
	MyImageLoader imageLoader = null;
	DisplayImageOptions imageLoaderOptions = null;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return playBillImageSites.size();
	}
	
	public void setList(ArrayList<String> list) {
		playBillImageSites = list;
	}
	

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		position = LoopViewPager.toRealPosition(position, getCount());
		return PlayBillFragment.newInstance(playBillImageSites.get(position), "********");
	}

}
