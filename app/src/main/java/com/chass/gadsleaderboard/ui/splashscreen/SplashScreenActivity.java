package com.chass.gadsleaderboard.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.chass.gadsleaderboard.MainActivity;
import com.chass.gadsleaderboard.R;
import com.chass.gadsleaderboard.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
  ActivitySplashScreenBinding B;
  SplashScreenViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    B = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
    viewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
    setupViewModel();
  }

  private void setupViewModel() {
    B.setViewModel(viewModel);
    viewModel.isLoaded().observe(this, loaded -> {
      //This is set by the viewModel when loading has finished
      if(loaded){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.HOURS_TAG, viewModel.getHoursResponse());
        intent.putExtra(MainActivity.SKILLS_IQ_TAG, viewModel.getSkillIqResponse());
        startActivity(intent);
        finish();   //We don't want to come back to this screen when someone presses the back button
      }
    });
    viewModel.load();
  }
}
