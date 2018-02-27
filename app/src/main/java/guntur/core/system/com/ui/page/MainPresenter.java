package guntur.core.system.com.ui.page;

import guntur.core.system.com.data.ResultData;
import guntur.core.system.com.model.DataModel;
import guntur.core.system.com.network.ApiResponse;
import guntur.core.system.com.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainPresenter implements MainContract.UserActionListener {
    private MainContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public MainPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(MainContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mDataModel.deleteDataList();
        mView.showProgressBar();
        mMainRepository.getData()
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        mView.hideProgressBar();
                        mView.setAdapter(apiResponse.getResults());
                        saveData(apiResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        mView.hideProgressBar();
                        mView.setAdapter(mDataModel.getAllData());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveData(ApiResponse apiResponse) {
        for(ResultData resultData : apiResponse.getResults()){
            mDataModel.insertData(resultData);
        }
    }
}
