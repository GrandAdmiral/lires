package com.lires.jimmy;


import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.View;

public class Gamenew extends FragmentActivity {
    SlidingPaneLayout pane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameview);
        pane = (SlidingPaneLayout) findViewById(R.id.sp);
        pane.setPanelSlideListener(new PaneListener());
        System.out.println(pane.isSlideable());
        System.out.println(pane.isOpen());

    }

    private class PaneListener implements SlidingPaneLayout.PanelSlideListener {
        @Override
        public void onPanelClosed(View view) {
            System.out.println("Panel closed");

        }

        @Override
        public void onPanelOpened(View view) {
            System.out.println("Panel opened");
        }

        @Override
        public void onPanelSlide(View view, float arg1) {
            System.out.println("Panel sliding");
        }
    }
}

