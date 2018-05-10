package com.example.selenge;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressWarnings("deprecation")
@SuppressLint("NewApi")
public class SuggestFragment extends Fragment implements OnClickListener, OnItemSelectedListener {

	private Spinner typeSp;
	private String type = "1";
	private EditText nameEt;
	private EditText phoneEt;
	private EditText emailEt;
	private EditText descriptionEt;
	private Button sendBtn;
	private ProgressDialog pDialog;

	public SuggestFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_suggest, container, false);

		this.typeSp = (Spinner) rootView.findViewById(R.id.typeSp);
		this.typeSp.setOnItemSelectedListener(this);
		this.nameEt = (EditText) rootView.findViewById(R.id.nameEt);
		this.phoneEt = (EditText) rootView.findViewById(R.id.phoneEt);
		this.emailEt = (EditText) rootView.findViewById(R.id.emailEt);
		this.descriptionEt = (EditText) rootView.findViewById(R.id.descriptionEt);
		this.sendBtn = (Button) rootView.findViewById(R.id.sendBtn);
		this.sendBtn.setOnClickListener(this);     
		return rootView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.sendBtn:
			if(this.validation()) {
				if (MainActivity.isNetworkAvailable(getActivity())) {
					new SendMailTask().execute(new String[]{nameEt.getText().toString(), phoneEt.getText().toString(), emailEt.getText().toString(), descriptionEt.getText().toString(), this.type});
				}
				else {
					Toast.makeText(getActivity(), R.string.no_network_connection_title, Toast.LENGTH_LONG).show();
				}
			}
			break;
		}
	}

	private Boolean validation() {
		boolean ch1 = true;
		if(this.nameEt.getText().toString().trim().equals("")) {
			this.nameEt.setError("Нэрээ заавал оруулна уу!");
			ch1 = false;
		}
		if(this.phoneEt.getText().toString().trim().equals("")) {
			this.phoneEt.setError("Утсаа заавал оруулна уу!");
			ch1 = false;
		}
		if(this.emailEt.getText().toString().trim().equals("")) {
			this.emailEt.setError("Э-майл заавал оруулна уу!");
			ch1 = false;
		}
		else {
			if(!this.isEmailValid(this.emailEt.getText().toString())) {
				this.emailEt.setError("Таны Э-майл буруу байна!");
				ch1 = false;
			}
		}
		if(this.descriptionEt.getText().toString().trim().equals("")) {
			this.descriptionEt.setError("Агуулгаа заавал оруулна уу!");
			ch1 = false;
		}
		return ch1;
	}

	private boolean isEmailValid(String email) {
		String regExpn =
				"^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
						+"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
						+"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
						+"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
						+"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
						+"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

		CharSequence inputStr = email;

		Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);

		if(matcher.matches())
			return true;
		else
			return false;
	}

	private class SendMailTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Түр хүлээнэ үү...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			String response = "";
			try {
				response = ((new SelengeMobileHttpClient()).sendMail(params[0], params[1], params[2], params[3], params[4]));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);

			if (pDialog.isShowing())
				pDialog.dismiss();
			JSONObject c;
			try {
				c = new JSONObject(result);			
				String message = "";
				if (c.getString("success").equals("1")) {
					message = "Амжилттай илгээлээ!";
				}
				else {
					message = "Амжилтгүй боллоо!";
				}
				Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		this.type = String.valueOf(position + 1);		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
