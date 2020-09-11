package com.chass.gadsleaderboard.utils;

import android.util.Log;

public class Utils {
  private static void log(String msg){
    Log.d("stub", msg);
  }

  public static void stub(String tag, String msg){
    log(tag + "::" + msg);
  }
}
