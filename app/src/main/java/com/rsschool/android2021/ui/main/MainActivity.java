package com.rsschool.android2021.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rsschool.android2021.App;
import com.rsschool.android2021.R;
import com.rsschool.android2021.navigation.core.NavStorage;
import com.rsschool.android2021.navigation.FragmentRouter;
import com.rsschool.android2021.navigation.core.Router;
import com.rsschool.android2021.ui.destinations.Screens;

import static com.rsschool.android2021.ui.extentions.BundleConstantsKt.ROUTER_TAG;

public class MainActivity extends AppCompatActivity {

    public volatile int previousResult = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRouter();
    }

    private void initRouter() {
        Router navigator = new FragmentRouter(getSupportFragmentManager(), R.id.container, ROUTER_TAG);
        getRoutersStorage().addRouter(ROUTER_TAG, navigator);
        navigator.navigate(Screens.MAIN_FRAGMENT.getDestination(), null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getRoutersStorage().removeRouter(ROUTER_TAG);
    }

    private NavStorage getRoutersStorage() {
        return ((App) getApplication()).getRoutersStorage();
    }
}
