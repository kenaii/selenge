package com.example.selenge.adapter;

import java.util.ArrayList;

import com.example.selenge.R;
import com.example.selenge.model.InfoModel;
import com.squareup.picasso.Picasso;

import ir.noghteh.JustifiedTextView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint.Align;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class InfoListViewAdapter extends BaseAdapter {
	
	private ArrayList<InfoModel> infoList;
	private Context context;
//	private Typeface tf1; 
	
	public InfoListViewAdapter(Context context, ArrayList<InfoModel> infoList) {
		this.context = context;
//		this.tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-CondBold.ttf");
		this.infoList = new ArrayList<InfoModel>();
		this.infoList.addAll(infoList);
	}
	
	public void add(InfoModel infoModel){
//		Log.v("AddView", country.getCode());
		this.infoList.add(infoModel);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.infoList.size();
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
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			arg1 = inflater.inflate(R.layout.item_of_info_listview, arg2, false);
			holder = new ViewHolder();

			holder.imageIv = (ImageView) arg1.findViewById(R.id.imageIv);
			holder.titleTv = (TextView) arg1.findViewById(R.id.titleTv);
			holder.descriptionTv = (JustifiedTextView) arg1.findViewById(R.id.descriptionTv);
			holder.dateTv = (TextView) arg1.findViewById(R.id.dateTv);
			arg1.setTag(holder);

		} 
		else {
			holder = (ViewHolder) arg1.getTag();
		}
		InfoModel infoModel = this.infoList.get(arg0);
		Picasso.with(context).load(infoModel.getImage()).placeholder(context.getResources().getDrawable(R.drawable.no_photo)).error(R.drawable.no_photo).into(holder.imageIv);
		holder.titleTv.setText(String.valueOf(infoModel.getTitle()));
		holder.descriptionTv.setText(String.valueOf(infoModel.getDescription()));
		holder.dateTv.setText(String.valueOf(infoModel.getDate()));
        holder.descriptionTv.setAlignment(Align.LEFT);

		//        numberTv.setTypeface(tf1);
		return arg1;
	}
	
	private class ViewHolder {
		ImageView imageIv;
		TextView titleTv;
		JustifiedTextView descriptionTv;
		TextView dateTv;
	}
	 
}
