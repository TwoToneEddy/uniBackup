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

public class DoWorkouts extends Activity implements DoWorkoutsListFragment.OnItemSelectedListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_do_workouts);
	}
	
	@Override
    public void onRssItemSelected(String link) {
      DoWorkoutsDetailFragment fragment = (DoWorkoutsDetailFragment) getFragmentManager()
              .findFragmentById(R.id.doWorkoutsDetailFrag);
          if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
            //Log.d("mydebug","detailFragment is in layout (not portrait)");
            
          } else {
        	 //Log.d("mydebug","detailFragment is not in layout (portrait)");
              Intent intent = new Intent(getApplicationContext(),
                      doWorkoutsDetailActivity.class);
                  intent.putExtra(DoWorkoutsDetailActivity.EXTRA_URL, link);
                  startActivity(intent);

                }
    }

}
