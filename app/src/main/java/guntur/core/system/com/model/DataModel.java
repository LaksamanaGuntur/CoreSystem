package guntur.core.system.com.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import guntur.core.system.com.data.DaoSession;
import guntur.core.system.com.data.ResultData;
import guntur.core.system.com.data.ResultDataDao;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataDao mResultDataDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
    }

    @NonNull
    public void insertData(ResultData resultData){
        mResultDataDao.insertOrReplace(resultData);
    }

    @Nullable
    public List<ResultData> getAllData(){
        return mResultDataDao.loadAll();
    }

    public void deleteDataList() {
        mResultDataDao.deleteAll();
    }
}
