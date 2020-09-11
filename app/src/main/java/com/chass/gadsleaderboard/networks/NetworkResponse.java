package com.chass.gadsleaderboard.networks;

public class NetworkResponse {

  public int getCode() {
    return code;
  }

  public String getText() {
    return text;
  }

  public NetworkResponse(int code, String text) {
    this.code = code;
    this.text = text;
  }

  public String toString(){
    return code + ":: " + text;
  }

  private int code;
  private String text;
}
