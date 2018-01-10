package com.netherbench.physics;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int MYTHREADS = 5;
        final ExecutorService executor = Executors.newFixedThreadPool(MYTHREADS);

        final GenSet<EditText> data_entry = new GenSet<>(EditText.class, 46);
        final GenSet<DataPoint> data_point1 = new GenSet<>(DataPoint.class, 15);
        final GenSet<DataPoint> data_point2 = new GenSet<>(DataPoint.class, 15);
        final GenSet<DataPoint> data_point3 = new GenSet<>(DataPoint.class, 15);
        final GenSet<DataPoint> data_point4 = new GenSet<>(DataPoint.class, 15);
        final GenSet<DataPoint> data_point5 = new GenSet<>(DataPoint.class, 15);

        for (int i = 1; i<46; i++)
        {
            final int Id = getResources().getIdentifier(("textView" + String.valueOf(3 + i)), "id", getPackageName());
            data_entry.a[i] = findViewById(Id);
        }

        final LinearLayout linearLayout = findViewById(R.id.linear_layout);
        final GraphView graph1 = new GraphView(MainActivity.this);
        graph1.getViewport().setScalable(true);
        graph1.getViewport().setScalableY(true);
        final GraphView graph2 = new GraphView(MainActivity.this);
        graph2.getViewport().setScalable(true);
        graph2.getViewport().setScalableY(true);
        final GraphView graph3 = new GraphView(MainActivity.this);
        graph3.getViewport().setScalable(true);
        graph3.getViewport().setScalableY(true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500);
        linearLayout.addView(graph1, params);
        linearLayout.addView(graph2, params);
        linearLayout.addView(graph3, params);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                graph1.removeAllSeries();
                graph2.removeAllSeries();
                graph3.removeAllSeries();
                float k = 0.684f, c = 13.802f;
                for (int i = 1; i < 16; i++) {
                    float x = 0, y = 0;
                    for (int j = 1; j < 4; j++) {
                        if (!data_entry.a[((i - 1) * 3) + j].getText().toString().isEmpty()) {
                            if (j == 2)
                                x = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                            else if (j == 3)
                                y = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                        } else {
                            if (j == 2)
                                x = 0;
                            else if (j == 3)
                                y = 0;
                        }
                    }
                    Log.d("debug", x + "   "+ y);
                    data_point1.a[i - 1] = new DataPoint(x, y);
                }
                LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(data_point1.a);
                Runnable Graph1 = new MyRunnable(graph1, series1);
                executor.execute(Graph1);
                /*try {
                    k = -(Float.valueOf(data_entry.a[45].getText().toString()) - Float.valueOf(data_entry.a[3].getText().toString())) / (Float.valueOf(data_entry.a[44].getText().toString()) - Float.valueOf(data_entry.a[2].getText().toString()));
                    c = (k * Float.valueOf(data_entry.a[2].getText().toString())) + Float.valueOf(data_entry.a[3].getText().toString());
                }catch (Exception e)
                {

                }*/
                    Log.d("debug", k + "  " + c);
                for (int i = 1; i < 16; i++) {
                    float x = 0, y = 0;
                    for (int j = 1; j < 4; j++) {
                        if (!data_entry.a[((i - 1) * 3) + j].getText().toString().isEmpty()) {
                            if (j == 3) {
                                x = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                                y = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString())*c;
                            }
                        } else {
                            if (j == 2)
                                x = 0;
                            else if (j == 3)
                                y = 0;
                        }
                    }
                    data_point2.a[i - 1] = new DataPoint(x, y);
                }
                for (int i = 1; i < 16; i++) {
                    float x = 0, y = 0;
                    for (int j = 1; j < 4; j++) {
                        if (!data_entry.a[((i - 1) * 3) + j].getText().toString().isEmpty()) {
                            if (j == 3) {
                                x = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                            }
                            else if (j ==1)
                            {
                                y = x*x*Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString())/1000;
                            }
                        } else {
                            if (j == 2)
                                x = 0;
                            else if (j == 3)
                                y = 0;
                        }
                    }
                    data_point3.a[i - 1] = new DataPoint(x, y);
                }
                for (int i = 1; i < 16; i++) {
                    float x = 0, y = 0;
                    for (int j = 1; j < 4; j++) {
                        if (!data_entry.a[((i - 1) * 3) + j].getText().toString().isEmpty()) {
                            if (j == 3) {
                                x = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                                y = x * x * k;
                            }
                        } else {
                            if (j == 2)
                                x = 0;
                            else if (j == 3)
                                y = 0;
                        }
                    }
                    data_point4.a[i - 1] = new DataPoint(x, y);
                }
                LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(data_point2.a);
                LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(data_point3.a);
                LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(data_point4.a);
                Runnable Graph2 = new MyRunnable(graph2, series2);
                executor.execute(Graph2);
                Runnable Graph3 = new MyRunnable(graph2, series3);
                executor.execute(Graph3);
                Runnable Graph4 = new MyRunnable(graph2, series4);
                executor.execute(Graph4);
                for (int i = 1; i < 16; i++) {
                    float x = 0, y = 0;
                    for (int j = 1; j < 4; j++) {
                        if (!data_entry.a[((i - 1) * 3) + j].getText().toString().isEmpty()) {
                            if (j == 3) {
                                y = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString());
                            }
                            else if (j == 2)
                            {
                                x = Float.valueOf(data_entry.a[((i - 1) * 3) + j].getText().toString())/c;
                            }
                        } else {
                            if (j == 2)
                                x = 0;
                            else if (j == 3)
                                y = 0;
                        }
                    }
                    data_point5.a[i - 1] = new DataPoint(x, y);
                }
                LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>(data_point5.a);
                Runnable Graph5 = new MyRunnable(graph3, series5);
                executor.execute(Graph5);
            }
        });
    }

    public static class MyRunnable implements Runnable {

        private final LineGraphSeries<DataPoint> series;
        private final GraphView graph;

        MyRunnable(GraphView graph, LineGraphSeries<DataPoint> series) {
            this.series = series;
            this.graph = graph;
        }

        @Override
        public void run() {
            graph.addSeries(series);
            graph.getViewport().calcCompleteRange();
            graph.getViewport().computeScroll();
            graph.animate();
        }
    }

    static class GenSet<E> {

        E[] a;

        GenSet(Class<E> c, int s) {
            // Use Array native method to create array
            // of a type only known at run time
            @SuppressWarnings("unchecked") final E[] a = (E[]) Array.newInstance(c, s);
            this.a = a;
        }

        E get(int i) {
            return a[i];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
