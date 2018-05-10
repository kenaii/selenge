package com.example.selenge;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.selenge.adapter.GalleryListViewAdapter;
import com.example.selenge.model.GalleryModel;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AbsListView.OnScrollListener;

public class GalleryFragment extends Fragment {

	private ListView galleryLv;
	private GalleryListViewAdapter galleryLvAdapter;
	
	private ArrayList<GalleryModel> galleryList = null;
	private ProgressBar progressBar;
	
	private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
		
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
	
		this.galleryList = new ArrayList<GalleryModel>();

		this.galleryLv = (ListView) rootView.findViewById(R.id.galleryLv);
		
		new JSONGalleryTask().execute(currentPage);
		this.galleryLvAdapter = new GalleryListViewAdapter(getActivity(), galleryList);
		this.galleryLv.setAdapter((ListAdapter) galleryLvAdapter);
		
		this.galleryLv.invalidate();
		
		this.galleryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), GalleryDetailActivity.class);
				intent.putStringArrayListExtra("url", galleryList.get(position).getDetailImages());
				startActivity(intent);
			}
		});
		
		this.galleryLv.setOnScrollListener(new OnScrollListener() {

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
					new JSONGalleryTask().execute(currentPage + 1);
					loading = true;
				}
			}
			
		});
		return rootView;
	}

	private class JSONGalleryTask extends AsyncTask<Integer, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Integer... params) {
			String jsonData = ( (new SelengeMobileHttpClient()).getGalleryData(Integer.valueOf(params[0])));
			if (jsonData != null) {
				try {
					JSONArray info = new JSONArray(jsonData);  
					for (int i = 0; i < info.length(); i ++) {
						JSONObject c = info.getJSONObject(i);
						String title = c.getString("title");
						String image = c.getString("thumb_image");
						JSONArray jsondetailImages = c.getJSONArray("detail_images");
						ArrayList<String> detailImages = new ArrayList<String>();
						for(int j = 0; j < jsondetailImages.length(); j ++) {
							detailImages.add(jsondetailImages.getString(j));
						}
						GalleryModel galleryModel = new GalleryModel(title, image, detailImages);
						galleryList.add(galleryModel);
						galleryLvAdapter.add(galleryModel);
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
			galleryLvAdapter.notifyDataSetChanged();
		}

	}
}
