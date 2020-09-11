package com.chass.gadsleaderboard.networks.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClientInstance {
  private static Retrofit retrofit;
  private static final String BASE_URL = //"https://docs.google.com/forms/d/e/";
                                          "http://chass.me.ht/";

  public static Retrofit getRetrofitInstance() {
    if (retrofit == null) {
      retrofit = new retrofit2.Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(ScalarsConverterFactory.create())
          .build();
    }
    return retrofit;
  }
}
