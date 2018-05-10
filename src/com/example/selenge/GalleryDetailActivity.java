package com.example.selenge;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import uk.co.senab.photoview.PhotoView;
import android.view.ViewGroup.LayoutParams;

public class GalleryDetailActivity extends Activity implements OnClickListener {

	private ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_gallery);
		this.init();
	}

	private void init() {
		mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
		List<String> url = getIntent().getStringArrayListExtra("url");
		mViewPager.setAdapter(new SamplePagerAdapter(url));

		ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		TextView titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/AGFuturaMon.ttf"));
		titleTv.setText(R.string.detailed);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		default:
			break;
		}
	}

	static class SamplePagerAdapter extends PagerAdapter {

		private List<String> url;

		public SamplePagerAdapter(List<String> url) {
			this.url = url;
		}

		@Override
		public int getCount() {
			return url.size();
		}

		@SuppressWarnings("deprecation")
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			PhotoView photoView = new PhotoView(container.getContext());
			Picasso.with(container.getContext()).load(url.get(position)).placeholder(container.getContext().getResources().getDrawable(R.drawable.no_photo)).error(container.getContext().getResources().getDrawable(R.drawable.no_photo)).into(photoView);

			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

			return photoView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

	}

}


