package guntur.core.system.com.ui.page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import guntur.core.system.com.CoreApp;
import guntur.core.system.com.R;
import guntur.core.system.com.adapter.ListAdapter;
import guntur.core.system.com.data.ResultData;
import guntur.core.system.com.ui.MainActivityModule;

public class MainActivity extends AppCompatActivity implements MainContract.View{
    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private MainContract.UserActionListener mUserActionListener;
    private GridLayoutManager mGridLayoutManager;
    private ListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = mainPresenter;
        mainPresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void setAdapter(List<ResultData> resultData) {
        mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new ListAdapter(resultData, this);
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void initializeData() {
        mUserActionListener.getData();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
