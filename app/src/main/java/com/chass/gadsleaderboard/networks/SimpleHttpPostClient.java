package com.chass.gadsleaderboard.networks;

import com.chass.gadsleaderboard.utils.Utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleHttpPostClient extends BaseNetworkClient<URL> {
  private SimpleFormData data;

  public SimpleHttpPostClient(SimpleFormData data){
    this.data = data;
  }

  @Override
  public void execute(URL args){
    if(data == null) return;
    super.execute(args);
  }

  @Override
  protected HttpURLConnection getConnection(URL url) throws IOException {
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    urlConnection.setRequestMethod("POST");
    OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());

    BufferedWriter writer = new BufferedWriter(
        new OutputStreamWriter(outputStream, "UTF-8"));
    writer.write(data.getSimpleData());
    writer.flush();
    writer.close();
    outputStream.close();
    return urlConnection;
  }
}
