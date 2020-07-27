package com.example.progressiveoverload.EditExercises;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.progressiveoverload.R;
import com.example.progressiveoverload.Adapters.CustomExerciseListAdapter;
import com.example.progressiveoverload.Adapters.CustomWorkoutListAdapter;
import com.example.progressiveoverload.ContentProviders.ExerciseContentProvider;
import com.example.progressiveoverload.ContentProviders.SchedulerContentProvider;
import com.example.progressiveoverload.ContentProviders.WorkoutContentProvider;
import com.example.progressiveoverload.Utilities.*;
import android.app.Activity;

public class EditExercisesListPopulator {
	ContentResolver myContentResolver;
	Activity hostActivity;
	CustomExerciseListAdapter exerciseListAdapter;
	BitmapHandler bitmapHandler;
	
	public EditExercisesListPopulator(Activity hostActivity){
		myContentResolver = hostActivity.getContentResolver();
		this.hostActivity=hostActivity;
		bitmapHandler = new BitmapHandler();
		
	}
	
public ListView populateExerciseList(){
		
	    Cursor selectionCursor = myContentResolver.query(ExerciseContentProvider.CONTENT_URI,null, null, null, ExerciseContentProvider.KEY_EXERCISE_NAME+" ASC");
	    
	    exerciseListAdapter = new CustomExerciseListAdapter(hostActivity,selectionCursor);
		ListView exerciseListView = (ListView) hostActivity.findViewById(R.id.exerciseList);
		if(exerciseListView != null){
			exerciseListView.setAdapter(exerciseListAdapter);
			return exerciseListView;
		}
		return null;
			
		
	}

	public void populateTextFields(int selectedExerciseID){
		EditText titleTextView = (EditText) hostActivity.findViewById(R.id.titleField);
		EditText restTextView = (EditText) hostActivity.findViewById(R.id.restGroupField);
		ImageView photoIcon = (ImageView) hostActivity.findViewById(R.id.exercisePhoto);
		
		int titleCol,restCol,uriCol;
		
		if(selectedExerciseID < 0){
			//No exercise selected so populate text fields with blanks
			titleTextView.setText("");
			restTextView.setText("");
			photoIcon.setImageResource(R.drawable.ic_launcher);
			
		}else{
			String selection = ExerciseContentProvider.KEY_EXERCISE_ID +" == ? ";
		    String[] selectionArgs = new String[] {Integer.toString(selectedExerciseID)};
		    Cursor selectionCursor = myContentResolver.query(ExerciseContentProvider.CONTENT_URI,null, selection, selectionArgs, null);
		    
		    
		    titleCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.KEY_EXERCISE_NAME);
		    restCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.KEY_REST_PERIOD);
		    uriCol = selectionCursor.getColumnIndexOrThrow(ExerciseContentProvider.IMAGE_URI);
			
			if(selectionCursor.moveToNext()){
				titleTextView.setText(selectionCursor.getString(titleCol));
				restTextView.setText(selectionCursor.getString(restCol));
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
	}

	public void saveExerciseToDB(int currentExerciseID, String title, int rest, Uri photoUri){
		
		
		//If exercise ID < 0 no exercise has been selected so insert a new one
		if(currentExerciseID < 0){
			ContentValues values = new ContentValues();
			values.put(ExerciseContentProvider.KEY_EXERCISE_NAME, title);
			values.put(ExerciseContentProvider.KEY_REST_PERIOD, rest);
			if(photoUri != null){
				values.put(ExerciseContentProvider.IMAGE_URI, photoUri.toString());
			}
			
			myContentResolver.insert(ExerciseContentProvider.CONTENT_URI, values);
			
		}else{
			//If exercise ID >=0 exercise has been selected
			ContentResolver myContentResolver = hostActivity.getContentResolver();
			myContentResolver.delete(ExerciseContentProvider.CONTENT_URI,ExerciseContentProvider.KEY_EXERCISE_ID+"="+currentExerciseID, null);
			
			ContentValues values = new ContentValues();
			values.put(ExerciseContentProvider.KEY_EXERCISE_ID, currentExerciseID);
			values.put(ExerciseContentProvider.KEY_EXERCISE_NAME, title);
			values.put(ExerciseContentProvider.KEY_REST_PERIOD, rest);
			if(photoUri != null){
				values.put(ExerciseContentProvider.IMAGE_URI, photoUri.toString());
			}
			myContentResolver.insert(ExerciseContentProvider.CONTENT_URI, values);
			
		}
		
	}
}
