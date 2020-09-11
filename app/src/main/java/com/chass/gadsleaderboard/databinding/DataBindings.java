package com.chass.gadsleaderboard.databinding;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.chass.gadsleaderboard.R;
import com.chass.gadsleaderboard.enums.FormStates;
import com.chass.gadsleaderboard.enums.TopLearnerTypes;
import com.chass.gadsleaderboard.models.TopLearner;
import com.chass.gadsleaderboard.models.TopLearnerHours;
import com.chass.gadsleaderboard.models.TopLearnerSkillIQ;

public class DataBindings {
  @BindingAdapter("badge")
  // We know there are only 2 badges - for hours and skill iq
  // Instead of loading the learners badgeUrl using a library such as Picasso,
  // we use local resources and use the learner's type to determine the badge to show.
  public static void setBadge(ImageView img, TopLearner learner){
    if (learner.getType() == TopLearnerTypes.SKILL_IQ) {
      img.setImageResource(R.drawable.skill_iq_trimmed);
    } else {
      img.setImageResource(R.drawable.top_learner);
    }
  }

  @BindingAdapter("rider")
  public static void setRider(TextView tv, TopLearner learner){
    if(learner.getType() == TopLearnerTypes.SKILL_IQ){
      TopLearnerSkillIQ learnerSkillIQ = (TopLearnerSkillIQ) learner;tv.setText(tv.getResources()
          .getString(R.string.iq_rider_format, learnerSkillIQ.getScore(), learner.getCountry()));
    }
    else {
      TopLearnerHours learnerHours = (TopLearnerHours)learner;
      tv.setText(tv.getResources().getString(
          R.string.hours_rider_format, learnerHours.getHours(), learner.getCountry()));
    }
  }

  @BindingAdapter("busyMessage")
  public static void setBusyMessage(ViewGroup viewGroup, String msg){
    if(TextUtils.isEmpty(msg)){
      viewGroup.setVisibility(View.GONE);
      return;
    }
    TextView tv = viewGroup.findViewById(R.id.message);
    if(tv != null){
      tv.setText(msg);
    }
    viewGroup.setVisibility(View.VISIBLE);
  }

  @BindingAdapter("submitResultState")
  public static void setSubmitResultState(ImageView img, FormStates state){
    if(state == FormStates.SUCCESS){
      img.setImageResource(R.drawable.ic_check_circle_green_24dp);
    }
    else{
      img.setImageResource(R.drawable.ic_warning_red_24dp);
    }
  }

  @BindingAdapter("anim")
  public static void applyAnimation(View view, int anim){
    Animation myFadeInAnimation = AnimationUtils.loadAnimation(view.getContext(), anim);
    view.startAnimation(myFadeInAnimation);
  }

  @BindingAdapter({"container", "toggleVisibility"})
  public static void toggleVisibility(View view, ViewGroup container, boolean show){

  }
}
