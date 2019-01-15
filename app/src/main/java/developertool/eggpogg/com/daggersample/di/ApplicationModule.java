package developertool.eggpogg.com.daggersample.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import developertool.eggpogg.com.daggersample.MainActivity;

@Module
public class ApplicationModule {

    private final Application mApp;

    public ApplicationModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApp;
    }


    @Provides
    @Singleton
    MainActivity.Repository provideRepository(MainActivity.Repository repository) {
        return repository;
    }

}
