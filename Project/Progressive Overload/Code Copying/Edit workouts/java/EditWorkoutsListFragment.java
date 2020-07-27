package com.example.progressiveoverload;



import com.example.progressiveoverload.R;
import com.example.progressiveoverload.R.id;
import com.example.progressiveoverload.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.util.DisplayMetrics;

public class EditWorkoutsListFragment extends Fragment {
	  
	  private OnItemSelectedListener listener;
	  
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
			View view = (ViewGroup)inflater.inflate(R.layout.fragment_edit_workouts_list,container, false);
		
	    
	    //Can't do the usual way as class extends Fragment not activity
	    Button button = (Button) view.findViewById(R.id.button1);
	    button.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	        updateDetail();
	      }
	    });
	    
	    return view;
	  }
	  

	  public interface OnItemSelectedListener {
	      public void onRssItemSelected(String link);
	  }
	  
	  @Override
	    public void onAttach(Activity activity) {
	      super.onAttach(activity);
	      if (activity instanceof OnItemSelectedListener) {
	        listener = (OnItemSelectedListener) activity;
	      } else {
	        throw new ClassCastException(activity.toString()
	            + " must implemenet MyListFragment.OnItemSelectedListener");
	      }
	    }
	  
	//May also be triggered from the Activity
	 public void updateDetail() {
	   // create fake data
	   String newTime = String.valueOf(System.currentTimeMillis());
	   // Send data to Activity
	   listener.onRssItemSelected(newTime);
	 }
	}
