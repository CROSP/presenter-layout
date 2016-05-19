package ua.com.crosp.solutions.library.presenterlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.joanzapata.iconify.widget.IconButton;
import com.joanzapata.iconify.widget.IconTextView;

import ua.com.crosp.solutions.library.presenterview.R;

/**
 * Created by Oleksandr "CROSP" Molochko on 5/10/16 at 3:07 PM.
 * Project : PresenterLayout
 * Package : ua.com.crosp.solutions.library.presenterlayout.view
 */
public class SuccessView extends FrameLayout {
    // Views
    private IconTextView mIconTextViewSuccess;
    private Button mButtonSuccess;
    private TextView mTextViewSuccess;

    public SuccessView(Context context) {
        super(context);
        initView();
    }

    public SuccessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        View inflatedView = inflate(getContext(), R.layout.view_overlapping_default_success, this);
        inflatedView.setBackgroundColor(getResources().getColor(R.color.default_background_success_layout));
        findViews(inflatedView);
        setId(R.id.view_overlapping_default_success);
    }

    private void findViews(View inflatedView) {
        mIconTextViewSuccess = (IconTextView) inflatedView.findViewById(R.id.icon_text_view_success);
        mButtonSuccess = (IconButton) inflatedView.findViewById(R.id.icon_button_success);
        mTextViewSuccess = (TextView) inflatedView.findViewById(R.id.text_view_success);
    }

    public TextView getTextViewSuccess() {
        return mTextViewSuccess;
    }

    public IconTextView getIconTextViewSuccess() {
        return mIconTextViewSuccess;
    }

    public Button getButtonSuccess() {
        return mButtonSuccess;
    }
    public void setSuccessIcon(String successIcon) {
        mIconTextViewSuccess.setText(successIcon);
    }

    public void setSuccessText(String successText) {
        mTextViewSuccess.setText(successText);
    }

    public void setSuccessButtonListener(OnClickListener onSuccessClickListener) {
        mButtonSuccess.setOnClickListener(onSuccessClickListener);
    }

    public void setSuccessButtonText(String buttonText) {
        mButtonSuccess.setText(buttonText);
    }

}
