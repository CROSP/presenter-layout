package ua.com.crosp.solutions.library.presenterlayout.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;

import ua.com.crosp.solutions.library.presenterview.R;

/**
 * Created by Oleksandr "CROSP" Molochko on 5/10/16 at 3:06 PM.
 * Project : PresenterLayout
 * Package : ua.com.crosp.solutions.library.presenterlayout.view
 */
public class NoConnectionView extends SwipeRefreshLayout {
    // Views
    private IconTextView mIconTextViewNoConnectionIcon;
    private TextView mTextViewNoConnection;
    private FrameLayout mFrameLayoutContainer;
    // Variables
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;

    public NoConnectionView(Context context) {
        super(context);
        initView();
    }

    public NoConnectionView(Context context, AttributeSet attrs) {
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

    public void setNoConnectionText(String noConnectionText) {
        mTextViewNoConnection.setText(noConnectionText);
    }

    public void setNoConnectionIcon(String noConnectionIcon) {
        mIconTextViewNoConnectionIcon.setText(noConnectionIcon);
    }

    public void setNoConnectionBackgroundColor(int backgroundColor) {
        mFrameLayoutContainer.setBackgroundColor(backgroundColor);
    }

    public void initView() {
        View inflatedView = inflate(getContext(), R.layout.view_overlapping_default_no_connection, this);
        inflatedView.setBackgroundColor(getResources().getColor(R.color.default_background_no_connection_layout));
        findViews(inflatedView);
        setId(R.id.view_overlapping_default_no_connection);
    }

    private void findViews(View inflatedView) {
        mFrameLayoutContainer = (FrameLayout) inflatedView.findViewById(R.id.frame_layout_view_container);
        mIconTextViewNoConnectionIcon = (IconTextView) inflatedView.findViewById(R.id.icon_text_view_no_connection_icon);
        mTextViewNoConnection = (TextView) inflatedView.findViewById(R.id.text_view_no_connection_text);
    }
}
