package com.chass.gadsleaderboard.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chass.gadsleaderboard.databinding.TopLearnerItemViewBinding;
import com.chass.gadsleaderboard.models.TopLearner;

import java.util.List;

public class TopLearnersAdapter extends
    RecyclerView.Adapter<TopLearnersAdapter.TopLearnerHolder> {

  private List<TopLearner> learners;

  @NonNull
  @Override
  public TopLearnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    TopLearnerItemViewBinding binding = TopLearnerItemViewBinding.inflate(
        LayoutInflater.from(parent.getContext()), parent, false);
    return new TopLearnerHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull TopLearnerHolder holder, int position) {
    holder.bind(learners.get(position));
  }

  @Override
  public int getItemCount() {
    return learners == null? 0 : learners.size();
  }

  public void load(List<TopLearner> learners){
    this.learners = learners;
    notifyDataSetChanged();
  }

  static class TopLearnerHolder extends RecyclerView.ViewHolder{
    private TopLearnerItemViewBinding B;

    public TopLearnerHolder(TopLearnerItemViewBinding binding){
      super(binding.getRoot());
      B = binding;
    }

    public void bind(TopLearner learner){
      B.setLearner(learner);
    }
  }
}
