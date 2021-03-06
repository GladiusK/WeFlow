package com.cmmobi.railwifi.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.trinea.android.common.util.ViewUtils;

import com.cmmobi.railwifi.Config;
import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.activity.CmmobiVideoPlayer;
import com.cmmobi.railwifi.activity.TitleRootActivity;
import com.cmmobi.railwifi.activity.VideoPlayerActivity;
import com.cmmobi.railwifi.activity.MovieDetailActivity;
import com.cmmobi.railwifi.dao.PlayHistory;
import com.cmmobi.railwifi.network.GsonResponseObject;
import com.cmmobi.railwifi.sql.FavManager;
import com.cmmobi.railwifi.sql.HistoryManager;
import com.cmmobi.railwifi.sql.SqlConvertor;
import com.cmmobi.railwifi.utils.CmmobiClickAgentWrapper;
import com.cmmobi.railwifi.utils.ConStant;
import com.cmmobi.railwifi.utils.DisplayUtil;
import com.cmmobi.railwifi.utils.DownloadApkUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.api.AnimateFirstDisplayListener;
import com.nostra13.universalimageloader.api.FadeInAnimateDisplayListener;
import com.nostra13.universalimageloader.api.MyImageLoader;
import com.nostra13.universalimageloader.api.RoundedBorderBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.simope.yzvideo.base.MobilePlayActivity;

public class MoviesListAdapter extends BaseAdapter {

//	public static final int TAG_KEY_MEDIA_ID = 0x133341;
//	public static final int TAG_KEY_SOURCE_ID = 0x133342;
//	public static final int TAG_KEY_SOURCE_NAME = 0x133343;
//	public static final int TAG_KEY_MEDIA_ITEM = 0x133344;
	
	private Context context;
	private LayoutInflater inflater;
	private List<GsonResponseObject.mediaElem> list;// = new ArrayList<GsonResponseObject.mediaElem>();
	private int size = 0;
	
	//ʹ�ÿ�Դ��webimageloader
	public static DisplayImageOptions options;
	protected MyImageLoader imageLoader;
	private ImageLoadingListener animateFirstListener;
	
	public MoviesListAdapter(final Context context) {
		this.context = context;
		DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
		this.size = dm.widthPixels/5;
		inflater = LayoutInflater.from(context);
		
		animateFirstListener = new AnimateFirstDisplayListener();
//		animateFirstListener = new FadeInAnimateDisplayListener(2000);
		imageLoader = MyImageLoader.getInstance();
		
		options = new DisplayImageOptions.Builder()
		.cacheOnDisk(true)
		.showImageOnFail(R.drawable.shape_pic_list)
		.cacheInMemory(true)
		.displayer(new RoundedBorderBitmapDisplayer(20, 0xfff65c00, 3))// Բ��ͼƬ
//		.displayer(new RoundedBitmapDisplayer(10))// Բ��ͼƬ
		.build();
		
		list = new ArrayList<GsonResponseObject.mediaElem>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public GsonResponseObject.mediaElem getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.list_movies_item, null);
			holder = new ViewHolder();
			
			holder.rl_whole = (RelativeLayout) convertView.findViewById(R.id.rl_whole);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_more = (TextView) convertView.findViewById(R.id.tv_more);
			holder.iv_movie_pic = (ImageView) convertView.findViewById(R.id.movie_pic);
			holder.tv_tag =  (TextView) convertView.findViewById(R.id.tag);
			holder.tv_score = (TextView) convertView.findViewById(R.id.tv_score);
			holder.tv_source = (TextView) convertView.findViewById(R.id.tv_source_name);
			holder.tv_descrp = (TextView) convertView.findViewById(R.id.tv_descrp);
			holder.tv_actor = (TextView) convertView.findViewById(R.id.tv_actor_name);
			holder.tv_director = (TextView) convertView.findViewById(R.id.tv_director_name);
			holder.btn_play = (ImageButton) convertView.findViewById(R.id.btn_play);
			holder.iv_fav_pic = (Button) convertView.findViewById(R.id.iv_fav);
			
			com.cmmobi.railwifi.utils.ViewUtils.setSize(holder.iv_movie_pic, 266, 348);
			com.cmmobi.railwifi.utils.ViewUtils.setSize(holder.btn_play, 260, 342);
			
