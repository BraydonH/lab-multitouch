package edu.uw.multitouchlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;

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
        int id = event.getPointerId(index);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) : //put finger down
                //Log.v(TAG, "finger down");
                Log.v(TAG, "Pointer Index: " + index);
                Log.v(TAG, "Pointer ID:" + id);
                view.ball.cx = x;
                view.ball.cy = y;
                view.addTouch(id, x, y);
                return true;
            case (MotionEvent.ACTION_MOVE) : //move finger
                //Log.v(TAG, "finger move");
                view.ball.cx = x;
                view.ball.cy = y;
                int numTouches = event.getPointerCount();
                for(int i = 0; i < numTouches; i++) {
                    view.moveTouch(event.getPointerId(i), event.getX(i), event.getY(i) - getSupportActionBar().getHeight());
                }
                return true;
            case (MotionEvent.ACTION_UP) : //lift finger up
                Log.v(TAG, "Pointer Index: " + index);
                Log.v(TAG, "Pointer ID:" + id);
                view.removeTouch(id);
                return true;
            case (MotionEvent.ACTION_CANCEL) : //aborted gesture
            case (MotionEvent.ACTION_OUTSIDE) : //outside bounds
            case (MotionEvent.ACTION_POINTER_DOWN) :
                float x1 = event.getX(index);
                float y1 = event.getY(index) - getSupportActionBar().getHeight();
                view.addTouch(id, x1, y1);
            case (MotionEvent.ACTION_POINTER_UP) :
                view.removeTouch(id);

            default :
                return super.onTouchEvent(event);
        }
    }
}