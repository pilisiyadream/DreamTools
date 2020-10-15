package com.pilisiya.dreamtools.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pilisiya.dreamtools.R;
import com.pilisiya.dreamtools.listener.IBackListener;
import com.pilisiya.dreamtools.util.CustomWindowFlag;

import java.util.HashMap;

/**
 * 【通用消息提示框】
 *
 * @author Created by xujiang on 2018/6/6.
 */

public class DreamDialogFactory {
    private static HashMap<String, Dialog> dialogs = new HashMap<>();

    private static TextView tvTitle;
    private static TextView tvCount;
    private static LinearLayout llBtn;
    private static TextView tvMsg;
    private static TextView tvOk;
    private static TextView tvCancel;
    private static ProgressBar progressBar;
    private static ImageView line;
    private static EditText ed_pass;
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
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_show_tip);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        tvTitle = dialog.findViewById(R.id.dialog_tip_title);
        tvCount = dialog.findViewById(R.id.dialog_tip_time);
        llBtn = dialog.findViewById(R.id.dialog_btn_ll);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
        tvOk = dialog.findViewById(R.id.dialog_tip_confirm_btn);
        progressBar = dialog.findViewById(R.id.bar_progress);
        line = dialog.findViewById(R.id.line);
        tvCancel = dialog.findViewById(R.id.dialog_tip_cancel_btn);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog1.dismiss();
                return false;
            } else {
                return false;
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private static Dialog createDialog2(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_input_value);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        tvTitle = dialog.findViewById(R.id.dialog_tip_title);
        ed_pass = dialog.findViewById(R.id.dialog_input_value);
        llBtn = dialog.findViewById(R.id.dialog_btn_ll);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
        tvOk = dialog.findViewById(R.id.dialog_tip_confirm_btn);
        tvCancel = dialog.findViewById(R.id.dialog_tip_cancel_btn);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                dialog1.dismiss();
                return false;
            } else {
                return false;
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * 【消息确认提示框，只有确认按钮，按返回键取消提示框】
     *
     * @param context Activity
     * @param title   标题
     * @param msg     提示信息
     * @param okStr   确认按钮
     * @param okClick 确认按钮事件
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
        tvOk.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != okClick) {
                okClick.onClick(v);
            }
        });
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }

    /**
     * 【倒计时提示框】
     *
     * @param context Activity
     * @param msg     消息
     * @param timeout 倒计时时间
     */
    public static void showTipsMessage(final Activity context, String title, String msg, int timeout, boolean isShowComfirm) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        loadingTimer = new CountDownTimer(timeout, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCount.setText((millisUntilFinished / 1000) + "");
            }

            @Override
            public void onFinish() {
                dismissAlert(context);
            }
        };
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.VISIBLE);
        tvCount.setText((timeout / 1000) + "");
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvCancel.setVisibility(View.GONE);
        if (isShowComfirm) {
            tvOk.setVisibility(View.VISIBLE);
            tvOk.setOnClickListener(v -> dismissAlert(context));
        } else {
            llBtn.setVisibility(View.GONE);
        }
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        loadingTimer.start();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * 【网络数据加载】
     *
     * @param context Activity
     * @param msg     消息
     */
    public static void showWebLoading(final Activity context, String title, String msg) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.GONE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        line.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * 【消息确认提示框，只有一个确认按钮，且屏蔽HOME键和返回键】
     *
     * @param context Activity
     * @param title   标题
     * @param msg     提示消息
     * @param okStr   确认按钮
     * @param okClick 确认按钮事件
     */
    public static void showMainComfirmMessage(final Activity context, String title, String msg, String okStr,
                                              final View.OnClickListener okClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setVisibility(View.GONE);
        tvOk.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != okClick) {
                okClick.onClick(v);
            }
        });
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键盘
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * 【消息提示框，确认取消按钮】
     *
     * @param context     Activity
     * @param title       标题
     * @param msg         提示消息
     * @param okStr       确认
     * @param cancleStr   取消
     * @param okClick     确认按钮事件
     * @param cancleClick 取消按钮事件
     */
    public static void showCustomMessage(final Activity context, String title, String msg, String okStr,
                                         String cancleStr, final View.OnClickListener okClick, final View.OnClickListener cancleClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        llBtn.setVisibility(View.VISIBLE);
        tvCount.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setText(cancleStr);
        tvOk.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != okClick) {
                okClick.onClick(v);
            }
        });
        tvCancel.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != cancleClick) {
                cancleClick.onClick(v);
            }
        });
        dialog.show();
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * 【消息提示框，确认取消按钮】
     *
     * @param context     Activity
     * @param title       标题
     * @param msg         提示消息
     * @param okStr       确认
     * @param cancleStr   取消
     * @param okClick     确认按钮事件
     * @param cancleClick 取消按钮事件
     */
    public static void showCustomMessage(final Activity context, int timeout, String title, String msg, String okStr,
                                         String cancleStr, final View.OnClickListener okClick, final View.OnClickListener cancleClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog(context);
        tvCount.setVisibility(View.VISIBLE);
        tvCount.setText((timeout / 1000) + "");
        loadingTimer = new CountDownTimer(timeout, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvCount.setText((millisUntilFinished / 1000) + "");
            }

            @Override
            public void onFinish() {
                dismissAlert(context);
                if (null != okClick) {
                    okClick.onClick(tvOk);
                }
            }
        };
        llBtn.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        tvMsg.setText(msg);
        tvOk.setText(okStr);
        tvCancel.setText(cancleStr);
        tvOk.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != okClick) {
                okClick.onClick(v);
            }
        });
        tvCancel.setOnClickListener(v -> {
            dismissAlert(context);
            if (null != cancleClick) {
                cancleClick.onClick(v);
            }
        });
        dialog.show();
        loadingTimer.start();
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
    }

    /**
     * 【消息提示框，确认取消按钮】
     *
     * @param context   Activity
     * @param title     标题
     * @param pass      密码
     * @param okStr     确认
     * @param cancleStr 取消
     * @param sureClick 密码输入正确事件
     */
    public static void showInputPass(final Activity context, String title, String pass, String okStr,
                                     String cancleStr, final View.OnClickListener sureClick) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog2(context);
        llBtn.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
        tvOk.setText(okStr);
        tvCancel.setText(cancleStr);
        tvOk.setOnClickListener(v -> {
            if (null != sureClick) {
                if (TextUtils.isEmpty(ed_pass.getText().toString())) {
                    Toast.makeText(context, "请输入密码", Toast.LENGTH_LONG).show();
                } else {
                    if (ed_pass.getText().toString().equals(pass)) {
                        dismissAlert(context);
                        sureClick.onClick(v);
                    } else {
                        Toast.makeText(context, "密码错误", Toast.LENGTH_LONG).show();
                        ed_pass.setText("");
                    }
                }

            }
        });
        tvCancel.setOnClickListener(v -> {
            dismissAlert(context);
        });
        dialog.show();
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }


    /**
     * @param activity
     */
    public static void clearBeforeDialog(Activity activity) {
        Dialog dialog = dialogs.get(activity.toString());
        if (null != dialog && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
            dialogs.remove(activity.toString());
        }
    }
}
