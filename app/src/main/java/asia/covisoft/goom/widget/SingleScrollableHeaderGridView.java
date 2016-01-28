package asia.covisoft.goom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Covisoft on 01/12/2015.
 */
public class SingleScrollableHeaderGridView extends HeaderGridView {
    private View mHeaderView;

    public SingleScrollableHeaderGridView(Context context) {
        super(context);
    }

    public SingleScrollableHeaderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SingleScrollableHeaderGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mHeaderView != null) return mHeaderView.onTouchEvent(ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        if (mHeaderView != null) removeHeaderView(mHeaderView);
        mHeaderView = v;
        super.addHeaderView(v, data, isSelectable);
    }
}