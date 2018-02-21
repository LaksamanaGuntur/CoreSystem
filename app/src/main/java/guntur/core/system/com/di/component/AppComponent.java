package guntur.core.system.com.di.component;

import javax.inject.Singleton;

import dagger.Component;
import guntur.core.system.com.di.module.AppModule;
import guntur.core.system.com.di.module.NetworkModule;
import guntur.core.system.com.ui.MainActivityModule;
import guntur.core.system.com.ui.MainComponent;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {
    MainComponent plus(MainActivityModule mainActivityModule);
}
