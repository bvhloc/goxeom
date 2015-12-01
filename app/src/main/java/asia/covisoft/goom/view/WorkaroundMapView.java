package asia.covisoft.goom.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

import asia.covisoft.goom.backpress.BackPressImpl;
import asia.covisoft.goom.backpress.OnBackPressListener;

/**
 * Created by Covisoft on 24/11/2015.
 */
public class WorkaroundMapView extends MapView{
    private OnTouchListener mListener;

    public WorkaroundMapView(Context context) {
        super(context);
    }

    public void setOnTouchListener(OnTouchListener listener) {
        mListener = listener;
    }

    public interface OnTouchListener {
        public abstract void onTouch();
    }

    public class TouchableWrapper extends FrameLayout {

        public TouchableWrapper(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mListener.onTouch();
                    break;
                case MotionEvent.ACTION_UP:
                    mListener.onTouch();
                    break;
            }
            return super.dispatchTouchEvent(event);
        }
    }
}