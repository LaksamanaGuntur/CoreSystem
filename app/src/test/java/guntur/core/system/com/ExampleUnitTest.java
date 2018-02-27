package guntur.core.system.com;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import guntur.core.system.com.data.ResultData;
import guntur.core.system.com.model.DataModel;
import guntur.core.system.com.network.ApiResponse;
import guntur.core.system.com.repository.MainRepository;
import guntur.core.system.com.ui.page.MainContract;
import guntur.core.system.com.ui.page.MainPresenter;
import io.reactivex.Flowable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class ExampleUnitTest {
    @Mock
    MainContract.View mMainView;

    @Mock
    MainContract.UserActionListener mActionListener;

    @Mock
    DataModel mDataModel;

    @Mock
    MainRepository mMainRepository;

    private MainPresenter mMainPresenter;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mMainPresenter = new MainPresenter(mMainRepository, mDataModel);
        mActionListener = mMainPresenter;
        mMainPresenter.setView(mMainView);
    }

    @Test
    public void initializeDataOnlineTest(){
        //Mocking Data
        ApiResponse apiresponse = new ApiResponse();
        List<ResultData> resultDataList = new ArrayList<>();
        apiresponse.setResults(resultDataList);
        //Mocking response
        when(mMainRepository.getData()).thenReturn(Flowable.just(apiresponse));
        //Get Movies Data
        mMainPresenter.getData();
        //Verify MainView Show ProgressBar
        verify(mMainView).showProgressBar();
        verify(mMainView).hideProgressBar();
        verify(mMainView).setAdapter(resultDataList);
    }

    @Test
    public void initializeDataOfflineTest(){
        //Mocking Data
        ApiResponse apiresponse = new ApiResponse();
        List<ResultData> resultDataList = new ArrayList<>();
        apiresponse.setResults(resultDataList);
        //Mocking response
        when(mMainRepository.getData()).thenReturn(Flowable.<ApiResponse>error(new Throwable("Cannot get response from server")));
        //Get Movies Data
        mMainPresenter.getData();
        //Verify MainView Show ProgressBar
        verify(mMainView).showProgressBar();
        //Verify MainView Hide progress bar and load data from local data
        verify(mMainView).hideProgressBar();
        verify(mDataModel).getAllData();
    }
}