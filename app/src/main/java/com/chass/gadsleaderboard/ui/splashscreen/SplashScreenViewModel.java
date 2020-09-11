package com.chass.gadsleaderboard.ui.splashscreen;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chass.gadsleaderboard.R;
import com.chass.gadsleaderboard.networks.HttpGetClient;
import com.chass.gadsleaderboard.utils.ApiUtils;
import com.chass.gadsleaderboard.utils.ExceptionCodes;
import com.chass.gadsleaderboard.utils.ThreadUtil;
import com.chass.gadsleaderboard.utils.Utils;

import java.net.HttpURLConnection;

public class SplashScreenViewModel extends ViewModel {
  private MutableLiveData<Boolean> loaded = new MutableLiveData<>();
  public final ObservableBoolean hasError = new ObservableBoolean();
  private String hoursResponse = null, skillIqResponse = null;
  private boolean minTimeBeforeLoad = false;

  public final int fadeIn = R.anim.fade_in;
  public final int fadeInAndOut = R.anim.fade_in_and_out;

  LiveData<Boolean> isLoaded(){
    return loaded;
  }

  String getHoursResponse(){
    return hoursResponse;
  }

  String getSkillIqResponse(){
    return skillIqResponse;
  }

  private HttpGetClient hoursClient, iqClient;

  /**
   * To ensure a smooth transition to MainActivity we use a 10s timer to ensure the SplashScreen
   * remains displayed for at least 10s.
   */
  private void delayLoading() {
    ThreadUtil util = ThreadUtil.getInstance();
    util.getExecutor().execute(() -> {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      util.post(() -> {
        minTimeBeforeLoad = true;
        checkLoadStatus();
      });
    });
  }

  /**
   * loaded is set to true when both network requests has finished and 10s has passed.
   */
  private void checkLoadStatus(){
    if(minTimeBeforeLoad && hoursResponse != null && skillIqResponse != null){
      loaded.setValue(true);
    }
  }

  /**
   * Make a network request to load the Top Learners in the hours category
   */
  private void loadTopLearnersHours() {
    hoursClient = new HttpGetClient();
    hoursClient.setOnNetworkResponseListener(response -> {
      switch (response.getCode()){
        case HttpURLConnection.HTTP_OK:
          hoursResponse = response.getText();
          hoursClient.removeOnNetworkResponseListener();
          hoursClient = null;
          checkLoadStatus();
          return;
        case ExceptionCodes.EXCEPTION:
        case ExceptionCodes.IO_EXCEPTION:
        default:
          hasError.set(true);
          Utils.stub("PageViewModel", response.toString());
      }
      hoursClient.removeOnNetworkResponseListener();
      hoursClient = null;
    });
    hoursClient.execute(ApiUtils.getTopHoursUrl());
  }

  /**
   * Make a network request to load the Top Learners in the skill iq category
   */
  private void loadTopLearnersSkillIQ() {
    iqClient = new HttpGetClient();
    iqClient.setOnNetworkResponseListener(response -> {
      switch (response.getCode()){
        case HttpURLConnection.HTTP_OK:
          skillIqResponse = response.getText();
          iqClient.removeOnNetworkResponseListener();
          iqClient = null;
          checkLoadStatus();
          return;
        case ExceptionCodes.EXCEPTION:
        case ExceptionCodes.IO_EXCEPTION:
        default:
          hasError.set(true);
          Utils.stub("PageViewModel", response.toString());
      }
      iqClient.removeOnNetworkResponseListener();
      iqClient = null;
    });
    iqClient.execute(ApiUtils.getTopSkillsUrl());
  }

  /**
   * We can easily load inside xtor, but network requests may fail and we may want to reload it.
   */
  public void load(){
    if(!minTimeBeforeLoad){
      delayLoading();
    }
    if(hoursResponse == null){
      loadTopLearnersHours();
    }
    if(skillIqResponse == null){
      loadTopLearnersSkillIQ();
    }
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    if(hoursClient != null){
      hoursClient.removeOnNetworkResponseListener();
      hoursClient = null;
    }
    if(iqClient != null){
      iqClient.removeOnNetworkResponseListener();
      iqClient = null;
    }
  }
}
