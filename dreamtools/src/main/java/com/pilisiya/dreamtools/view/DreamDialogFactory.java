package com.pilisiya.dreamtools.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pilisiya.dreamtools.R;
import com.pilisiya.dreamtools.listener.DreamChoosePayTypeListener;
import com.pilisiya.dreamtools.listener.DreamIBackListener;
import com.pilisiya.dreamtools.util.CustomWindowFlag;
import com.pilisiya.dreamtools.util.DreamConstant;
import com.pilisiya.dreamtools.view.loading.ShapeLoadingDrawable;
import com.pilisiya.dreamtools.view.loading.ThreeBallsLoadingDrawable;

import java.util.HashMap;

/**
 * 【通用消息提示框】
 *
 * @author Created by xujiang on 2018/6/6.
 */

public class DreamDialogFactory {
    private static HashMap<String, Dialog> dialogs = new HashMap<>();
    private static HashMap<String, ThreeBallsLoadingDrawable> loadingDrawableHashMap = new HashMap<>();
    private static HashMap<String, ShapeLoadingDrawable> shapeLoadingDrawableHashMap = new HashMap<>();

    private static TextView tvTitle;
    private static TextView tvCount;
    private static LinearLayout llBtn;
    private static TextView tvMsg;
    private static TextView tvOk;
    private static TextView tvCancel;
    private static ProgressBar progressBar;
    private static ImageView line, img_cancel;
    private static EditText ed_pass;
    private static TextView tv_bank, tv_scan, tv_amt,tv_face;
    private static ImageView iv_shape_loading, img_ok, img_error;
    private static CountDownTimer loadingTimer = null;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean dismissAlert(Activity activity) {
        if (activity == null || activity.isDestroyed() || activity.isFinishing()) {
            return false;
        }
        if (null != loadingTimer) {
            loadingTimer.cancel();
            loadingTimer = null;
        }
        ThreeBallsLoadingDrawable drawable = loadingDrawableHashMap.get(activity.toString());
        if (drawable != null) {
            drawable.stop();
        }

        ShapeLoadingDrawable drawable1 = shapeLoadingDrawableHashMap.get(activity.toString());
        if (drawable1 != null) {
            drawable1.stop();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_show_tip);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog2(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_input_value);
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog3(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_web_loading);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        ThreeBallsLoadingDrawable drawable = new ThreeBallsLoadingDrawable();
        loadingDrawableHashMap.put(activity.toString(), drawable);
        tvTitle = dialog.findViewById(R.id.dialog_tip_title);
        iv_shape_loading = dialog.findViewById(R.id.iv_shape_loading);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog4(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_web_loading2);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        ShapeLoadingDrawable drawable = new ShapeLoadingDrawable();
        shapeLoadingDrawableHashMap.put(activity.toString(), drawable);
        tvTitle = dialog.findViewById(R.id.dialog_tip_title);
        iv_shape_loading = dialog.findViewById(R.id.iv_shape_loading);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog5(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_toast);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        tvMsg = dialog.findViewById(R.id.dialog_tip_content);
        img_ok = dialog.findViewById(R.id.img_ok);
        img_error = dialog.findViewById(R.id.img_error);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog6(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_chose);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        img_cancel = dialog.findViewById(R.id.img_cancel);
        tv_bank = dialog.findViewById(R.id.tv_bank);
        tv_scan = dialog.findViewById(R.id.tv_scan);
        tv_amt = dialog.findViewById(R.id.tv_amt);
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static Dialog createDialog7(final Activity activity) {
        dismissAlert(activity);
        Dialog dialog = new Dialog(activity, R.style.dream_dialog_basic);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dream_dialog_chose_face);
        dialog.setCancelable(false);
        dialogs.put(activity.toString(), dialog);
        img_cancel = dialog.findViewById(R.id.img_cancel);
        tv_face = dialog.findViewById(R.id.tv_face);
        tv_scan = dialog.findViewById(R.id.tv_scan);
        tv_amt = dialog.findViewById(R.id.tv_amt);
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showConfirmMessage(final Activity context, String title, String msg, String okStr, final View.OnClickListener okClick) {
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
    }

    /**
     * 【倒计时提示框】
     *
     * @param context Activity
     * @param msg     消息
     * @param timeout 倒计时时间
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
    }


    /**
     * 【网络数据加载】
     *
     * @param context Activity
     * @param msg     消息
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showWebLoading(final Activity context, String title, String msg) {
        if (context == null || context.isDestroyed() || context.isFinishing()) {
            return;
        }
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

    }


    /**
     * 【网络数据加载,添加倒计时时间】
     *
     * @param context Activity
     * @param msg     消息
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showWebLoading(final Activity context, String title, String msg, String count) {
        Dialog dialog = dialogs.get(context.toString());
        if (dialog != null && dialog.isShowing()) {
            llBtn.setVisibility(View.GONE);
            tvCount.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            line.setVisibility(View.GONE);
            tvTitle.setText(title);
            tvCount.setText(count);
            tvMsg.setText(msg);
        } else {
            clearBeforeDialog(context);
            dialog = createDialog(context);
            llBtn.setVisibility(View.GONE);
            tvCount.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            line.setVisibility(View.GONE);
            tvTitle.setText(title);
            tvCount.setText(count);
            tvMsg.setText(msg);
            dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
            dialog.show();
        }
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
    }

    /**
     * 【网络数据加载】
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     消息
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showWebLoadingThreeBall(final Activity context, String title, String msg) {
        if (context == null || context.isDestroyed() || context.isFinishing()) {
            return;
        }
        clearBeforeDialog(context);
        Dialog dialog = createDialog3(context);
        tvTitle.setVisibility(View.VISIBLE);
        iv_shape_loading.setImageDrawable(loadingDrawableHashMap.get(context.toString()));
        loadingDrawableHashMap.get(context.toString()).start();
        tvTitle.setText(title);
        tvMsg.setText(msg);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
    }


    /**
     * 【网络数据加载】
     *
     * @param context 上下文
     * @param title   标题
     * @param msg     消息
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showWebLoadingShape(final Activity context, String title, String msg) {
        if (context == null || context.isDestroyed() || context.isFinishing()) {
            return;
        }
        clearBeforeDialog(context);
        Dialog dialog = createDialog4(context);
        tvTitle.setVisibility(View.VISIBLE);
        iv_shape_loading.setImageDrawable(shapeLoadingDrawableHashMap.get(context.toString()));
        shapeLoadingDrawableHashMap.get(context.toString()).start();
        tvTitle.setText(title);
        tvMsg.setText(msg);
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showMainComfirmMessage(final Activity context, String title, String msg, String okStr, final View.OnClickListener okClick) {
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showCustomMessage(final Activity context, String title, String msg, String okStr, String cancleStr, final View.OnClickListener okClick, final View.OnClickListener cancleClick) {
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showCustomMessage(final Activity context, int timeout, String title, String msg, String okStr, String cancleStr, final View.OnClickListener okClick, final View.OnClickListener cancleClick) {
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showInputPass(final Activity context, String title, String pass, String okStr, String cancleStr, final View.OnClickListener sureClick) {
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
    }


    /**
     * 【倒计时提示框】
     *
     * @param context Activity
     * @param msg     消息
     * @param timeout 倒计时时间
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void showToast(final Activity context, boolean success, String msg, int timeout, DreamIBackListener listener) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog5(context);
        loadingTimer = new CountDownTimer(timeout, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                dismissAlert(context);
                listener.onBack();
            }
        };
        tvMsg.setText(msg);
        if (success) {
            img_ok.setVisibility(View.VISIBLE);
            img_error.setVisibility(View.GONE);
        } else {
            img_ok.setVisibility(View.GONE);
            img_error.setVisibility(View.VISIBLE);
        }
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        loadingTimer.start();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void choosePayType(final Activity context, String amt, DreamChoosePayTypeListener listener) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog6(context);
        if (!TextUtils.isEmpty(amt))
            tv_amt.setText(amt);
        if (listener != null) {
            img_cancel.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_CANCEL);
            });
            tv_bank.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_BANK);
            });
            tv_scan.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_SCAN);
            });
        }
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void choosePayType2(final Activity context, String amt, DreamChoosePayTypeListener listener) {
        clearBeforeDialog(context);
        Dialog dialog = createDialog7(context);
        if (!TextUtils.isEmpty(amt))
            tv_amt.setText(amt);
        if (listener != null) {
            img_cancel.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_CANCEL);
            });
            tv_face.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_FACE);
            });
            tv_scan.setOnClickListener(view -> {
                dismissAlert(context);
                listener.onChoice(DreamConstant.PAY_TYPE_SCAN);
            });
        }
        dialog.setOnKeyListener((dialog1, keyCode, event) -> false);
        dialog.show();
        //屏蔽HOME键
        CustomWindowFlag.disableHomeKey(dialog.getWindow());
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
