package com.rsschool.android2021.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.rsschool.android2021.R;
import com.rsschool.android2021.ui.randomresult.SecondFragment;

public class MainActivity extends AppCompatActivity implements FirstFragment.GenerationCallback, SecondFragment.BackButtonCallback {

    private int previousResult = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openMainFragment();
    }

    private void openMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, FirstFragment.newInstance());
        transaction.commit();
    }

    private void openResultFragment(int min, int max) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, SecondFragment.newInstance(min, max));
        transaction.addToBackStack(SecondFragment.class.getSimpleName());
        transaction.commit();
    }

    @Override
    public void onGenerateButtonClicked(int min, int max) {
        openResultFragment(min, max);
    }

    @Override
    public int getPreviousResult() {
        return previousResult;
    }

    @Override
    public void onBackPressedCall(int result) {
        previousResult = result;
        getSupportFragmentManager().popBackStack();
    }
}
