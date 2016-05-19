package ua.com.crosp.solutions.library.presenterlayout.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.com.crosp.solutions.library.presenterlayout.view.ErrorView;
import ua.com.crosp.solutions.library.presenterlayout.view.LoadingView;
import ua.com.crosp.solutions.library.presenterlayout.view.NoConnectionView;
import ua.com.crosp.solutions.library.presenterlayout.view.SuccessView;
import ua.com.crosp.solutions.library.presenterview.R;

/**
 * Created by Oleksandr "CROSP" Molochko on 4/29/16 at 10:57 AM.
 * Project : PresenterView
 * Package : ua.com.crosp.solutions.library.presenterview
 */
public class PresenterLayout extends FrameLayout {

    // Constants
    private static final List<Integer> CONTAINER_IDS;
    private static final List<Integer> DEFAULT_VIEWS_IDS;

    // Views
    private FrameLayout mFrameLayoutChildViewsContainer;
    private FrameLayout mFrameLayoutOverlappingViewContainer;
    private ErrorView mErrorView;
    private LoadingView mLoadingView;
    private NoConnectionView mNoConnectionView;
    private SuccessView mSuccessView;
    // Variables
    private Map<Integer, View> mOverlappingViewsMap = new HashMap<>();
    private LayoutParams mCommonChildLayoutParams;
    // Default attribute values
    private int mDefaultOverlappingBackgroundColor;
    // Current attribute values
    private int mOverlappingBackgroundColor;

    // Initialization of static constant arrays with generated IDs
    static {
        // Initializing font awesome library only once
        Iconify.with(new FontAwesomeModule());
        CONTAINER_IDS = new ArrayList<>();
        DEFAULT_VIEWS_IDS = new ArrayList<>();
        CONTAINER_IDS.add(R.id.frame_layout_child_container);
        CONTAINER_IDS.add(R.id.frame_layout_overlapping_views_container);
        DEFAULT_VIEWS_IDS.add(R.id.view_overlapping_default_error);
        DEFAULT_VIEWS_IDS.add(R.id.view_overlapping_default_loading);
        DEFAULT_VIEWS_IDS.add(R.id.view_overlapping_default_success);
        DEFAULT_VIEWS_IDS.add(R.id.view_overlapping_default_no_connection);
    }


    /**
     * In case of creating view from code
     *
     * @param context context in order to access resources
     */
    public PresenterLayout(Context context) {
        super(context);
        initializedDefaultsFromXml();
        initLayout();
        createDefaultOverlappingViews();
    }

