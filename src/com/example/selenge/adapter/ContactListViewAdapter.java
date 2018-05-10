package com.example.selenge.adapter;

import java.util.ArrayList;

import com.example.selenge.R;
import com.example.selenge.model.ContactModel;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ViewHolder")
public class ContactListViewAdapter extends BaseAdapter {
	
	private ArrayList<ContactModel> contactList;
	private Context context;
//	private Typeface tf1; 
	
	public ContactListViewAdapter(Context context, ArrayList<ContactModel> contactList) {
		this.context = context;
//		this.tf1 = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-CondBold.ttf");
		this.contactList = new ArrayList<ContactModel>();
		this.contactList.addAll(contactList);
	}
	
	public void add(ContactModel contactModel){
//		Log.v("AddView", country.getCode());
		this.contactList.add(contactModel);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.contactList.size();
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

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		if (arg1 == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			arg1 = inflater.inflate(R.layout.item_of_contact_listview, arg2, false);
			holder = new ViewHolder();

			holder.imageIv = (ImageView) arg1.findViewById(R.id.contactIv);
			holder.titleTv = (TextView) arg1.findViewById(R.id.contactTv);
			arg1.setTag(holder);

		} 
		else {
			holder = (ViewHolder) arg1.getTag();
		}
		ContactModel contactModel = this.contactList.get(arg0);
		holder.imageIv.setBackgroundResource(contactModel.getImage());	
		holder.titleTv.setText(String.valueOf(contactModel.getTitle()));
		return arg1;
	}
	
	private class ViewHolder {
		ImageView imageIv;
		TextView titleTv;
	}
	 
}
