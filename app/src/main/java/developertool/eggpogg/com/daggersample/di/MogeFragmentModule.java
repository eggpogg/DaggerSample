package developertool.eggpogg.com.daggersample.di;


import android.app.Service;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import developertool.eggpogg.com.daggersample.MainActivity;
import developertool.eggpogg.com.daggersample.ServiceType;

@Module
public class MogeFragmentModule {

    ServiceType mServiceType;
    String uniqueId;

    public MogeFragmentModule(ServiceType serviceType, String uniqueId) {
        mServiceType = serviceType;
        this.uniqueId = uniqueId;
    }

    @Provides
    ServiceType provideServiceType() {
        return mServiceType;
    }

    @Provides
    @Named("uniqueId")
    String provideUniqueId() {
        return uniqueId;
    }
}
