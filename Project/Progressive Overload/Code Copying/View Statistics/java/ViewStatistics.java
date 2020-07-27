package com.example.progressiveoverload;




import com.example.progressiveoverload.R;
import com.example.progressiveoverload.R.id;
import com.example.progressiveoverload.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class ViewStatistics extends Activity implements ViewStatisticsListFragment.OnItemSelectedListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_statistics);
	}
	
	@Override
    public void onRssItemSelected(String link) {
      ViewStatisticsDetailFragment fragment = (ViewStatisticsDetailFragment) getFragmentManager()
              .findFragmentById(R.id.viewStatisticsDetailFrag);
          if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
            //Log.d("mydebug","detailFragment is in layout (not portrait)");
            
          } else {
        	 //Log.d("mydebug","detailFragment is not in layout (portrait)");
              Intent intent = new Intent(getApplicationContext(),
                      ViewStatisticsDetailActivity.class);
                  intent.putExtra(ViewStatisticsDetailActivity.EXTRA_URL, link);
                  startActivity(intent);

                }
    }

}
