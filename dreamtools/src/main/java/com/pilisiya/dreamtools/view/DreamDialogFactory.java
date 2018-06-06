package com.pilisiya.dreamtools.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pilisiya.dreamtools.R;

import java.util.HashMap;

/**
 * Created by xujiang on 2018/6/6.
 */

public class DreamDialogFactory {
    private static HashMap<String, Dialog> dialogs = new HashMap<>();

    private static TextView tvTitle;
    private static TextView tvCount;
    private static LinearLayout llBtn;
    private static TextView tvMsg;
    private static TextView tvOk;
    private static TextView tvCancel;
    private static CountDownTimer loadingTimer = null;

    public static boolean dismissAlert(Activity activity) {
        if (null != loadingTimer) {
            loadingTimer.cancel();
            loadingTimer = null;
        }
        Dialog dialog = dialogs.get(activity.toString());
        if ((dialog != null) && (dialog.isShowing())) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
            dialogs.remove(activity.toString());
            return true;
        } else {
            return false;
        }
    }

    private static Dialog createDialog(final Activity activity) {

        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dialog_basic);
        dialog.setContentView(R.layout.dialog_show_tip);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);

        tvTitle = dialog.findViewById(R.id.dialog_tip_title);
        tvCount = dialog.findViewById(R.id.dialog_tip_time);
        llBtn = dialog.findViewById(R.id.dialog_btn_ll);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
        tvOk = dialog.findViewById(R.id.dialog_tip_confirm_btn);
        tvCancel = dialog.findViewById(R.id.dialog_tip_cancel_btn);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                    return true;
                } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    return false;
                } else {
                    return false;
                }
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * @param context
     * @param title
     * @param msg
     * @param okStr
     * @param okClick
     */
    public static void showMessage(final Activity context, String title, String msg, String okStr,
                                   final View.OnClickListener okClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.VISIBLE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // dismissAlert(context);
                if (null != okClick) {
                    okClick.onClick(v);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // dismissAlert(context);
            }
        });
        dialog.show();
    }

    public static void showConfirmMessage(final Activity context, String title, String msg) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText("确认");
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
            }
        });
        dialog.show();
    }

    public static void showConfirmMessage(final Activity context, String title, String msg, String okStr) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
            }
        });
        dialog.show();
    }

    public static void showTipsMessage(final Activity context, String title, String msg, String okStr
    ) {
        loadingTimer = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                dismissAlert(context);
            }
        };
        loadingTimer.start();
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
            }
        });
        dialog.show();
    }

    /**
     * @param context
     * @param title
     * @param msg
     * @param okStr
     * @param okClick
     */
    public static void showConfirmMessage(final Activity context, String title, String msg, String okStr,
                                          final View.OnClickListener okClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
                if (null != okClick) {
                    okClick.onClick(v);
                }
            }
        });
        dialog.show();
    }

    public static void showMainComfirm(final Activity context, String title, String msg, String okStr,
                                       final View.OnClickListener okClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
                if (null != okClick) {
                    okClick.onClick(v);
                }
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        dialog.show();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * 弹框左右2按钮
     *
     * @param context
     * @param title
     * @param msg
     * @param okStr
     * @param cancleStr
     * @param okClick
     * @param cancleClick
     */
    public static void showConfirmMessage(final Activity context, String title, String msg, String okStr,
                                          String cancleStr, final View.OnClickListener okClick, final View.OnClickListener cancleClick
    ) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
                if (null != okClick) {
                    okClick.onClick(v);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismissAlert(context);
                if (null != cancleClick) {
                    cancleClick.onClick(v);
                }
            }
        });
        dialog.show();
    }

    /**
     * @param activity
     */
    public static void clearBeforeDialog(Activity activity) {
        Dialog dialog = (Dialog) dialogs.get(activity.toString());
        if (null != dialog && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
            dialogs.remove(activity.toString());
        }
    }
}
