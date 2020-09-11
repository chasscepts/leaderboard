package com.chass.gadsleaderboard.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
  private final int NUMBER_OF_THREADS = 4;
  private final ExecutorService executor;
  private final Handler mainThreadHandler;

  public ExecutorService getExecutor(){
    return executor;
  }

  public Handler getMainThreadHandler(){
    return mainThreadHandler;
  }

  private ThreadUtil(){
    executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //TODO: Which threads do not have Looper and how do we get mainLooper from such Threads
    mainThreadHandler = new Handler(Looper.getMainLooper());
  }

  private static final Object instanceLock = new Object();
  private static ThreadUtil instance;

  public static ThreadUtil getInstance(){
    if(instance == null){
      synchronized (instanceLock){
        if(instance == null){
          instance = new ThreadUtil();
        }
      }
    }
    return instance;
  }

  public void post(Runnable runnable){
    mainThreadHandler.post(runnable);
  }
}
