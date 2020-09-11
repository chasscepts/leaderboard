package com.chass.gadsleaderboard.ui.submit;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chass.gadsleaderboard.enums.FormStates;
import com.chass.gadsleaderboard.interfaces.IFormSubmittedListener;
import com.chass.gadsleaderboard.networks.retrofit.GetDataService;
import com.chass.gadsleaderboard.networks.retrofit.RetrofitClientInstance;
import com.chass.gadsleaderboard.utils.Utils;
import com.chass.gadsleaderboard.viewModels.BusyViewModel;
import com.chass.gadsleaderboard.viewModels.SubmitFormViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitViewModel extends ViewModel implements IFormSubmittedListener {

  private void log(String msg){
    Utils.stub("SubmitViewModel", msg);
  }

  public final BusyViewModel busy = new BusyViewModel();
  //public final ObservableField<FormStates> state;
  public final SubmitFormViewModel submitFormViewModel;

  private MutableLiveData<FormStates> state = new MutableLiveData<>();

  public LiveData<FormStates> getState(){
    return state;
  }

  public void setState(FormStates state){
    this.state.setValue(state);
  }

  public SubmitViewModel(){
    //state = new ObservableField<>();
    state.setValue(FormStates.FILL);
    submitFormViewModel = new SubmitFormViewModel(this);
    submitFormViewModel.fillTestData();
  }

  public void confirmSubmission(){
    busy.show("Submitting Form. Please wait ...");
    state.setValue(FormStates.BUSY);
    GetDataService service = RetrofitClientInstance.getRetrofitInstance()
        .create(GetDataService.class);
    Call<String> call = service.submit(submitFormViewModel.getFirstname(),
        submitFormViewModel.getLastname(),
        submitFormViewModel.getEmail(), submitFormViewModel.getLink());
    call.enqueue(new Callback<String>() {
      @Override
      public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        state.setValue(FormStates.SUCCESS);
        busy.clear();
        log(response.body());
      }

      @Override
      public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        state.setValue(FormStates.FAILED);
        busy.clear();
        log(t.getMessage());
        t.printStackTrace();
      }
    });
  }

  public void cancelSubmission(){
    state.setValue(FormStates.FILL);
  }

  @Override
  public void onSubmit() {
    state.setValue(FormStates.CONFIRM);
  }
}
