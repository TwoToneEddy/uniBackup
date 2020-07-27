package com.example.progressiveoverload;

import com.example.progressiveoverload.R;
import com.example.progressiveoverload.R.id;
import com.example.progressiveoverload.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EditWorkoutsDetailFragment extends Fragment {
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = (ViewGroup)inflater.inflate(R.layout.fragment_edit_workouts_detail,
	        container, false);
	    return view;
	  }

	
	  public void setText(String item) {
	    TextView view = (TextView) getView().findViewById(R.id.detailsText);
	    view.setText(item);
	  }
}