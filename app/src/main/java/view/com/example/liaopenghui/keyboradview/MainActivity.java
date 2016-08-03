package view.com.example.liaopenghui.keyboradview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import view.com.example.liaopenghui.keyboradview.view.XZKeyboradView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText edit;
    private XZKeyboradView keyboard_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normol);
        edit = (EditText) this.findViewById(R.id.edit);
        keyboard_view = (XZKeyboradView) this.findViewById(R.id.keyboard_view);
        hideSoftInputMethod(edit);
        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                keyboard_view.setEditText(edit);
                keyboard_view.showKeyboard();

                if(event.getAction()==MotionEvent.ACTION_UP){
                    //隐藏键盘
                    Toast.makeText(MainActivity.this, "隐藏", Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });


        keyboard_view.setOnClickSureListener(new XZKeyboradView.OnSureListener() {
            @Override
            public void makeSure() {
                Log.e(TAG,"用户点击确认键了");
            }
        });
    }



    // 隐藏系统键盘
    public void hideSoftInputMethod(EditText ed){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        int currentVersion = android.os.Build.VERSION.SDK_INT;
        String methodName = null;
        if(currentVersion >= 16){
            // 4.2
            methodName = "setShowSoftInputOnFocus";
        }
        else if(currentVersion >= 14){
            // 4.0
            methodName = "setSoftInputShownOnFocus";
        }

        if(methodName == null){
            ed.setInputType(InputType.TYPE_NULL);
        }
        else{
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus;
            try {
                setShowSoftInputOnFocus = cls.getMethod(methodName, boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (NoSuchMethodException e) {
                ed.setInputType(InputType.TYPE_NULL);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
