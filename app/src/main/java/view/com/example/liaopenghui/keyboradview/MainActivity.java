package view.com.example.liaopenghui.keyboradview;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import view.com.example.liaopenghui.keyboradview.view.KeyboardNormol;

public class MainActivity extends AppCompatActivity {
    private Context ctx;
    private Activity act;
    private EditText edit;
    private EditText edit1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normol);
        ctx = this;
        act = this;

        edit = (EditText) this.findViewById(R.id.edit);
        edit.setInputType(InputType.TYPE_NULL);// 不显示光标

        edit1 = (EditText) this.findViewById(R.id.edit1);

        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboardNormol(act, ctx, edit).showKeyboard();
                return false;
            }
        });

        edit1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inputback = edit1.getInputType();
                edit1.setInputType(InputType.TYPE_NULL);
                new KeyboardNormol(act, ctx, edit1).showKeyboard();
                edit1.setInputType(inputback);
                return false;
            }
        });

    }

}
