package com.example.selenge;
import java.util.ArrayList;

import com.example.selenge.adapter.ContactListViewAdapter;
import com.example.selenge.model.ContactModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ContactFragment extends Fragment implements OnClickListener, OnItemClickListener {

	private MapView mapView;
	private GoogleMap map;
	private ListView contactLv;
	private ContactListViewAdapter contactLvAdapter;
	private ArrayList<ContactModel> contactList = null;
	private String number = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

		mapView = (MapView) rootView.findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);

		map = mapView.getMap();
		map.getUiSettings().setMyLocationButtonEnabled(false);
		map.setMyLocationEnabled(true);
		MapsInitializer.initialize(this.getActivity());

		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(49.22, 106.05), 10);
		map.animateCamera(cameraUpdate);

		contactLv = (ListView) rootView.findViewById(R.id.contactLv);
		contactList = new ArrayList<ContactModel>();
		ContactModel infoModel = new ContactModel("АЗДТТ-н жижүүр : 7036-2171", R.drawable.ic_default);
		contactList.add(infoModel);
		infoModel = new ContactModel("АЗДТТ-н даргийн нарийн бичиг : 7036-3849", R.drawable.ic_menu6);
		contactList.add(infoModel);
		infoModel = new ContactModel("Аймгийн засаг даргын нарийн бичиг : 7036-2139", R.drawable.ic_default);
		contactList.add(infoModel);
		infoModel = new ContactModel("azd@selenge.gov.mn", R.drawable.ic_menu5);
		contactList.add(infoModel);
		infoModel = new ContactModel("Сэлэнгэ аймаг", R.drawable.ic_menu7);
		contactList.add(infoModel);
		contactLvAdapter = new ContactListViewAdapter(getActivity(), contactList);
		contactLv.setAdapter(contactLvAdapter);
		contactLv.setOnItemClickListener(this);
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mapView.onPause();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		String message = "Та "; 
		switch(position) {
		case 0:
			number = "7036-2171";
			break;
		case 1:
			number = "7036-3849";
			break;
		case 2:
			number = "7036-2139";
			break;
		default:
				return;
		}
		message += number + " дугаарлуу залгах уу?";
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(message).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + number));
				startActivity(callIntent);
			}
		}).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		builder.show();
	}

}
