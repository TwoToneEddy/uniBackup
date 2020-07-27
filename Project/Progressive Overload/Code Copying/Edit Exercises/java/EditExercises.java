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

public class EditExercises extends Activity implements EditExercisesListFragment.OnItemSelectedListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_exercises);
	}
	
	@Override
    public void onRssItemSelected(String link) {
      EditExercisesDetailFragment fragment = (EditExercisesDetailFragment) getFragmentManager()
              .findFragmentById(R.id.EditExercisesDetailFrag);
          if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
            //Log.d("mydebug","detailFragment is in layout (not portrait)");
            
          } else {
        	 //Log.d("mydebug","detailFragment is not in layout (portrait)");
              Intent intent = new Intent(getApplicationContext(),
                      EditExercisesDetailActivity.class);
                  intent.putExtra(EditExercisesDetailActivity.EXTRA_URL, link);
                  startActivity(intent);

                }
    }

}
