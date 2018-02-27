package guntur.core.system.com.ui;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import dagger.Module;
import dagger.Provides;
import guntur.core.system.com.CoreApp;
import guntur.core.system.com.data.DaoMaster;
import guntur.core.system.com.data.DaoSession;
import guntur.core.system.com.di.scope.ActivityScope;
import guntur.core.system.com.helper.Constant;
import guntur.core.system.com.model.DataModel;
import guntur.core.system.com.network.NetworkService;
import guntur.core.system.com.repository.MainRepository;
import guntur.core.system.com.ui.page.MainActivity;
import guntur.core.system.com.ui.page.MainPresenter;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Module
public class MainActivityModule {
    private MainActivity mainActivity;
    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(NetworkService networkService) {
        return new MainRepository(networkService);
    }

    @Provides
    @ActivityScope
    DaoSession provideDaoSession() {
        String DbName = Constant.DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(CoreApp.get(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        Log.d("DB PATH", db.getPath());
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


    @Provides
    @ActivityScope
    DataModel provideDataModel(DaoSession daoSession){
        return new DataModel(daoSession);
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new MainPresenter(mainRepository, dataModel);
    }
}
