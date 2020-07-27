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

public class EditWorkouts extends Activity implements EditWorkoutsListFragment.OnItemSelectedListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_workouts);
	}
	
	@Override
    public void onRssItemSelected(String link) {
      EditWorkoutsDetailFragment fragment = (EditWorkoutsDetailFragment) getFragmentManager()
              .findFragmentById(R.id.editWorkoutsDetailFrag);
          if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
            //Log.d("mydebug","detailFragment is in layout (not portrait)");
            
          } else {
        	 //Log.d("mydebug","detailFragment is not in layout (portrait)");
              Intent intent = new Intent(getApplicationContext(),
                      EditWorkoutsDetailActivity.class);
                  intent.putExtra(EditWorkoutsDetailActivity.EXTRA_URL, link);
                  startActivity(intent);

                }
    }

}
