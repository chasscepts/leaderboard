package com.chass.gadsleaderboard.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chass.gadsleaderboard.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

  @StringRes
  private static final int[] TAB_TITLES = new int[]{
      R.string.ucf_Learning_leaders, R.string.skill_iq_leaders};
  private final Context mContext;
  private final String hoursResponse, skillIQResponse;

  public SectionsPagerAdapter(Context context, FragmentManager fm, String hoursResponse,
                              String skillIQResponse) {
    super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    mContext = context;
    this.hoursResponse = hoursResponse;
    this.skillIQResponse = skillIQResponse;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    // getItem is called to instantiate the fragment for the given page.
    // Return a PlaceholderFragment (defined as a static inner class below).
    int tabIndex = position + 1;
    String response = tabIndex == 2? skillIQResponse : hoursResponse;
    return PlaceholderFragment.newInstance(tabIndex, response);
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    return mContext.getResources().getString(TAB_TITLES[position]);
  }

  @Override
  public int getCount() {
    // Show 2 total pages.
    return 2;
  }
}