package com.royce.avgarde.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.royce.avgarde.R;

import java.util.Objects;

public class GuideActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_guide);

        setFlowAnimation();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }
        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("What is crowdsourcing");
        sliderPage1.setDescription("The practice of obtaining information or input into a task or project by enlisting the services of a large number of people, either paid or unpaid, typically via the Internet");
        sliderPage1.setImageDrawable(R.drawable.logo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage1.setBgColor(getColor(R.color.colorPrimaryDark));
        }

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Data tagging for AI");
        sliderPage2.setDescription("The practice of obtaining information or input into a task or project by enlisting the services of a large number of people, either paid or unpaid, typically via the Internet");
        sliderPage2.setImageDrawable(R.drawable.logo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage2.setBgColor(getColor(R.color.black));
        }
        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Be a part");
        sliderPage3.setDescription("The practice of obtaining information or input into a task or project by enlisting the services of a large number of people, either paid or unpaid, typically via the Internet");
        sliderPage3.setImageDrawable(R.drawable.logo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sliderPage3.setBgColor(getColor(R.color.colorPrimary));
        }
        addSlide(AppIntroFragment.newInstance(sliderPage1));
        addSlide(AppIntroFragment.newInstance(sliderPage2));
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        showSkipButton(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
        startActivity(new Intent(this, SignupActivity.class));
        this.finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        startActivity(new Intent(this, SignupActivity.class));
        this.finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}
