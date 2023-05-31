package edu.cs.birzeit.stopwatch_dana;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean running = false;

    private Button btnStart;
    private Button btnStop;
    private Button btnReset;
     TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore the state
        checkInstance(savedInstanceState);

        // Initialize the view
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);
        txtTime = findViewById(R.id.txtTimer);

        // Run the timer
        runTimer();
        btnStart.setOnClickListener(v -> {
            onClickStart(v);
        });

        btnStop.setOnClickListener(v -> onClickStop(v));

        btnReset.setOnClickListener(v -> onClickReset(v));
    }
    private void checkInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("SECONDS");
            running = savedInstanceState.getBoolean("RUNNING");
        }
    }

    @Override
    public void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SECONDS", seconds);
        outState.putBoolean("RUNNING", running);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        // Post the code in the runnable object to run after 1 second passes
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = hours + " : " + minutes + " : " + secs;
                txtTime.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);

            }
        });
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
}