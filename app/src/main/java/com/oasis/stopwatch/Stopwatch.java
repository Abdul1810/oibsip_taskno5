package com.oasis.stopwatch;
import android.os.Handler;

public class Stopwatch {
    private long startTime;
    private long elapsedTime;
    private boolean isRunning;
    private StopwatchListener listener;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRunning) {
                elapsedTime = System.currentTimeMillis() - startTime;
                listener.onTick(elapsedTime);
                handler.postDelayed(this, 1000);
            }
        }
    };

    public interface StopwatchListener {
        void onTick(long elapsedTime);
    }

    public void setListener(StopwatchListener listener) {
        this.listener = listener;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            isRunning = true;
            handler.postDelayed(runnable, 1000);
        }
    }


    public void stop() {
        if (isRunning) {
            isRunning = false;
            handler.removeCallbacks(runnable);
        }
    }

    public void reset() {
        isRunning = false;
        elapsedTime = 0;
        handler.removeCallbacks(runnable);
    }

    public static String formatTime(long elapsedTime) {
        long seconds = elapsedTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60);
    }
}