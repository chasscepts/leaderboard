package com.chass.gadsleaderboard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chass.gadsleaderboard.databinding.FragmentMainBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";
  public static final String RESPONSE = "RESPONSE";

  private PageViewModel pageViewModel;

  static PlaceholderFragment newInstance(int index, String response) {
    PlaceholderFragment fragment = new PlaceholderFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ARG_SECTION_NUMBER, index);
    bundle.putString(RESPONSE, response);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    //pageViewModel = new ViewModelProvider(getViewModelStore(),
        //ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
        //.get(PageViewModel.class);
    //pageViewModel.setupAdapters(mainViewModel);
    pageViewModel = new ViewModelProvider(getViewModelStore(),
      ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
      .get(PageViewModel.class);
    int index = 1;
    String response = null;
    if (getArguments() != null) {
      Bundle bundle = getArguments();
      index = bundle.getInt(ARG_SECTION_NUMBER);
      response = bundle.getString(RESPONSE);
    }
    pageViewModel.setup(index, response);
  }

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
    FragmentMainBinding B = FragmentMainBinding.inflate(inflater, container, false);
    B.topLearnersList.setLayoutManager(new LinearLayoutManager(B.topLearnersList.getContext()));
    B.topLearnersList.setAdapter(pageViewModel.getAdapter());
    return B.getRoot();
  }
}