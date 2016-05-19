package ua.com.crosp.solutions.presenterlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Oleksandr "CROSP" Molochko on 5/10/16 at 3:06 PM.
 * Project : PresenterLayout
 * Package : ua.com.crosp.solutions.library.presenterlayout.view
 */
public class TransparentView extends FrameLayout {

    public TransparentView(Context context) {
        super(context);
        initView();
    }

    public TransparentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    public void initView() {
        inflate(getContext(), R.layout.view_overlapping_transparent, this);
        setBackgroundColor(getResources().getColor(R.color.transparent_grey));
    }

}
