package com.chass.gadsleaderboard.utils;

import android.net.Uri;

import com.chass.gadsleaderboard.utils.Utils;

import java.net.URL;

public class ApiUtils {
  private static void log(String msg){
    Utils.stub("ApiUtil", msg);
  }

  public static final String TOP_LEARNERS_BASE_URL = "https://gadsapi.herokuapp.com";

  private static URL getTopLearnersUrl(String path){
    URL url = null;
    Uri uri = Uri.parse(TOP_LEARNERS_BASE_URL + path);
    try{
      url = new URL(uri.toString());
    }
    catch (Exception e){
      e.printStackTrace();
      log(e.getMessage());
    }
    return url;
  }

  public static URL getTopHoursUrl(){
    return getTopLearnersUrl("/api/hours");
  }

  public static URL getTopSkillsUrl(){
    return getTopLearnersUrl("/api/skilliq");
  }

  public static URL getSubmitUrl(){
    URL url = null;
    //Uri uri = Uri.parse("http://chass.me.ht/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse");
    Uri uri = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse");
    try{
      url = new URL(uri.toString());
    }
    catch (Exception e){
      e.printStackTrace();
      log(e.getMessage());
    }
    return url;
  }
}
