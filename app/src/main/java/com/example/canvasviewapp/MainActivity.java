package com.example.canvasviewapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import biz.kasual.materialnumberpicker.MaterialNumberPicker;
import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabPathColor, fabStrokeWidth;
    private MyCanvasView myCanvasView;

    private int mSelectedColor = R.color.black;
    private int mSelectedWidth = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //MyCanvasView myCanvasView;
        //myCanvasView= new MyCanvasView(this);
        //myCanvasView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        myCanvasView = findViewById(R.id.my_canvas_view);
        fabPathColor = findViewById(R.id.fab_color_picker);
        fabStrokeWidth = findViewById(R.id.fab_stroke_width);

        fabPathColor.setOnClickListener((view) -> {
            ColorPicker colorPicker = new ColorPicker(this);
            colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                @Override
                public void setOnFastChooseColorListener(int position, int color) {
                    mSelectedColor = color;
                    myCanvasView.setPathColor(color);
                }

                @Override
                public void onCancel() {
                    colorPicker.dismissDialog();
                }
            })
                    .setColumns(5)
                    .disableDefaultButtons(true)
                    .show();
        });

        fabStrokeWidth.setOnClickListener((view) -> {
            MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(this)
                    .minValue(5)
                    .maxValue(25)
                    .defaultValue(mSelectedWidth)
                    .backgroundColor(Color.WHITE)
                    .separatorColor(Color.TRANSPARENT)
                    .textColor(Color.BLACK)
                    .textSize(20)
                    .enableFocusability(false)
                    .wrapSelectorWheel(true)
                    .build();

            new AlertDialog.Builder(this)
                    .setTitle("Pick Stroke Width")
                    .setView(numberPicker)
                    .setPositiveButton(getString(android.R.string.ok), (dialogInterface, which) -> {
                        mSelectedWidth = numberPicker.getValue();
                        myCanvasView.setPathStrokeWidth(numberPicker.getValue());
                    })
                    .show();
        });
    }
}