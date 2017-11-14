package com.example.edaibu.airpurgelayoutviewdemo;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.wx.airpurgeview.AirPurgeLayoutView;
import com.wx.airpurgeview.onPanListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ValueAnimator animator = new ValueAnimator();
    private int mCurIndex1 = 0;
    private AirPurgeLayoutView mAirPurgeView;
    private Random random;
    private int colorIndex;
    private int[] bgColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAirPurgeView = findViewById(R.id.airpurge_view);
        Button btnMode1 = findViewById(R.id.btn9);

        random = new Random();
        bgColors = new int[]{0xFFDC143C, 0xFFFF00FF, 0xFF9400D3, 0xFF7B68EE, 0xFF0000FF, 0xFF00BFFF};
        mAirPurgeView.setBackgroundColor(bgColors[0]);
        colorIndex = 0;

        btnMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAirPurgeView.setCenterTitle(String.valueOf(521));
                mCurIndex1 = -1;
                mAirPurgeView.setBackgroundColor(bgColors[0]);
                animator.setIntValues(521,32);
                animator.setDuration(5000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int value = (int) valueAnimator.getAnimatedValue();
                        Log.e("MainActivity","******value = "+value);
                        mAirPurgeView.setCenterTitle(String.valueOf(value));
                        if (value>=480 && value<600) {
                            if (mCurIndex1 != 0) {
                                mCurIndex1 = 0;
                                mAirPurgeView.setBackgroundColor(bgColors[0],1000);
                                mAirPurgeView.setBottomTitle("空气极差");
                                mAirPurgeView.setSpeedLevel(1800);
                            }
                        } else if (value>=360 && value<480) {
                            if (mCurIndex1 != 1) {
                                mCurIndex1 = 1;
                                mAirPurgeView.setBackgroundColor(bgColors[1],1000);
                                mAirPurgeView.setBottomTitle("空气较差");
                                mAirPurgeView.setSpeedLevel(2400);
                            }
                        } else if (value>=240 && value<360) {
                            if (mCurIndex1 != 2) {
                                mCurIndex1 = 2;
                                mAirPurgeView.setBackgroundColor(bgColors[2],1000);
                                mAirPurgeView.setBottomTitle("空气良好");
                                mAirPurgeView.setSpeedLevel(3000);
                            }
                        } else if (value>=120 && value<240) {
                            if (mCurIndex1 != 3) {
                                mCurIndex1 = 3;
                                mAirPurgeView.setBackgroundColor(bgColors[3],1000);
                                mAirPurgeView.setBottomTitle("空气较好");
                                mAirPurgeView.setSpeedLevel(3600);
                            }

                        } else if (value>=0 && value<120) {
                            if (mCurIndex1 != 4) {
                                mCurIndex1 = 4;
                                mAirPurgeView.setBackgroundColor(bgColors[4],1000);
                                mAirPurgeView.setBottomTitle("空气优");
                                mAirPurgeView.setSpeedLevel(4200);
                            }
                            if (value == 32) {
                                mAirPurgeView.onCloseAirPurge();
                                mAirPurgeView.onClearAllGranule();
                            }
                        }
                    }
                });

                mAirPurgeView.onOpenGranule(false);
                mAirPurgeView.onOpenAirPurge();
                mAirPurgeView.setPanListener(new onPanListener() {
                    @Override
                    public void onHasClose() {

                    }

                    @Override
                    public void onHasOpen() {
                        mAirPurgeView.onChangeGranuleMode(true);
                        animator.start();
                    }

                });
            }
        });


    }
    private void clearMode1Anim() {
        if (animator.isRunning()){
            animator.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAirPurgeView.clearAllAnimator();
    }

}
