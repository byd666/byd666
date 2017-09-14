package byd.com.byd.popupview.weiget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import byd.com.byd.popupview.R;

/**
 * Created by Geh on 2017/3/25.
 */

public class PopupView {
    private PopupWindow popupWindow;
    private Activity activity;
    private View contentView;

    private boolean flag = false;

    public PopupView(Activity activity, View contentView) {
        this.activity = activity;
        this.contentView = contentView;
        init();
    }

    public boolean getFlag() {
        return flag;
    }

    private void init() {
        LinearLayout r = new LinearLayout(activity);
        r.setOrientation(LinearLayout.VERTICAL);
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        LinearLayout.LayoutParams rl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        rl.gravity = Gravity.RIGHT | Gravity.TOP;
        r.addView(contentView, rl);

        popupWindow = new PopupWindow(r, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
    }

    public void dismiss() {
        flag = false;
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public void show(View v) {
        flag = true;
        hideSoftInput();
        if (popupWindow.isShowing()) {
            return;
        }
        popupWindow.showAsDropDown(v, 0, 0);
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void auto(View v) {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(v, 0, 0);
        }
    }
}
