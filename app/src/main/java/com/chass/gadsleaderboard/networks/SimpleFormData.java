package com.chass.gadsleaderboard.networks;

public class SimpleFormData {
  private StringBuilder sb;

  public void append(String key, String value){
    if(sb == null){
      sb = new StringBuilder();
    }
    else {
      sb.append("&");
    }
    sb.append(key)
    .append('=')
    .append(value);
  }

  public String getSimpleData() {
    return sb == null? "" : sb.toString();
  }
}
