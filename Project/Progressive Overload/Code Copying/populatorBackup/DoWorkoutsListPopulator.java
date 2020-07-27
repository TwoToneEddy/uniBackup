package com.example.progressiveoverload.DoWorkouts;

import com.example.progressiveoverload.R;
import com.example.progressiveoverload.Adapters.CustomExerciseListAdapter;
import com.example.progressiveoverload.Adapters.CustomWorkoutListAdapter;
import com.example.progressiveoverload.ContentProviders.*;
import com.example.progressiveoverload.Utilities.BitmapHandler;
import com.example.progressiveoverload.Utilities.CursorSelector;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.*;
import android.text.format.*;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class DoWorkoutsListPopulator {
	
	ContentResolver myContentResolver;
	Activity hostActivity;
	CustomExerciseListAdapter exerciseListAdapter;
	CustomWorkoutListAdapter workoutListAdapter;
	BitmapHandler bitmapHandler;
	
	public DoWorkoutsListPopulator(Activity hostActivity){
		myContentResolver = hostActivity.getContentResolver();
		this.hostActivity=hostActivity;
		bitmapHandler = new BitmapHandler();
		
	}
	
	public ListView populateExerciseList(int selectedWorkoutID){
		
		String selection = SchedulerContentProvider.KEY_WORKOUT_ID +" == ? ";
	    String[] selectionArgs = new String[] {Integer.toString(selectedWorkoutID)};
	    String[] colomnNames ={"_id",ExerciseContentProvider.KEY_EXERCISE_NAME};
	    Cursor selectionCursor = myContentResolver.query(SchedulerContentProvider.CONTENT_URI,null, selection, selectionArgs, SchedulerContentProvider.KEY_WORKOUT_ID+" DESC");
	    
	    CursorSelector exerciseCursorSelector = new CursorSelector(hostActivity,selectionCursor,ExerciseContentProvider.CONTENT_URI,colomnNames);
		exerciseCursorSelector.setColomns(SchedulerContentProvider.KEY_EXERCISE_ID);
	    MatrixCursor matrixCursor = exerciseCursorSelector.generateMatrixCursor();

	    exerciseListAdapter = new CustomExerciseListAdapter(hostActivity,matrixCursor);
		ListView exerciseListView = (ListView) hostActivity.findViewById(R.id.exerciseList);
		if(exerciseListView != null){
			  exerciseListView.setAdapter(exerciseListAdapter);
			  return exerciseListView;
		}else
			return null;
		
	}
	
	public void populateTextFields(int selectedExerciseID){
		
		ImageView photoIcon = (ImageView) hostActivity.findViewById(R.id.exercisePhoto);
		TextView previousDate = (TextView) hostActivity.findViewById(R.id.lastPerformedField);
		if(previousDate==null)
			return;
		//Create arraylists of all textviews to allow easy access later
		int[] preRepsTextID = {R.id.preRepsText1,R.id.preRepsText2,R.id.preRepsText3,R.id.preRepsText4,R.id.preRepsText5};
		ArrayList<TextView> preRepsTextViews = new ArrayList<TextView>();
		int[] preWeightTextID = {R.id.preWeightText1,R.id.preWeightText2,R.id.preWeightText3,R.id.preWeightText4,R.id.preWeightText5};
		ArrayList<TextView> preWeightTextViews = new ArrayList<TextView>();
		
		for(int id: preRepsTextID){
			TextView textView = (TextView) hostActivity.findViewById(id);
			if(textView != null)
				preRepsTextViews.add(textView);
		}
		for(int id: preWeightTextID){
			TextView textView = (TextView) hostActivity.findViewById(id);
			if(textView != null)
				preWeightTextViews.add(textView);
		}
		
				
		if(selectedExerciseID < 0){
			if(photoIcon != null)
				photoIcon.setImageResource(R.drawable.ic_launcher);
			
			for(TextView id: preRepsTextViews){
				if(id != null)
					id.setText("");
			}
			for(TextView id: preWeightTextViews){
				if(id != null)
					id.setText("");
			}
			previousDate.setText("");
		}else{
			setImageView(photoIcon, selectedExerciseID);
			populatePreviousResults(selectedExerciseID,previousDate,preRepsTextViews,preWeightTextViews);
			
			
		}
		
	}
	
	public void setImageView(ImageView photoIcon, int selectedExerciseID){
		if(photoIcon == null)
			return;
		int titleCol,restCol,uriCol;
		String selection = ExerciseContentProvider.KEY_EXERCISE_ID +" == ? ";
	    String[] selectionArgs = new String[] {Integer.toString(selectedExerciseID)};
	    Cursor selectionCursor = myContentResolver.query(ExerciseContentProvider.CONTENT_URI,null, selection, selectionArgs, null);
	    
	    titleCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.KEY_EXERCISE_NAME);
	    restCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.KEY_REST_PERIOD);
	    uriCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.IMAGE_URI);
	    if(selectionCursor.moveToNext()){
			String imageUriString = selectionCursor.getString(uriCol);
			
			if(imageUriString != null){

				Uri photoUri = Uri.parse(selectionCursor.getString(uriCol));
				Bitmap bitmap = bitmapHandler.generateBitmap(hostActivity, photoUri,R.integer.edit_exercise_photo_size,R.integer.edit_exercise_photo_size);
				if(photoIcon != null){
					photoIcon.setImageBitmap(bitmap);
				}
				
			}else{
				photoIcon.setImageResource(R.drawable.ic_launcher);
				
			}
		}
		
	}
	
	public void populatePreviousResults(int selectedExerciseID,TextView previousDate,ArrayList<TextView> preRepsTextViews,ArrayList<TextView> preWeightTextViews){
		
		String selection = ExerciseRecordContentProvider.KEY_EXERCISE_ID +" == ? ";
	    String[] selectionArgs = new String[] {Integer.toString(selectedExerciseID)};
	    Cursor selectionCursor = myContentResolver.query(ExerciseRecordContentProvider.CONTENT_URI,null, selection, selectionArgs, ExerciseRecordContentProvider.KEY_DATE + " DESC");
	   
	    String[] preRepsColTitle = {ExerciseRecordContentProvider.KEY_SET_A,ExerciseRecordContentProvider.KEY_SET_B,
	    							ExerciseRecordContentProvider.KEY_SET_C,ExerciseRecordContentProvider.KEY_SET_D,
	    							ExerciseRecordContentProvider.KEY_SET_E,};
	    String[] preWeightColTitle = {ExerciseRecordContentProvider.KEY_WEIGHT_A,ExerciseRecordContentProvider.KEY_WEIGHT_B,
									ExerciseRecordContentProvider.KEY_WEIGHT_C,ExerciseRecordContentProvider.KEY_WEIGHT_D,
									ExerciseRecordContentProvider.KEY_WEIGHT_E,};
	    
	    int[] preRepsCols  = new int[preRepsTextViews.size()];
	    int[] preWeightCols = new int[preWeightTextViews.size()];
	    int dateCol;
	    
	    for(int i = 0; i < preRepsColTitle.length; i++ ){
	    	preRepsCols[i] = selectionCursor.getColumnIndexOrThrow(preRepsColTitle[i]);
	    	preWeightCols[i] = selectionCursor.getColumnIndexOrThrow(preWeightColTitle[i]);
	    }
	    dateCol = selectionCursor.getColumnIndexOrThrow(ExerciseRecordContentProvider.KEY_DATE);
	    //Check this exercise has been performed before
	    if(selectionCursor.getCount() > 0){
	    	if(selectionCursor.moveToFirst()){
	    		for(int j = 0; j < preRepsTextViews.size(); j++ ){
	    			preRepsTextViews.get(j).setText(selectionCursor.getString(preRepsCols[j]));
	    			preWeightTextViews.get(j).setText(selectionCursor.getString(preWeightCols[j])+"kgs");
	    		}
	    		previousDate.setText(selectionCursor.getString(dateCol));
	    	}
	    }else{
	    	for(TextView id: preRepsTextViews){
				id.setText("");
			}
			for(TextView id: preWeightTextViews){
				id.setText("");
			}
			previousDate.setText("");
	    	return;
	    }
	}
	
	public void saveRecordToDB(int selectedExerciseID,List<Spinner> repsSpinners, List<Spinner> weightSpinners){
		
		ContentValues values = new ContentValues();
		values.put(ExerciseRecordContentProvider.KEY_EXERCISE_ID, selectedExerciseID);
		values.put(ExerciseRecordContentProvider.KEY_DATE, getDateTime());
		
		values.put(ExerciseRecordContentProvider.KEY_SET_A, stripNonDigits(repsSpinners.get(0).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_SET_B, stripNonDigits(repsSpinners.get(1).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_SET_C, stripNonDigits(repsSpinners.get(2).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_SET_D, stripNonDigits(repsSpinners.get(3).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_SET_E, stripNonDigits(repsSpinners.get(4).getSelectedItem().toString()));
		
		values.put(ExerciseRecordContentProvider.KEY_WEIGHT_A, stripNonDigits(weightSpinners.get(0).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_WEIGHT_B, stripNonDigits(weightSpinners.get(1).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_WEIGHT_C, stripNonDigits(weightSpinners.get(2).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_WEIGHT_D, stripNonDigits(weightSpinners.get(3).getSelectedItem().toString()));
		values.put(ExerciseRecordContentProvider.KEY_WEIGHT_E, stripNonDigits(weightSpinners.get(4).getSelectedItem().toString()));
		
		myContentResolver.insert(ExerciseRecordContentProvider.CONTENT_URI, values);
		
		populateTextFields(selectedExerciseID);
		
	}
	
	public static String stripNonDigits(final CharSequence input){
	    final StringBuilder sb = new StringBuilder(input.length());
	    for(int i = 0; i < input.length(); i++){
	        final char c = input.charAt(i);
	        if(c > 47 && c < 58){
	            sb.append(c);
	        }
	    }
	    return sb.toString();
	}
	
	public String getDateTime(){
		
		Time now = new Time();
		now.setToNow();
		int year,month,day,hour,min,sec;
		String yearS,monthS,dayS,hourS,minS,secS;
		
		year = now.year;
		month = (now.month)+1;
		day = now.monthDay;
		hour = now.hour;
		min = now.minute;
		sec = now.second;	
		
		yearS = Integer.toString(year);
		
		if(month < 10)
			monthS = "0"+Integer.toString(month);
		else
			monthS = Integer.toString(month);
		
		if(day < 10)
			dayS = "0"+Integer.toString(day);
		else
			dayS = Integer.toString(day);
		
		if(hour < 10)
			hourS = "0"+Integer.toString(hour);
		else
			hourS = Integer.toString(hour);
		
		if(min < 10)
			minS = "0"+Integer.toString(min);
		else
			minS = Integer.toString(min);
		
		if(sec < 10)
			secS = "0"+Integer.toString(sec);
		else
			secS = Integer.toString(sec);
		
		return ""+yearS+"-"+monthS+"-"+dayS+" "+hourS+":"+minS+":"+secS;
		
	}
	
	public ArrayList<Spinner> setSpinners(int[] repsSpinnerIds,int values){
		ArrayList<Spinner> spinnerArrayList;
		spinnerArrayList = new ArrayList<Spinner>();
		
		for(int id : repsSpinnerIds) {
	        Spinner spinner = (Spinner)hostActivity.findViewById(id);
	        if(spinner != null){
		        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(hostActivity,
		        		values, android.R.layout.simple_spinner_item);
		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spinner.setAdapter(adapter);
		        
		        spinnerArrayList.add(spinner);
	        }
	    }
		return spinnerArrayList;
		
	}

}