package developertool.eggpogg.com.daggersample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Named;

import developertool.eggpogg.com.daggersample.di.ActivityModule;
import developertool.eggpogg.com.daggersample.di.DaggerMainActivityComponent;
import developertool.eggpogg.com.daggersample.di.MainActivityComponent;
import developertool.eggpogg.com.daggersample.di.MogeFragmentModule;
import developertool.eggpogg.com.daggersample.di.PerActivity;

public class MainActivity extends AppCompatActivity {

    MainActivityComponent mMainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainActivityComponent = DaggerMainActivityComponent
                .builder()
                .applicationComponent(App.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
        mMainActivityComponent.inject(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MogeFragment(), "moge");
        fragmentTransaction.commit();
    }

    public static class HogeFragment extends Fragment {
        @Inject
        Presenter mPresenter;
    }

    public static class MogeFragment extends Fragment {

        @Inject
        Presenter mPresenter;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            ((MainActivity) getActivity()).mMainActivityComponent
                    .subMainFragmentComponent(new MogeFragmentModule(ServiceType.SHP, "hogehoge"))
                    .inject(this);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_moge, container, false);

            view.findViewById(R.id.text_view).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(getView().findViewById(R.id.fragment_container), mPresenter.getData(), Snackbar.LENGTH_LONG).show();
                }
            });

            return view;
        }
    }

    public static class Presenter {

        @Inject
        ServiceType mServiceType;

        @Inject
        UseCase mUseCase;

        @Inject
        UltManager mUltManager;

        @Inject
        public Presenter() {}

        public String getData() {
            return mUseCase.getData();
        }
    }

    @PerActivity
    public static class UseCase {

        @Inject
        Repository mRepository;

        @Inject
        UseCase() {}

        public String getData() {
            return mRepository.getData();
        }
    }

    public static class Repository {

        @Inject
        public Repository() {}

        public String getData() {
            return "RepositoryのgetDataが呼ばれたよ！";
        }
    }

    public static class UltManager {

        @Inject
        ServiceType mServiceType;

        @Inject
        @Named("uniqueId")
        String uniqueId;

        @Inject
        public UltManager() {}
    }

}
