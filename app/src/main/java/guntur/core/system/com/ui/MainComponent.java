package guntur.core.system.com.ui;

import dagger.Subcomponent;
import guntur.core.system.com.di.scope.ActivityScope;
import guntur.core.system.com.ui.page.MainActivity;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainComponent {
    MainActivity inject(MainActivity mainActivity);
}
