package com.oasis.stopwatch;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView timeView;
    private Button startButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeView = findViewById(R.id.timer);
        startButton = findViewById(R.id.startBtn);
        resetButton = findViewById(R.id.resetBtn);

        final Stopwatch stopwatch = new Stopwatch();
        stopwatch.setListener(new Stopwatch.StopwatchListener() {
            @Override
            public void onTick(long elapsedTime) {
                timeView.setText(Stopwatch.formatTime(elapsedTime));
            }
        });

        startButton.setOnClickListener(getStop(stopwatch));
        resetButton.setOnClickListener(getReset(stopwatch));
        resetButton.setEnabled(false);
    }

    @NonNull
    private View.OnClickListener getReset(Stopwatch stopwatch) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.reset();
                timeView.setText(Stopwatch.formatTime(0));
                startButton.setText("Start");
                startButton.setOnClickListener(getStop(stopwatch));
                resetButton.setEnabled(false);
            }
        };
    }

    @NonNull
    private View.OnClickListener getStop(Stopwatch stopwatch) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.start();
                // change start button to stop button
                startButton.setText("Stop");
                startButton.setOnClickListener(getStart(stopwatch));
                resetButton.setEnabled(true);
            }
        };
    }

    @NonNull
    private View.OnClickListener getStart(Stopwatch stopwatch) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.stop();
                // change stop button to start button
                startButton.setText("Start");
                startButton.setOnClickListener(getStop(stopwatch));
                resetButton.setEnabled(true);
            }
        };
    }
}
