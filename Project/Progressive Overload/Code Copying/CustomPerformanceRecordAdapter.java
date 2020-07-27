

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class CustomPerformanceRecordAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public CustomPerformanceRecordAdapter(Context context, Cursor c) {
        super(context, c);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context); 
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mLayoutInflater.inflate(R.layout.workouts_listview_contents, parent, false);
        return v;
    }


    @Override
    public void bindView(View v, Context context, Cursor c) {
        String workoutDay = c.getString(c.getColumnIndexOrThrow(PerformanceRecordProvider.KEY_WORKOUT_DAY));
        String workoutMuscleGroup = c.getString(c.getColumnIndexOrThrow(PerformanceRecordProvider.KEY_MUSCLE_GROUP));

        TextView dayTextView = (TextView) v.findViewById(R.id.workout_day_textview);
        if (dayTextView != null) {
            dayTextView.setText(workoutDay);
        }

        TextView muscleGroupTextView = (TextView) v.findViewById(R.id.workout_muscle_group_textview);
        if (muscleGroupTextView != null) {
            muscleGroupTextView.setText(workoutMuscleGroup);
        }      
    }
}