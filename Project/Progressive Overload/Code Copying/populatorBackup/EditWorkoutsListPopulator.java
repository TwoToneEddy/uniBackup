package com.example.progressiveoverload.EditWorkouts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.ListView;
import android.widget.TextView;

import com.example.progressiveoverload.R;
import com.example.progressiveoverload.Adapters.CustomExerciseListAdapter;
import com.example.progressiveoverload.Adapters.CustomWorkoutListAdapter;
import com.example.progressiveoverload.ContentProviders.ExerciseContentProvider;
import com.example.progressiveoverload.ContentProviders.SchedulerContentProvider;
import com.example.progressiveoverload.ContentProviders.WorkoutContentProvider;
import com.example.progressiveoverload.Utilities.CursorSelector;
import android.app.Activity;

public class EditWorkoutsListPopulator {
	ContentResolver myContentResolver;
	Activity hostActivity;
	CustomExerciseListAdapter exerciseListAdapter;
	CustomWorkoutListAdapter workoutListAdapter;
	
	public EditWorkoutsListPopulator(Activity hostActivity){
		myContentResolver = hostActivity.getContentResolver();
		this.hostActivity=hostActivity;
		
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
	
	//Could be put in super class
	public ListView populateWorkoutList(){
		Cursor cursor = myContentResolver.query(WorkoutContentProvider.CONTENT_URI,null, null, null, WorkoutContentProvider.KEY_WORKOUT_DAY+" DESC");
		workoutListAdapter = new CustomWorkoutListAdapter(hostActivity,cursor);
		ListView workoutListView = (ListView)hostActivity.findViewById(R.id.workoutList);
		workoutListView.setAdapter(workoutListAdapter);
		return workoutListView;
	}
	
	
	public void populateTextFields(int selectedWorkoutID){
		TextView dayTextView = (TextView) hostActivity.findViewById(R.id.dayField);
		TextView muscleGroupTextView = (TextView) hostActivity.findViewById(R.id.muscleGroupField);
		
		int dayCol,muscleCol;
		
		String selection = WorkoutContentProvider.KEY_WORKOUT_ID +" == ? ";
	    String[] selectionArgs = new String[] {Integer.toString(selectedWorkoutID)};
	    Cursor selectionCursor = myContentResolver.query(WorkoutContentProvider.CONTENT_URI,null, selection, selectionArgs, null);
	    
	    
		dayCol = selectionCursor.getColumnIndexOrThrow(WorkoutContentProvider.KEY_WORKOUT_DAY);
		muscleCol = selectionCursor.getColumnIndexOrThrow(WorkoutContentProvider.KEY_MUSCLE_GROUP);
		
		if(selectionCursor.moveToNext()){
			dayTextView.setText(selectionCursor.getString(dayCol));
			muscleGroupTextView.setText(selectionCursor.getString(muscleCol));
		}
		
		
	}

}
