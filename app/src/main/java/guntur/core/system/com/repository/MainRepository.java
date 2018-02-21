package guntur.core.system.com.repository;

import guntur.core.system.com.helper.Constant;
import guntur.core.system.com.network.ApiResponse;
import guntur.core.system.com.network.NetworkService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainRepository extends BaseRepository {
    public MainRepository(NetworkService networkService) {
        super(networkService);
    }

    /**
     * Get Data
     * @Param Sorting Type
     * */
    public Flowable<ApiResponse> getData() {
        return networkService.getData(Constant.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
