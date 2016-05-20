package ua.com.crosp.solutions.library.presenterlayout.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconTextView;

import ua.com.crosp.solutions.library.presenterview.R;

/**
 * Created by Oleksandr "CROSP" Molochko on 5/10/16 at 3:06 PM.
 * Project : PresenterLayout
 * Package : ua.com.crosp.solutions.library.presenterlayout.view
 */
public class ErrorView extends SwipeRefreshLayout {

    // Views
    private IconTextView mIconTextViewErrorIcon;
    private TextView mTextViewError;
    private LinearLayout mLinearLayoutContainer;
    // Variables
    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;


    public ErrorView(Context context) {
        super(context);
        initView();
    }

    public ErrorView(Context context, AttributeSet attrs) {
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
        View inflatedView = inflate(getContext(), R.layout.view_overlapping_default_error, this);
        findViews(inflatedView);
        setId(R.id.view_overlapping_default_error);
    }

    public void setErrorText(String errorText) {
        mTextViewError.setText(errorText);
    }

    public void setErrorIcon(String errorIcon) {
        mIconTextViewErrorIcon.setText(errorIcon);
    }

    public void setErrorBackgroundColor(int backgroundColor) {
        mLinearLayoutContainer.setBackgroundColor(backgroundColor);
    }

    private void findViews(View parentView) {
        mIconTextViewErrorIcon = (IconTextView) parentView.findViewById(R.id.icon_text_view_error_icon);
        mTextViewError = (TextView) parentView.findViewById(R.id.text_view_error_text);
        mLinearLayoutContainer = (LinearLayout) parentView.findViewById(R.id.linear_layout_view_container);
    }
    public IconTextView getIconTextViewErrorIcon() {
        return mIconTextViewErrorIcon;
    }

    public TextView getTextViewError() {
        return mTextViewError;
    }


}
