package ua.com.crosp.solutions.presenterlayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by Oleksandr "CROSP" Molochko on 5/10/16 at 3:06 PM.
 * Project : PresenterLayout
 * Package : ua.com.crosp.solutions.library.presenterlayout.view
 */
public class NoWifiView extends SwipeRefreshLayout {

    // Variables
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;


    public NoWifiView(Context context) {
        super(context);
        initView();
    }

    public NoWifiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mPreMeasureRefreshing);
        }
    }


    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mPreMeasureRefreshing = refreshing;
        }
    }

    public void initView() {
        inflate(getContext(), R.layout.view_overlapping_no_wifi, this);
    }

}
