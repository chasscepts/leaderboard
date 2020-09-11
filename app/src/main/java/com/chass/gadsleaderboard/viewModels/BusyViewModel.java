package com.chass.gadsleaderboard.viewModels;

import androidx.databinding.ObservableField;

public class BusyViewModel {
  private static final String DEFAULT_MESSAGE = "Application busy. Please wait ...";
  public final ObservableField<String> message = new ObservableField<>();

  public void show(){
    message.set(DEFAULT_MESSAGE);
  }

  public void show(String msg){
    message.set(msg);
  }

  public void clear(){
    message.set(null);
  }

  public boolean isOn(){
    return message.get() != null;
  }
}
