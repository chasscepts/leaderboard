package com.chass.gadsleaderboard.networks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetClient extends BaseNetworkClient<URL> {
  @Override
  protected HttpURLConnection getConnection(URL url) throws IOException {
    return (HttpURLConnection) url.openConnection();
  }
}
