package com.example.progressiveoverload;

import com.example.progressiveoverload.R;
import com.example.progressiveoverload.R.id;
import com.example.progressiveoverload.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;

public class EditExercisesDetailActivity extends Activity {

	 public static final String EXTRA_URL = "url";

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Need to check if Activity has been switched to landscape mode
	    // If yes, finished and go back to the start Activity
	    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
	      finish();
	      return;
	    }
	    setContentView(R.layout.activity_edit_exercises_detail);
	    Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	     String url = extras.getString(EXTRA_URL);
	      EditExercisesDetailFragment exerciseDetailFragment = (EditExercisesDetailFragment) getFragmentManager()
	          .findFragmentById(R.id.editExercisesDetailFrag);
	      exerciseDetailFragment.setText(url);
	    }
	  }
}
