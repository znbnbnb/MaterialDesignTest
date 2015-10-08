package com.znb.materialdesigntest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.rubengees.introduction.IntroductionActivity;
import com.rubengees.introduction.IntroductionBuilder;
import com.rubengees.introduction.IntroductionConfiguration;
import com.rubengees.introduction.entity.Option;
import com.rubengees.introduction.entity.Slide;

import java.util.ArrayList;
import java.util.List;

public class IntroductionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        introduce();
    }

    private void introduce() {
        new IntroductionBuilder(this).withSlides(generateSlides()).withStyle(IntroductionBuilder.STYLE_FULLSCREEN)
                .withOnSlideChangedListener(new IntroductionConfiguration.OnSlideChangedListener() {
                    @Override
                    public void onSlideChanged(int from, int to) {
                        if (from == 0 && to == 1) {
                            if (ActivityCompat.checkSelfPermission(IntroductionTestActivity.this,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(IntroductionTestActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        12);
                            }
                        }
                    }
                })
                .introduceMyself();
    }

    private List<Slide> generateSlides() {
        List<Slide> result = new ArrayList<>();
        result.add(new Slide().withTitle("Some title").withDescription("Some description").
                withColorResource(android.R.color.holo_red_light).withImage(R.drawable.test2).
                withOption(new Option("Enable the 1")));
        result.add(new Slide().withTitle("Another title").withDescription("Another description")
                .withColorResource(android.R.color.holo_blue_light).withImage(R.drawable.test)
                .withOption(new Option("Enable the 2")));
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntroductionBuilder.INTRODUCTION_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            String result = "User chose: ";

            for (Option option : data.<Option>getParcelableArrayListExtra(IntroductionActivity.
                    OPTION_RESULT)) {
                result += option.getPosition() //The position of the Slide
                        + (option.isActivated() ? " enabled" : " disabled");
            }
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 12) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission was successfully granted!", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
