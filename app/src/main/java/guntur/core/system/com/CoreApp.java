package guntur.core.system.com;

import android.app.Application;

import guntur.core.system.com.di.component.AppComponent;
import guntur.core.system.com.di.component.DaggerAppComponent;
import guntur.core.system.com.di.module.AppModule;
import guntur.core.system.com.di.module.NetworkModule;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class CoreApp extends Application {
    private AppComponent appComponent = createAppComponent();

    private static CoreApp instance;

    public static CoreApp get() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    protected AppComponent createAppComponent() {
        return appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return  appComponent;
    }
}
