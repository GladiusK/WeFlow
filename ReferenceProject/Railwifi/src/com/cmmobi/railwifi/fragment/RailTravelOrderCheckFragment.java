package com.cmmobi.railwifi.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.mina.filter.reqres.Response;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.activity.RailTravelOrderHistoryAcitivity;
import com.cmmobi.railwifi.adapter.RailTravelDetailListAdapter;
import com.cmmobi.railwifi.adapter.RailTravelOrderHistoryAdapter;
import com.cmmobi.railwifi.dao.DaoMaster;
import com.cmmobi.railwifi.dao.DaoSession;
import com.cmmobi.railwifi.dao.TravelOrderInfo;
import com.cmmobi.railwifi.dao.TravelOrderInfoDao;
import com.cmmobi.railwifi.dao.DaoMaster.DevOpenHelper;
import com.cmmobi.railwifi.dao.TravelOrderInfoDao.Properties;
import com.cmmobi.railwifi.network.GsonResponseObject;
import com.google.gson.Gson;

public class RailTravelOrderCheckFragment extends Fragment implements OnClickListener {

	private View contentView;
	private ListView lvTravelList;
	private RailTravelOrderHistoryAdapter mAdapter;
	private Bundle bundle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		contentView = inflater.inflate(
				R.layout.activity_railtravel_detail_check_two, null);	
		return contentView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		loaddata();
	}
	
	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		if(!hidden){
			loaddata();
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		lvTravelList = (ListView)contentView.findViewById(R.id.lv_travellist);
		bundle = getArguments();	
	}

	private void loaddata(){
		if(bundle != null){
			String ispaid = bundle.getString("ispaid");
			
			DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(), "railwifidb", null);
	        SQLiteDatabase db = helper.getWritableDatabase();
	        DaoMaster daoMaster = new DaoMaster(db);
	        DaoSession daoSession = daoMaster.newSession();
	        TravelOrderInfoDao travelOrderInfoDao = daoSession.getTravelOrderInfoDao();
	        List<TravelOrderInfo> list = travelOrderInfoDao.queryBuilder().where(Properties.Ispaid.eq(ispaid)).orderDesc(Properties.Order_time).list();
	        
			mAdapter = new RailTravelOrderHistoryAdapter((RailTravelOrderHistoryAcitivity)getActivity(), list);
			lvTravelList.setAdapter(mAdapter);
		}
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
	
		default:
			break;
		}
	}


}
