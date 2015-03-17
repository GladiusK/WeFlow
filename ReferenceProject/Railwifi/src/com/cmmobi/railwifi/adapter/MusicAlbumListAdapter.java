package com.cmmobi.railwifi.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.cmmobi.railwifi.R;
import com.cmmobi.railwifi.music.MusicService;
import com.cmmobi.railwifi.network.GsonResponseObject.LineInfo;
import com.cmmobi.railwifi.network.GsonResponseObject.MusicElem;
import com.cmmobi.railwifi.utils.DisplayUtil;
import com.nostra13.universalimageloader.api.BlurBitmapDisplayer;
import com.nostra13.universalimageloader.api.MyImageLoader;
import com.nostra13.universalimageloader.api.BlurBitmapDisplayer.BlurType;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
public class MusicAlbumListAdapter extends BaseAdapter {

	private Activity context;
	private LayoutInflater inflater;
	private List<MusicElem> musicList = new ArrayList<MusicElem>();
	
	public MusicAlbumListAdapter(final Activity context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return musicList.size();
	}

	@Override
	public MusicElem getItem(int position) {
		// TODO Auto-generated method stub
		return musicList.get(position);
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
					R.layout.activity_music_album_item, null);
			holder = new ViewHolder();			
			holder.rlContent = (RelativeLayout) convertView.findViewById(R.id.rl_music_album_item);
			AbsListView.LayoutParams apm = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, DisplayUtil.getSize(context, 111));
			holder.rlContent.setLayoutParams(apm);
			
			holder.tvIsPlaying = (TextView) convertView.findViewById(R.id.tv_is_playing);
			
			holder.tvIndex = (TextView) convertView.findViewById(R.id.tv_index);
			
			
			holder.tvIndex.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD_ITALIC);
			RelativeLayout.LayoutParams pm = (RelativeLayout.LayoutParams) holder.tvIndex.getLayoutParams();
			pm.leftMargin = DisplayUtil.getSize(context, 27);
			pm.rightMargin = DisplayUtil.getSize(context, 15);
			holder.tvIndex.setLayoutParams(pm);
			holder.tvIndex.setTextSize(DisplayUtil.textGetSizeSp(context, 36));
			
			holder.tvSong = (TextView) convertView.findViewById(R.id.tv_song);
			holder.tvSong.setTextSize(DisplayUtil.textGetSizeSp(context, 36));
			holder.tvSinger = (TextView) convertView.findViewById(R.id.tv_singer);
			holder.tvSinger.setTextSize(DisplayUtil.textGetSizeSp(context, 30));
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		DecimalFormat df = new DecimalFormat("#00");
		holder.tvIndex.setText(df.format(position+1) + " ");
		holder.tvSong.setText(musicList.get(position).name);
		holder.tvSinger.setText(musicList.get(position).content);
		if(!TextUtils.isEmpty(musicList.get(position).name) && musicList.get(position).name.equals(MusicService.getInstance().curSongName())){
			holder.tvIsPlaying.setVisibility(View.VISIBLE);
		}else{
			holder.tvIsPlaying.setVisibility(View.GONE);
		}
		return convertView;
	}
	
	public void setData(MusicElem[] list){
		musicList.clear();
		for(int i=0; i< list.length; i++){
			musicList.add(list[i]);
		}
		notifyDataSetChanged();
	}
	
	public void setData(List<MusicElem> list){
		musicList.clear();
		musicList.addAll(list);
		notifyDataSetChanged();
	}
		
	public class ViewHolder {
		TextView tvIsPlaying;
		TextView tvIndex;
		TextView tvSong;
		TextView tvSinger;
		RelativeLayout rlContent;
	}

	
}