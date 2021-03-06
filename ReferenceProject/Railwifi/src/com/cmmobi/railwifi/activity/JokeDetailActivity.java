package com.cmmobi.railwifi.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.dialog.PromptDialog;
import com.cmmobi.railwifi.dialog.XProcessDialog;
import com.cmmobi.railwifi.network.GsonResponseObject;
import com.cmmobi.railwifi.network.Requester;
import com.cmmobi.railwifi.utils.ConStant;

public class JokeDetailActivity extends TitleRootActivity{
//	TextView tv_joke_title;
//	TextView tv_joke_content;
//	GifView iv_joke_pic;
	WebView webView;
//	XProcessDialog dialog;
	RelativeLayout rlNoNet;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitleText("逗你玩");
		
		rightButton.setVisibility(View.GONE);
		
//		showSmoothProgressBar();
//		dialog = new XProcessDialog(this);
//		dialog.show();
		PromptDialog.showProgressDialog(this);
		
		rlNoNet = (RelativeLayout) findViewById(R.id.rl_no_network);
		webView = (WebView) findViewById(R.id.wv_game);
		webView.getSettings().setJavaScriptEnabled(true);  

		if (Build.VERSION.SDK_INT >= 11){
			webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
		}

		webView.getSettings().setPluginState(PluginState.ON);
		webView.setWebViewClient(new WebViewClient(){       
            public boolean shouldOverrideUrlLoading(WebView view, String url) {       
                view.loadUrl(url);    
                return true;       
                
            }
            
            @Override
            public void onPageFinished(WebView view, String url) {
            	// TODO Auto-generated method stub
            	PromptDialog.dimissProgressDialog();
            	super.onPageFinished(view, url);
            }
            
            @Override
            public void onReceivedError(WebView view, int errorCode,
            		String description, String failingUrl) {
            	rlNoNet.setVisibility(View.VISIBLE);
            	// TODO Auto-generated method stub
            	PromptDialog.dimissProgressDialog();
            	super.onReceivedError(view, errorCode, description, failingUrl);
            }
            
		});
		
		String mediaId = getIntent().getStringExtra(ConStant.INTENT_MEDIA_ID);
		if (mediaId != null) {
			Requester.requestJokeInfo(handler, mediaId);
		} else {
			String srcPath = getIntent().getStringExtra(ConStant.INTENT_MEDIA_SRC_PATH);
			webView.loadUrl(srcPath);
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		webView.onPause();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		webView.onResume();
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		webView.destroy();
		super.onDestroy();
	}

	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case Requester.RESPONSE_TYPE_MEDIA_JOKEINFO:
			if (msg.obj != null) {
				GsonResponseObject.JokDetailInfoResp resp = (GsonResponseObject.JokDetailInfoResp) msg.obj;
				if (resp!=null && "0".equals(resp.status) && resp.src_path!=null && resp.src_path.startsWith("http://")) {
					webView.loadUrl(resp.src_path);
				} else {
//					dialog.dismiss();
					PromptDialog.dimissProgressDialog();
					rlNoNet.setVisibility(View.VISIBLE);
				}
			} else {
//				dialog.dismiss();
				PromptDialog.dimissProgressDialog();
				rlNoNet.setVisibility(View.VISIBLE);
			}
			break;
		}
		return false;
	}

	@Override
	public int subContentViewId() {
		// TODO Auto-generated method stub
		return R.layout.activity_media_joke_detail;
	}
	
	

}
