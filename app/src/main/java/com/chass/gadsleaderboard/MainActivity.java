package com.chass.gadsleaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.chass.gadsleaderboard.ui.main.SectionsPagerAdapter;
import com.chass.gadsleaderboard.ui.submit.SubmitActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
  public static final String HOURS_TAG = "com.chass.gadsleaderboard.HOURS_TAG";
  public static final String SKILLS_IQ_TAG = "com.chass.gadsleaderboard.SKILLS_IQ_TAG";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupViewModel();
    setupSubmitButton();
  }

  private void setupTabs(String hoursResponse, String skillIQResponse) {
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(
        this, getSupportFragmentManager(), hoursResponse, skillIQResponse);
    ViewPager viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
  }

  private void setupViewModel() {
    Intent intent = getIntent();
    if(intent == null) return;
    String hoursResponse = intent.getStringExtra(HOURS_TAG);
    String skillIqResponse = intent.getStringExtra(SKILLS_IQ_TAG);
    setupTabs(hoursResponse, skillIqResponse);
  }

  private void setupSubmitButton() {
    Button btn = findViewById(R.id.submit_btn);
    btn.setOnClickListener(view -> startActivity(new Intent(this, SubmitActivity.class)));
  }
}