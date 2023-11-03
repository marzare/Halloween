package com.example.hallowensth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private ImageView halloweenImage;
    private TextView countdownText;
    private TextView countdownTimer;
    private Button startButton;
    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        halloweenImage = findViewById(R.id.halloweenImage);
        countdownText = findViewById(R.id.countdownText);
        countdownTimer = findViewById(R.id.countdownTimer);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCountdown();
            }
        });
    }

    private void startCountdown() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date halloweenDate = sdf.parse("2023-10-31"); // Ustaw datę Halloween

        long halloweenTime = halloweenDate.getTime();
        long currentTime = System.currentTimeMillis();
        long timeDiff = halloweenTime - currentTime;

        if (timeDiff > 0) {
            timer = new CountDownTimer(timeDiff, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long days = millisUntilFinished / (1000 * 60 * 60 * 24);
                    long hours = (millisUntilFinished / (1000 * 60 * 60)) % 24;
                    long minutes = (millisUntilFinished / (1000 * 60)) % 60;
                    long seconds = (millisUntilFinished / 1000) % 60;

                    String countdown = String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
                    countdownTimer.setText(countdown);
                }

                @Override
                public void onFinish() {
                    countdownTimer.setText("Czas do Halloween minął!");
                }
            };

            timer.start();
            startButton.setEnabled(false);
        }
    }
}