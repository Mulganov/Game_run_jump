package com.game.game.game0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view = findViewById(R.id.view);

        view.post(() -> Game.getInstance().init(view, this));

        Game.getInstance().activity = this;

        findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
        findViewById(R.id.textView).setVisibility(View.INVISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            TextView textView = findViewById(R.id.textView2);
            int k = -1;
            @Override
            public void run() {
                textView.setAlpha(textView.getAlpha() + k * 0.01f);

                if (textView.getAlpha() > 0.7){
                    k = -1;
                }

                if (textView.getAlpha() < 0.1){
                    k = 1;
                }

                handler.postDelayed(this::run, 30);
            }
        }, 10);
    }
}
