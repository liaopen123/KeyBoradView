package view.com.example.liaopenghui.keyboradview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import view.com.example.liaopenghui.keyboradview.view.XZKeyboradView;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private XZKeyboradView keyboard_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normol);
        edit = (EditText) this.findViewById(R.id.edit);
        keyboard_view = (XZKeyboradView) this.findViewById(R.id.keyboard_view);
        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboard_view.setEditText(edit);
                keyboard_view.showKeyboard();
                return false;
            }
        });



    }

}
