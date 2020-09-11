package com.chass.gadsleaderboard.models;

import com.chass.gadsleaderboard.enums.TopLearnerTypes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopLearnerSkillIQ extends TopLearner {
  private int score;

  public int getScore() {
    return score;
  }

  public TopLearnerSkillIQ (int score, String name, String country, String badgeUrl) {
    super(TopLearnerTypes.SKILL_IQ, name, country, badgeUrl);
    this.score = score;
  }

  private static TopLearnerSkillIQ parse(JSONObject entity){
    TopLearnerSkillIQ learner = null;
    try {
      learner = new TopLearnerSkillIQ(
          entity.getInt(SCORE),
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

  public static List<TopLearnerSkillIQ> parse(String text){
    List<TopLearnerSkillIQ> learners = new ArrayList<>();
    try {
      JSONArray array = new JSONArray(text);
      int length = array.length();
      for(int i = 0; i < length; i++){
        TopLearnerSkillIQ learner = parse(array.getJSONObject(i));
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
        TopLearnerSkillIQ learner = parse(array.getJSONObject(i));
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
