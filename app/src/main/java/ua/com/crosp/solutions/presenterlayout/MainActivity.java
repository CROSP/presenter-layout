package ua.com.crosp.solutions.presenterlayout;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ua.com.crosp.solutions.library.presenterlayout.layout.PresenterLayout;

public class MainActivity extends AppCompatActivity {
    public static final int DELAY_MILLIS = 2000;
    PresenterLayout mPresenterLayout;
    private Button mButtonShowError;
    private Button mButtonShowLoading;
    private Button mButtonShowNoConnection;
    private Button mButtonShowSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenterLayout = (PresenterLayout) findViewById(R.id.presenter_layout_main);
        mButtonShowSuccess = (Button) findViewById(R.id.button_show_success);
        mButtonShowSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showSuccess();
                mPresenterLayout.setSuccessButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenterLayout.hideSuccess();
                    }
                });
            }
        });
        mButtonShowError = (Button) findViewById(R.id.button_show_error);
        mButtonShowError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showErrorMessage("Error message, swipe to retry");
            }
        });
        mPresenterLayout.setErrorRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenterLayout.getErrorRefreshLayout().setRefreshing(false);
                mPresenterLayout.hideErrorMessage();
            }
        });
        mButtonShowLoading = (Button) findViewById(R.id.button_show_loading);
        mButtonShowLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showLoading("Loading wait please !");
                mPresenterLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenterLayout.hideLoading();
                    }
                }, DELAY_MILLIS);
            }
        });
        mButtonShowNoConnection = (Button) findViewById(R.id.button_show_no_connection);
        mButtonShowNoConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showNoConnection("Internet connection is not available");
            }
        });
        mPresenterLayout.setNoConnectionRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenterLayout.getNoConnectionRefreshLayout().setRefreshing(false);
                mPresenterLayout.hideNoConnection();
            }
        });

    }
}
