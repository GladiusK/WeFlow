package com.cmmobi.railwifi.activity;

import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.network.GsonResponseObject;
import com.cmmobi.railwifi.network.Requester;
import com.cmmobi.railwifi.utils.CmmobiClickAgentWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class IntroductionActivity extends TitleRootActivity{
	TextView tv_introdution_title;
	TextView tv_introdution_content;
	private RelativeLayout rlNoNet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitleText("服务简介");
//		setRightButtonText("满意度调查>>");
		hideRightButton();
		
//		rightButton.setVisibility(View.GONE);
		rlNoNet = (RelativeLayout) findViewById(R.id.rl_no_network);
		tv_introdution_title = (TextView)findViewById(R.id.tv_tip_title);
		tv_introdution_content = (TextView)findViewById(R.id.tv_tip_content);
		
		Requester.requestIntro(handler);
		
//		tv_introdution_title.setText(R.string.introdution_title);
//		tv_introdution_content.setText(R.string.introdution_content);
	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch(msg.what){
		case Requester.RESPONSE_TYPE_INTRO:
			GsonResponseObject.commonContent r7 = (GsonResponseObject.commonContent)msg.obj;
			if(r7!=null && "0".equals(r7.status)){
				tv_introdution_title.setText(r7.title);
				tv_introdution_content.setText(r7.content);
				rlNoNet.setVisibility(View.GONE);
			}else{
				rlNoNet.setVisibility(View.VISIBLE);
			}
			break;
		}
		return false;
	}

	/**
	 * 资源文件，复用fragment_tip的
	 * 
	 * */
	@Override
	public int subContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_railservice_tip;
	}
	
	/*@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.btn_title_right:
			CmmobiClickAgentWrapper.onEvent(this, "t_survey");
			Intent intentSatisfaction = new Intent(this, SatisfactionSurveyActivity.class);
			startActivity(intentSatisfaction);
			break;
		}
		super.onClick(v);
	}*/
	
	

}
