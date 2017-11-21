package edu.uw.multitouchlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private DrawingSurfaceView view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY() - getSupportActionBar().getHeight(); //closer to center...

        int action = event.getActionMasked();

        int index = event.getActionIndex();

        switch(action) {
            case (MotionEvent.ACTION_DOWN) : //put finger down
                //Log.v(TAG, "finger down");
                Log.v(TAG, "Pointer Index: " + index);
                Log.v(TAG, "Pointer ID:" + event.getPointerId(index));
                view.ball.cx = x;
                view.ball.cy = y;
                return true;
            case (MotionEvent.ACTION_MOVE) : //move finger
                //Log.v(TAG, "finger move");
                view.ball.cx = x;
                view.ball.cy = y;
                return true;
            case (MotionEvent.ACTION_UP) : //lift finger up
                Log.v(TAG, "Pointer Index: " + index);
                Log.v(TAG, "Pointer ID:" + event.getPointerId(index));
                return true;
            case (MotionEvent.ACTION_CANCEL) : //aborted gesture
            case (MotionEvent.ACTION_OUTSIDE) : //outside bounds
            default :
                return super.onTouchEvent(event);
        }
    }
}