package com.chass.gadsleaderboard.ui.main;

import androidx.lifecycle.ViewModel;

import com.chass.gadsleaderboard.models.TopLearner;
import com.chass.gadsleaderboard.models.TopLearnerHours;
import com.chass.gadsleaderboard.models.TopLearnerSkillIQ;
import com.chass.gadsleaderboard.utils.ThreadUtil;

import java.util.List;

public class PageViewModel extends ViewModel {
  TopLearnersAdapter adapter;

  public TopLearnersAdapter getAdapter() {
    return adapter;
  }

  public void setup(int tabIndex, String response){
    adapter = new TopLearnersAdapter();
    ThreadUtil util = ThreadUtil.getInstance();
    util.getExecutor().execute(() -> {
      List<TopLearner> learners = tabIndex == 2? TopLearnerSkillIQ.parseLearners(response) :
          TopLearnerHours.parseLearners(response);
      util.post(() -> adapter.load(learners));
    });
  }
}
