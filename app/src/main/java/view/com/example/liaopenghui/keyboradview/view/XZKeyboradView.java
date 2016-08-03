package view.com.example.liaopenghui.keyboradview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import view.com.example.liaopenghui.keyboradview.R;

/**
 * Created by liaopenghui on 16/8/3.
 */
public class XZKeyboradView extends KeyboardView {

    private  Context mContext;


    private  Keyboard numKeyboard;
    private EditText mEditText;

    public XZKeyboradView(Context context, AttributeSet attrs) {
        super(context, attrs);
       init(context);
    }

    public XZKeyboradView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        this.mContext = context;
        numKeyboard = new Keyboard(mContext, R.xml.symbols);
        setKeyboard(numKeyboard);
        setEnabled(true);
        setPreviewEnabled(false);//关闭preview
        setOnKeyboardActionListener(listener);
    }

    /**重画一些键盘*/
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取所有按键
        List<Keyboard.Key> keys = numKeyboard.getKeys();
        for(Keyboard.Key key : keys){
            if(key.codes[0]==-3){
                //修改"确认键的样式"
                drawKeyBackground(R.drawable.btn_keyboard_key2, canvas, key);
                drawText(canvas, key);
            }
        }
    }

    private void drawText(Canvas canvas, Keyboard.Key key) {
        Rect bounds = new Rect();
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        /**sp转px*/
            paint.setTextSize(100);
        paint.setAntiAlias(true);
        // paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setColor(Color.WHITE);
        if (key.label != null) {
            paint.getTextBounds(key.label.toString(), 0, key.label.toString()
                    .length(), bounds);
            canvas.drawText(key.label.toString(), key.x + (key.width / 2),
                    (key.y + key.height / 2) + bounds.height() / 2, paint);
        }
    }


    private void drawKeyBackground(int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable npd = (Drawable) mContext.getResources().getDrawable(
                drawableId);
        int[] drawableState = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            npd.setState(drawableState);
        }
        npd.setBounds(key.x, key.y, key.x + key.width, key.y
                + key.height);
        npd.draw(canvas);
    }
    /**得到界面的EidtText对象*/
    public void setEditText(EditText editText){
        mEditText = editText;
    }




    private OnKeyboardActionListener listener = new OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            if (primaryCode == Keyboard.KEYCODE_CANCEL) {// 完成
                hideKeyboard();
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {// 回退
                if (editable != null && editable.length() > 0) {
                    if (start > 0) {
                        editable.delete(start - 1, start);
                    }
                }
            } else if (primaryCode == 57419) { // go left
                if (start > 0) {
                    mEditText.setSelection(start - 1);
                }
            } else if (primaryCode == 57421) { // go right
                if (start < mEditText.length()) {
                    mEditText.setSelection(start + 1);
                }
            }else if(primaryCode == 10000){
                //+100的操作
                double v = Double.parseDouble(editable.toString()) + 100;
                mEditText.setText(String.valueOf(v));

            }else if(primaryCode == 10001){
                //-100的操作
                try {
                    double v = Double.parseDouble(editable.toString()) - 100;
                    mEditText.setText(String.valueOf(v));
                    if (v < 0) throw new RuntimeException();
                }catch (Exception e){
                    mEditText.setText("0");

                }


            } else {
                editable.insert(start, Character.toString((char) primaryCode));
            }
        }
    };



    public void showKeyboard() {
        int visibility = this.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard() {
        int visibility = getVisibility();
        if (visibility == View.VISIBLE) {
            setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("DefaultLocale")
    private boolean isword(String str) {
        String wordstr = "abcdefghijklmnopqrstuvwxyz";
        if (wordstr.indexOf(str.toLowerCase()) > -1) {
            return true;
        }
        return false;
    }


}