			holder.tv_score.setTextSize(DisplayUtil.textGetSizeSp(context, 24));
			holder.tv_descrp.setTextSize(DisplayUtil.textGetSizeSp(context, 24));
			holder.tv_actor.setTextSize(DisplayUtil.textGetSizeSp(context, 24));
			((TextView) convertView.findViewById(R.id.tv_actor_title)).setTextSize(DisplayUtil.textGetSizeSp(context, 24));
			((TextView) convertView.findViewById(R.id.tv_more)).setTextSize(DisplayUtil.textGetSizeSp(context, 26));
			holder.tv_title.setTextSize(DisplayUtil.textGetSizeSp(context, 36));
			holder.tv_tag.setTextSize(DisplayUtil.textGetSizeSp(context, 26));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(FavManager.getInstance().hasFavItem(list.get(position).media_id)){
			holder.iv_fav_pic.setBackgroundResource(R.drawable.btn_fav_selected);
		}else{
			holder.iv_fav_pic.setBackgroundResource(R.drawable.btn_fav_normal);
		}
		

		holder.tv_title.setText(list.get(position).name);
		holder.tv_score.setText(list.get(position).score);
		holder.tv_actor.setText(list.get(position).actors);
		holder.tv_director.setText(list.get(position).director);
		holder.tv_source.setText("来源：" + list.get(position).source);
		holder.tv_descrp.setText(list.get(position).introduction);
		
		holder.tv_source.setOnClickListener((TitleRootActivity)context);
		holder.tv_source.setTag(R.string.key_media_id, list.get(position).media_id);
		holder.tv_source.setTag(R.string.key_source_id, list.get(position).source_id);
		holder.tv_source.setTag(R.string.key_source_name, list.get(position).source);
		
		imageLoader.displayImage(list.get(position).img_path, holder.iv_movie_pic, options, animateFirstListener);
		
		if(list.get(position).tag!=null && !list.get(position).tag.equals("")){
			String color = list.get(position).color;
			holder.tv_tag.setVisibility(View.VISIBLE);
			holder.tv_tag.setText(list.get(position).tag);

			if(color.equals("1")){
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_a);
			}else if(color.equals("2")){
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_b);
			}else if(color.equals("3")){
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_c);
			}else if(color.equals("4")){
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_d);
			}else if(color.equals("5")){
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_e);
			}else{
				holder.tv_tag.setBackgroundResource(R.drawable.tag_pic_b);
			}
//			holder.tag.setBackgroundColor(context.getResources().getColor(R.color.red));
		}else{
			holder.tv_tag.setVisibility(View.GONE);
		}


		holder.btn_play.setOnClickListener((TitleRootActivity)context);
		holder.btn_play.setTag(R.string.key_media_id, list.get(position).media_id);
		holder.btn_play.setTag(R.string.key_source_id, list.get(position).source_id);
		holder.btn_play.setTag(R.string.key_source_name, list.get(position).source);
		holder.btn_play.setTag(R.string.key_media_object, list.get(position));
		
		holder.tv_more.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MovieDetailActivity.class);
				intent.putExtra(ConStant.INTENT_MEDIA_ID, list.get(position).media_id);
				intent.putExtra(ConStant.INTENT_SOURCE_ID, list.get(position).source_id);
				context.startActivity(intent);
			}
		});
		holder.rl_whole.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, MovieDetailActivity.class);
				intent.putExtra(ConStant.INTENT_MEDIA_ID, list.get(position).media_id);
				intent.putExtra(ConStant.INTENT_SOURCE_ID, list.get(position).source_id);
				context.startActivity(intent);
			}
		});
		
		holder.iv_fav_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CmmobiClickAgentWrapper.onEvent(context, "av_movie_fav", "label", list.get(position).media_id, "label2", list.get(position).source_id);
				GsonResponseObject.mediaElem mItem = list.get(position);
				if(FavManager.getInstance().hasFavItem(list.get(position).media_id)){
					FavManager.getInstance().removeFavItem(mItem.media_id);
					v.setBackgroundResource(R.drawable.btn_fav_normal);
				}else{
					FavManager.getInstance().putFavItem(SqlConvertor.mediaItem2Fav(mItem, GsonResponseObject.MEDIA_TYPE_MOVIE));
					v.setBackgroundResource(R.drawable.btn_fav_selected);
				}
			}
		});
		return convertView;
	}
	
	public void setData(List<GsonResponseObject.mediaElem> data){
		this.list = data;
	}
	
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	
	
	public class ViewHolder {
		RelativeLayout rl_whole;
		TextView tv_title;
		TextView tv_more;
		ImageView iv_movie_pic;
		Button iv_fav_pic;
		TextView tv_tag;
		TextView tv_score; //9.1评分
		TextView tv_actor; //演员
		TextView tv_director; //导演
		TextView tv_source;
		TextView tv_descrp;
		ImageButton btn_play;
	}

	
}