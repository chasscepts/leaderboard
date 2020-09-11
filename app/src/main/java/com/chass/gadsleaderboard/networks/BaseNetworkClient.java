package com.chass.gadsleaderboard.networks;

import android.os.Handler;

import com.chass.gadsleaderboard.utils.ExceptionCodes;
import com.chass.gadsleaderboard.utils.ThreadUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import javax.net.ssl.SSLPeerUnverifiedException;

public abstract class BaseNetworkClient<T> {

  protected INetworkResponseListener responseListener;

  public void setOnNetworkResponseListener(INetworkResponseListener listener){
    this.responseListener = listener;
  }

  public void removeOnNetworkResponseListener(){
    this.responseListener = null;
  }

  public void execute(T args){
    final Handler handler = new Handler();
    ThreadUtil.getInstance().getExecutor().execute(() -> {
      final NetworkResponse response = doInBackground(args);
      handler.post(() -> {
        if(responseListener != null){
          responseListener.onNetworkResponse(response);
        }
      });
    });
  }

  /**
   * Cancels network request
   */
  //TODO: implement
  public void cancel(){

  }

  protected abstract HttpURLConnection getConnection(T args) throws IOException;

  private NetworkResponse doInBackground(T args){
    NetworkResponse response;
    HttpURLConnection conn = null;
    try{
      conn = getConnection(args);
      conn.connect();

      StringBuilder result = new StringBuilder();
      int responseCode = conn.getResponseCode();
      BufferedReader br;
      switch (responseCode){
        case HttpURLConnection.HTTP_OK:
        case HttpURLConnection.HTTP_ACCEPTED:
        case HttpURLConnection.HTTP_CREATED:
          br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          break;
        default:
          br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      String line;
      while ((line = br.readLine()) != null) {
        result.append(line);
      }
      response = new NetworkResponse(responseCode, result.toString());
    }
    catch (MalformedURLException ex){
      response = new NetworkResponse(ExceptionCodes.MALFORMED_URL_EXCEPTION, ex.getMessage());
    }
    catch (ProtocolException ex){
      response = new NetworkResponse(ExceptionCodes.PROTOCOL_EXCEPTION, ex.getMessage());
    }
    catch (SSLPeerUnverifiedException ex){
      response = new NetworkResponse(ExceptionCodes.SSL_PEER_UN_VERIFIED_EXCEPTION, ex.getMessage());
    }
    catch (IOException ex){
      response = new NetworkResponse(ExceptionCodes.IO_EXCEPTION, ex.getMessage());
    }
    catch (IllegalStateException ex){
      response = new NetworkResponse(ExceptionCodes.ILLEGAL_STATE_EXCEPTION, ex.getMessage());
    }
    catch (IllegalArgumentException ex){
      response = new NetworkResponse(ExceptionCodes.ILLEGAL_ARGUMENT_EXCEPTION, ex.getMessage());
    }
    catch (SecurityException ex){
      response = new NetworkResponse(ExceptionCodes.SECURITY_EXCEPTION, ex.getMessage());
    }
    catch (Exception ex){
      response = new NetworkResponse(ExceptionCodes.EXCEPTION, ex.getMessage());
    }
    finally {
      if(conn != null){
        conn.disconnect();
      }
    }
    return response;
  }

  //TODO remove
  protected void mockRequest(NetworkResponse response){
    final Handler handler = new Handler();
    ThreadUtil.getInstance().getExecutor().execute(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      handler.post(() -> {
        if(responseListener != null){
          responseListener.onNetworkResponse(response);
        }
      });
    });
  }
}
