package developertool.eggpogg.com.daggersample;

import android.app.Application;

import developertool.eggpogg.com.daggersample.di.ApplicationComponent;
import developertool.eggpogg.com.daggersample.di.ApplicationModule;
import developertool.eggpogg.com.daggersample.di.DaggerApplicationComponent;

public class App extends Application {

    private static App mApp;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static App getInstance() {
        return mApp;
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApp.mApplicationComponent;
    }
}
