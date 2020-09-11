package com.chass.gadsleaderboard.ui.submit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;

import com.chass.gadsleaderboard.R;
import com.chass.gadsleaderboard.databinding.ActivitySubmitBinding;
import com.chass.gadsleaderboard.databinding.BusyViewBinding;
import com.chass.gadsleaderboard.databinding.ConfirmFormSubmitBinding;
import com.chass.gadsleaderboard.databinding.SubmitFormBinding;
import com.chass.gadsleaderboard.databinding.SubmitResultViewBinding;
import com.chass.gadsleaderboard.enums.FormStates;

public class SubmitActivity extends AppCompatActivity {
  ActivitySubmitBinding B;
  private SubmitViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    B = DataBindingUtil.setContentView(this, R.layout.activity_submit);
    viewModel = new ViewModelProvider(this).get(SubmitViewModel.class);
    B.setViewModel(viewModel);
    setupBackNavigation();
    setupScenes();
  }

  private void setupScenes() {
    viewModel.getState().observe(this, state -> {
      Scene scene;
      switch (state){
        case CONFIRM:
          ConfirmFormSubmitBinding confirmFormSubmitBinding = ConfirmFormSubmitBinding.inflate(
              getLayoutInflater(), B.submitViewHolder, false
          );
          confirmFormSubmitBinding.setViewModel(viewModel);
          scene = new Scene(B.submitViewHolder, confirmFormSubmitBinding.getRoot());
          break;
        case SUCCESS:
        case FAILED:
          SubmitResultViewBinding submitResultViewBinding = SubmitResultViewBinding.inflate(
            getLayoutInflater(), B.submitViewHolder, false
          );
          submitResultViewBinding.setViewModel(viewModel);
          scene = new Scene(B.submitViewHolder, submitResultViewBinding.getRoot());
          break;
        case BUSY:
          BusyViewBinding busyViewBinding = BusyViewBinding.inflate(
              getLayoutInflater(), B.submitViewHolder, false);
          busyViewBinding.setViewModel(viewModel.busy);
          scene = new Scene(B.submitViewHolder, busyViewBinding.getRoot());
          break;
        default:
          SubmitFormBinding submitFormBinding = SubmitFormBinding.inflate(
              getLayoutInflater(), B.submitViewHolder, false);
          submitFormBinding.setViewModel(viewModel.submitFormViewModel);
          scene = new Scene(B.submitViewHolder, submitFormBinding.getRoot());
          break;
      }

      TransitionSet transition = (TransitionSet) TransitionInflater.from(this).inflateTransition(
          R.transition.view_swap);
      TransitionManager.go(scene, transition);
    });
  }

  private void setupBackNavigation() {
    B.backBtn.setOnClickListener(view -> {
      if(viewModel.getState().getValue() == FormStates.FILL){
        finish();
      }
      else {
        viewModel.setState(FormStates.FILL);
      }
    });
  }
}
