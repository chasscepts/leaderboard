package com.chass.gadsleaderboard.models;

import com.chass.gadsleaderboard.enums.TopLearnerTypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopLearnerHours extends TopLearner {

  private int hours;

  public int getHours() {
    return hours;
  }

  public TopLearnerHours(int hours, String name, String country, String badgeUrl) {
    super(TopLearnerTypes.HOURS, name, country, badgeUrl);
    this.hours = hours;
  }

  private static TopLearnerHours parse(JSONObject entity){
    TopLearnerHours learner = null;
    try {
      learner = new TopLearnerHours(
        entity.getInt(HOURS),
        entity.getString(NAME),
        entity.getString(COUNTRY),
        entity.getString(BADGE_URL)
      );
    }
    catch (Exception ex){
      ex.printStackTrace();
    }
    return learner;
  }

  public static List<TopLearnerHours> parse(String text){
    List<TopLearnerHours> learners = new ArrayList<>();
    try {
      JSONArray array = new JSONArray(text);
      int length = array.length();
      for(int i = 0; i < length; i++){
        TopLearnerHours learner = parse(array.getJSONObject(i));
        if(learner != null){
          learners.add(learner);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return learners;
  }

  public static List<TopLearner> parseLearners(String text){
    List<TopLearner> learners = new ArrayList<>();
    try {
      JSONArray array = new JSONArray(text);
      int length = array.length();
      for(int i = 0; i < length; i++){
        TopLearnerHours learner = parse(array.getJSONObject(i));
        if(learner != null){
          learners.add(learner);
        }
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return learners;
  }
}
