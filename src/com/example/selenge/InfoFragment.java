package com.example.selenge;

import java.util.ArrayList;
import com.example.selenge.adapter.InfoListViewAdapter;
import com.example.selenge.model.InfoModel;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InfoFragment extends Fragment {

	private ListView infoLv;
	private InfoListViewAdapter infoLvAdapter;
	private ProgressBar progressBar;
	private ArrayList<InfoModel> infoList = null;
	
	private int visibleThreshold = 10;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_info, container, false);
		if (!MainActivity.isNetworkAvailable(getActivity())) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.no_network_connection_title);
			builder.setMessage(R.string.no_network_connection_message)
			       .setCancelable(false)
			       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
			return rootView;
		}
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressbar1);
		
		this.infoList = new ArrayList<InfoModel>();

		this.infoLv = (ListView) rootView.findViewById(R.id.infoLv);
		
		new JSONInfoTask().execute(currentPage);
		this.infoLvAdapter = new InfoListViewAdapter(getActivity(), infoList);
		this.infoLv.setAdapter((ListAdapter) infoLvAdapter);
		
		this.infoLv.invalidate();
		
		this.infoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), InfoDetailActivity.class);
				intent.putExtra("url", infoList.get(position).getUrl());
				startActivity(intent);
			}
		});
		
		this.infoLv.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if (loading) {
					if (totalItemCount > previousTotal) {
						loading = false;
						previousTotal = totalItemCount;
						currentPage ++;
					}
				}
				if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
					new JSONInfoTask().execute(currentPage + 1);
					loading = true;
				}
			}
			
		});
		return rootView;
	}

	private class JSONInfoTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Integer... params) {
			String jsonData = ( (new SelengeMobileHttpClient()).getInfoData(Integer.valueOf(params[0])));
			if (jsonData != null) {
				try {
					JSONArray info = new JSONArray(jsonData);  
					for (int i = 0; i < info.length(); i ++) {
						JSONObject c = info.getJSONObject(i);

						String title = c.getString("title");
						String image = c.getString("thumb_image");
						String description = c.getString("description_text");
						String date = c.getString("date");
						String url = c.getString("url");
						
						InfoModel infoModel = new InfoModel(title, image, description, date, url);
						infoList.add(infoModel);
						infoLvAdapter.add(infoModel);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} 
			else {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {			
			super.onPostExecute(result);
			progressBar.setVisibility(View.GONE);
			infoLvAdapter.notifyDataSetChanged();
		}

	}

}