    /**
     * In case of creating from XML
     *
     * @param context context in order to access resources
     * @param attrs   attributes set containing XML specific attributes
     */
    public PresenterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializedDefaultsFromXml();
        collectAttributes(attrs);
        initLayout();
        createDefaultOverlappingViews();
    }

    /**
     * In case of creating view from XML with @style attribute set
     *
     * @param context      context in order to access resources
     * @param attrs        attributes set containing XML specific attributes
     * @param defStyleAttr a resource identifier of a style resource that supplies default values for the view
     */
    public PresenterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializedDefaultsFromXml();
        collectAttributes(attrs);
        initLayout();
        createDefaultOverlappingViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PresenterLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializedDefaultsFromXml();
        collectAttributes(attrs);
        initLayout();
        createDefaultOverlappingViews();
    }

    public void createDefaultOverlappingViews() {
        mCommonChildLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        Context context = getContext();
        mErrorView = new ErrorView(context);
        mLoadingView = new LoadingView(context);
        mNoConnectionView = new NoConnectionView(context);
        mSuccessView = new SuccessView(context);
        mErrorView.setVisibility(GONE);
        mLoadingView.setVisibility(GONE);
        mNoConnectionView.setVisibility(GONE);
        mSuccessView.setVisibility(GONE);
        addOverlappingView(mErrorView);
        addOverlappingView(mLoadingView);
        addOverlappingView(mNoConnectionView);
        addOverlappingView(mSuccessView);
    }


    /**
     * Collect attributes provided in XML declaration
     *
     * @param attributeSet attribute set
     */

    private void collectAttributes(AttributeSet attributeSet) {
        TypedArray attributesArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.PresenterLayout);
        try {
            mOverlappingBackgroundColor = attributesArray.getColor(R.styleable.PresenterLayout_background_color_overlapping, mDefaultOverlappingBackgroundColor);
        } finally {
            attributesArray.recycle();
        }
    }

    /**
     * Initialize default values from xml like color, dimension ...
     */
    private void initializedDefaultsFromXml() {
        // mDefaultOverlappingBackgroundColor = getResources().getColor(R.color.default_background_loading_layout);
        mDefaultOverlappingBackgroundColor = Color.TRANSPARENT;
    }

    private void initLayout() {
        inflate(getContext(), R.layout.presenter_layout, this);
        mFrameLayoutChildViewsContainer = (FrameLayout) findViewById(R.id.frame_layout_child_container);
        mFrameLayoutOverlappingViewContainer = (FrameLayout) findViewById(R.id.frame_layout_overlapping_views_container);
        if (mOverlappingBackgroundColor != Color.TRANSPARENT) {
            mFrameLayoutOverlappingViewContainer.setBackgroundColor(mOverlappingBackgroundColor);
        }

    }

    @Override
    public void addView(View child) {
        if (CONTAINER_IDS.contains(child.getId())) {
            super.addView(child);
        } else {
            mFrameLayoutChildViewsContainer.addView(child);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (CONTAINER_IDS.contains(child.getId())) {
            super.addView(child, index, params);
        } else {
            mFrameLayoutChildViewsContainer.addView(child, index, params);
        }
    }

    @Override
    public void addView(View child, int width, int height) {
        if (CONTAINER_IDS.contains(child.getId())) {
            super.addView(child, width, height);
        } else {
            mFrameLayoutChildViewsContainer.addView(child, width, height);
        }
    }

    /**
     * Add overlapping view and place it into overlapping container
     *
     * @param view   view
     * @param width  width
     * @param height height
     */
    public void addOverlappingView(View view, int width, int height) {
        mFrameLayoutOverlappingViewContainer.addView(view, width, height);
        mOverlappingViewsMap.put(view.getId(), view);
    }

    /**
     * Add overlapping view and place it into overlapping container
     *
     * @param view   view
     * @param index  index
     * @param params params
     */
    public void addOverlappingView(View view, int index, ViewGroup.LayoutParams params) {
        mFrameLayoutOverlappingViewContainer.addView(view, index, params);
        mOverlappingViewsMap.put(view.getId(), view);
    }

    /**
     * Add overlapping view and place it into overlapping container
     *
     * @param view view
     */
    public void addOverlappingView(View view) {
        mFrameLayoutOverlappingViewContainer.addView(view, mCommonChildLayoutParams);
        mOverlappingViewsMap.put(view.getId(), view);
    }

    // Delegating everything to child container
    @Override
    public void removeView(View view) {
        mFrameLayoutChildViewsContainer.removeView(view);
    }

    @Override
    public void removeAllViews() {
        mFrameLayoutChildViewsContainer.removeAllViews();
    }

    @Override
    public void removeViewAt(int index) {
        mFrameLayoutChildViewsContainer.removeViewAt(index);
    }

    @Override
    public void removeViews(int start, int count) {
        mFrameLayoutChildViewsContainer.removeViews(start, count);
    }

    @Override
    public void removeViewInLayout(View view) {
        mFrameLayoutChildViewsContainer.removeViewInLayout(view);
    }

    @Override
    public void removeAllViewsInLayout() {
        mFrameLayoutChildViewsContainer.removeAllViewsInLayout();
    }


    /**
     * Show custom overlapping view by id
     * Note : view has to be added using {@link #addOverlappingView(View) addOverlappingView} method
     *
     * @param viewId     id of target view, that has been added to map
     * @param hideOthers hide other views in overlapping container
     * @return if view has been found and shown
     */
    public boolean showCustomOverlappingView(int viewId, boolean hideOthers) {
        // Check if view exists
        View targetView = mOverlappingViewsMap.get(viewId);
        if (targetView != null) {
            if (hideOthers) {
                for (Map.Entry<Integer, View> viewEntry : mOverlappingViewsMap.entrySet()) {
                    if (viewEntry.getKey() != viewId) {
                        viewEntry.getValue().setVisibility(GONE);
                    }
                }
            }
            // Make our target view visible
            targetView.setVisibility(VISIBLE);
            return true;
        }
        return false;
    }

    /**
     * Remove overlapping view added with {@link #addOverlappingView(View) addOverlappingView} method
     * Note : Default view cannot be removed because they are tied to public methods
     *
     * @param overlappingView view object to remove
     */
    public void removeOverlappingView(View overlappingView) {
        removeOverlappingView(overlappingView.getId());
    }

    /**
     * Remove overlapping view added with {@link #addOverlappingView(View) addOverlappingView} method
     * Note : Default view cannot be removed because they are tied to public methods
     *
     * @param viewId id of view to remove
     */
    public void removeOverlappingView(int viewId) {
        if (DEFAULT_VIEWS_IDS.contains(viewId)) {
            throw new InvalidParameterException("View cannot be predefined");
        } else {
            View view = mOverlappingViewsMap.get(viewId);
            if (view != null) {
                mFrameLayoutOverlappingViewContainer.removeView(view);
                mOverlappingViewsMap.remove(viewId);
            }
        }
    }

    public void hideOverlappingContainer() {
        mFrameLayoutOverlappingViewContainer.setVisibility(GONE);
    }

    public void showOverlappingContainer() {
        mFrameLayoutOverlappingViewContainer.setVisibility(VISIBLE);
    }

    /**
     * Predefined methods for default views
     */
    // Error specific methods
    public void showErrorMessage(String errorMessage) {
        mErrorView.setErrorText(errorMessage);
        mErrorView.setVisibility(VISIBLE);
    }

    public void showErrorMessage() {
        mErrorView.setVisibility(VISIBLE);
    }

    public void hideErrorMessage() {
        mErrorView.setVisibility(GONE);
    }

    public SwipeRefreshLayout getErrorRefreshLayout() {
        return mErrorView;
    }

    public void setErrorMessage(String errorMessage) {
        mErrorView.setErrorText(errorMessage);
    }

    public void setErrorIcon(String errorIcon) {
        mErrorView.setErrorIcon(errorIcon);
    }

    public void setErrorRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        mErrorView.setOnRefreshListener(onRefreshListener);
    }

    public void setErrorBackgroundColor(int backgroundColor) {
        mErrorView.setErrorBackgroundColor(backgroundColor);
    }

    // Loading specific methods
    public void showLoading(String loadingMessage) {
        mLoadingView.setLoadingText(loadingMessage);
        mLoadingView.setVisibility(VISIBLE);
    }

    public void showLoading() {
        mLoadingView.setVisibility(VISIBLE);
    }

    public void hideLoading() {
        mLoadingView.setVisibility(GONE);
    }

    public void setLoadingText(String loadingText) {
        mLoadingView.setLoadingText(loadingText);
    }

    public void setLoadingIcon(String loadingIcon) {
        mLoadingView.setLoadingIcon(loadingIcon);
    }

    public void setLoadingBackground(int color) {
        mLoadingView.setBackgroundColor(color);
    }


    // No connection specific methods

    public void showNoConnection() {
        mNoConnectionView.setVisibility(VISIBLE);
    }

    public void hideNoConnection() {
        mNoConnectionView.setVisibility(GONE);
    }

    public void showNoConnection(String message) {
        mNoConnectionView.setNoConnectionText(message);
        mNoConnectionView.setVisibility(VISIBLE);
    }

    public void setNoConnectionText(String noConnectionText) {
        mNoConnectionView.setNoConnectionText(noConnectionText);
    }

    public void setNoConnectionIcon(String noConnectionIcon) {
        mNoConnectionView.setNoConnectionIcon(noConnectionIcon);
    }

    public void setNoConnectionBackgroundColor(int backgroundColor) {
        mNoConnectionView.setNoConnectionBackgroundColor(backgroundColor);
    }

    public void setNoConnectionRefreshListener(SwipeRefreshLayout.OnRefreshListener refreshListener) {
        mNoConnectionView.setOnRefreshListener(refreshListener);
    }

    public SwipeRefreshLayout getNoConnectionRefreshLayout() {
        return mNoConnectionView;
    }

    // Success specific methods

    public void showSuccess() {
        mSuccessView.setVisibility(VISIBLE);
    }

    public void hideSuccess() {
        mSuccessView.setVisibility(GONE);
    }

    public void showSuccess(String successText) {
        mSuccessView.setSuccessText(successText);
        mSuccessView.setVisibility(VISIBLE);
    }

    public void setSuccessIcon(String successIcon) {
        mSuccessView.setSuccessIcon(successIcon);
    }

    public void setSuccessButtonText(String buttonText) {
        mSuccessView.setSuccessButtonText(buttonText);
    }

    public void setSuccessBackgroundColor(int backgroundColor) {
        mSuccessView.setBackgroundColor(backgroundColor);
    }

    public void setSuccessText(String successText) {
        mSuccessView.setSuccessText(successText);
    }

    public void setSuccessButtonClickListener(OnClickListener successButtonClickListener) {
        mSuccessView.setSuccessButtonListener(successButtonClickListener);
    }
}