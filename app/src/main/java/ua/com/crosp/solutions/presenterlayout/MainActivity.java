package ua.com.crosp.solutions.presenterlayout;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ua.com.crosp.solutions.library.presenterlayout.layout.PresenterLayout;

public class MainActivity extends AppCompatActivity {
    public static final int DELAY_MILLIS = 2000;
    public static final int ID_VIEW_TRANSPARENT = R.id.view_overlapping_transparent;
    PresenterLayout mPresenterLayout;
    private Button mButtonShowError, mButtonShowSuccess, mButtonShowLoading,
            mButtonShowNoConnection, mButtonShowNoWifi, mButtonShowTransparent;
    public static final int ID_VIEW_NO_WIFI = R.id.view_overlapping_no_wifi_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenterLayout = (PresenterLayout) findViewById(R.id.presenter_layout_main);
        final NoWifiView noWifiView = new NoWifiView(this);
        noWifiView.setId(ID_VIEW_NO_WIFI);
        noWifiView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noWifiView.setRefreshing(false);
                mPresenterLayout.hideOverlappingView(ID_VIEW_NO_WIFI);
            }
        });
        TransparentView transparentView = new TransparentView(this);
        transparentView.setId(ID_VIEW_TRANSPARENT);
        transparentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.hideOverlappingView(ID_VIEW_TRANSPARENT);
            }
        });
        mPresenterLayout.addOverlappingView(noWifiView, true);
        mPresenterLayout.addOverlappingView(transparentView);
        mButtonShowTransparent = (Button) findViewById(R.id.button_show_transparent);
        mButtonShowTransparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showOverlappingView(ID_VIEW_TRANSPARENT, true);
            }
        });
        mButtonShowSuccess = (Button) findViewById(R.id.button_show_success);
        mButtonShowNoWifi = (Button) findViewById(R.id.button_show_no_wifi_connection);
        mButtonShowNoWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showOverlappingView(R.id.view_overlapping_no_wifi_connection, true);

            }
        });
        mButtonShowSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenterLayout.showSuccess("Success !!!");
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
