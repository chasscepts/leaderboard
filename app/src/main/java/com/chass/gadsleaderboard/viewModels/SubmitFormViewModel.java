package com.chass.gadsleaderboard.viewModels;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.chass.gadsleaderboard.BR;
import com.chass.gadsleaderboard.interfaces.IFormSubmittedListener;

public class SubmitFormViewModel extends BaseObservable {
  private IFormSubmittedListener listener;

  private String firstname, lastname, email, link;
  private boolean firstnameValid, lastnameValid, emailValid, linkValid, valid;

  @Bindable
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String value) {
    if(TextUtils.equals(firstname, value)) return;
    firstname = value;
    notifyPropertyChanged(BR.firstname);
    setFirstnameValid();
  }

  @Bindable
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String value) {
    if(TextUtils.equals(lastname, value)) return;
    lastname = value;
    notifyPropertyChanged(BR.lastname);
    setLastnameValid();
  }

  @Bindable
  public String getEmail() {
    return email;
  }

  public void setEmail(String value) {
    if(TextUtils.equals(email, value)) return;
    email = value;
    notifyPropertyChanged(BR.email);
    setEmailValid();
  }

  @Bindable
  public String getLink() {
    return link;
  }

  public void setLink(String value) {
    if(TextUtils.equals(link, value)) return;
    link = value;
    notifyPropertyChanged(BR.link);
    setLinkValid();
  }

  public boolean isFirstnameValid() {
    return firstnameValid;
  }

  private void setFirstnameValid() {
    boolean valid = !TextUtils.isEmpty(firstname);
    if(valid == firstnameValid) return;
    firstnameValid = valid;
    setValid();
  }

  public boolean isLastnameValid() {
    return lastnameValid;
  }

  private void setLastnameValid() {
    boolean valid = !TextUtils.isEmpty(lastname);
    if(valid == lastnameValid) return;
    lastnameValid = valid;
    setValid();
  }

  public boolean isEmailValid() {
    return emailValid;
  }

  private void setEmailValid() {
    boolean valid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
    if(valid == emailValid) return;
    emailValid = valid;
    setValid();
  }

  public boolean isLinkValid() {
    return linkValid;
  }

  private void setLinkValid() {
    boolean valid = Patterns.WEB_URL.matcher(link).matches();
    if(valid == linkValid) return;
    linkValid = valid;
    setValid();
  }

  public boolean isValid(){
    return valid;
  }

  private void setValid(){
    boolean valid = firstnameValid && lastnameValid && emailValid && linkValid;
    if(valid == this.valid) return;
    this.valid = valid;
  }

  public SubmitFormViewModel(@NonNull IFormSubmittedListener listener) {
    this.listener = listener;
  }

  public void submit() {
    if (valid) {
      listener.onSubmit();
    }
    //else {
      //we show a message
    //}
  }

  public void fillTestData(){
    setFirstname("Jerry");
    setLastname("Coleman");
    setEmail("sindrarl@gmail.com");
    setLink("https://github.com/talcum/Leaderboard");
  }
}
