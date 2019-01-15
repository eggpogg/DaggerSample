package developertool.eggpogg.com.daggersample.di;

import dagger.Subcomponent;
import developertool.eggpogg.com.daggersample.MainActivity;

@PerFragment
@Subcomponent(modules = {MogeFragmentModule.class})
public interface MogeFragmentComponent {
    void inject(MainActivity.MogeFragment mainFragment);
}
