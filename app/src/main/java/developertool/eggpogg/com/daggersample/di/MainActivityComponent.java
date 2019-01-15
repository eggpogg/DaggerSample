package developertool.eggpogg.com.daggersample.di;

import dagger.Component;
import developertool.eggpogg.com.daggersample.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface MainActivityComponent extends ActivityComponent {
    MogeFragmentComponent subMainFragmentComponent(MogeFragmentModule mainFragmentModule);
    void inject(MainActivity mainActivity);
}
