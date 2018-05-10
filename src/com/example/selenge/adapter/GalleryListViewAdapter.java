package com.example.selenge.adapter;

import java.util.ArrayList;
import com.example.selenge.R;
import com.example.selenge.model.GalleryModel;
import com.squareup.picasso.Picasso;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class GalleryListViewAdapter extends BaseAdapter {

	private ArrayList<GalleryModel> galleryList;
	private Context context;
	//	private Typeface tf1; 

	public GalleryListViewAdapter(Context context, ArrayList<GalleryModel> galleryList) {
		this.context = context;
		this.galleryList = new ArrayList<GalleryModel>();
		this.galleryList.addAll(galleryList);
	}
	
	public void add(GalleryModel galleryModel){
		this.galleryList.add(galleryModel);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.galleryList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (arg1 == null) {
			LayoutInflater  inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			arg1 = inflater.inflate(R.layout.item_of_gallery_listview, arg2, false);
			holder = new ViewHolder();

			holder.thumbIv = (ImageView) arg1.findViewById(R.id.thumbIv);
			holder.titleTv = (TextView) arg1.findViewById(R.id.titleTv);	
			arg1.setTag(holder);
		}
		else {
			holder = (ViewHolder) arg1.getTag();
		}
		GalleryModel galleryModel = this.galleryList.get(arg0);
		Picasso.with(context).load(galleryModel.getImage()).placeholder(context.getResources().getDrawable(R.drawable.no_photo)).error(context.getResources().getDrawable(R.drawable.no_photo)).into(holder.thumbIv);
		holder.titleTv.setText(String.valueOf(galleryModel.getTitle()));

		//        numberTv.setTypeface(tf1);
		return arg1;
	}

	private class ViewHolder {
		ImageView thumbIv;
		TextView titleTv;
	}
}
