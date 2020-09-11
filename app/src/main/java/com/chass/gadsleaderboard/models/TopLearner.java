package com.chass.gadsleaderboard.models;

import com.chass.gadsleaderboard.enums.TopLearnerTypes;

public class TopLearner {
  protected static final String HOURS = "hours";
  protected static final String SCORE = "score";
  protected static final String NAME = "name";
  protected static final String COUNTRY = "country";
  protected static final String BADGE_URL = "badgeUrl";

  private String name, country, badgeUrl;
  private TopLearnerTypes type;

  public TopLearnerTypes getType() {
    return type;
  }

  public void setType(TopLearnerTypes type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public String getBadgeUrl() {
    return badgeUrl;
  }

  public void setBadgeUrl(String badgeUrl) {
    this.badgeUrl = badgeUrl;
  }

  public TopLearner(TopLearnerTypes type, String name, String country, String badgeUrl) {
    this.type = type;
    this.name = name;
    this.country = country;
    this.badgeUrl = badgeUrl;
  }
}
