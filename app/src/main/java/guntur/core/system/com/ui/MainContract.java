package guntur.core.system.com.ui;

import java.util.List;

import guntur.core.system.com.data.ResultData;
import guntur.core.system.com.network.ApiResponse;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainContract {

    public interface View{
        void setAdapter(List<ResultData> resultData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void getData();
        void saveData(ApiResponse apiResponse);
    }
}
