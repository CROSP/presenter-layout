package ua.com.crosp.solutions.library.presenterlayout.view;

import android.content.Context;
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
public class LoadingView extends FrameLayout {
    // Views
    private TextView mTextViewLoading;
    private IconTextView mIconTextViewLoading;

    public LoadingView(Context context) {
        super(context);
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        View inflatedView = inflate(getContext(), R.layout.view_overlapping_default_loading, this);
        inflatedView.setBackgroundColor(getResources().getColor(R.color.default_background_loading_layout));
        findViews(inflatedView);
        setId(R.id.view_overlapping_default_loading);
    }

    private void findViews(View inflatedView) {
        mTextViewLoading = (TextView) inflatedView.findViewById(R.id.text_view_loading);
        mIconTextViewLoading = (IconTextView) inflatedView.findViewById(R.id.icon_text_view_loading);
    }

    public void setLoadingText(String loadingText) {
        mTextViewLoading.setText(loadingText);
    }

    public void setLoadingIcon(String loadingIcon) {
        mIconTextViewLoading.setText(loadingIcon);
    }
}
