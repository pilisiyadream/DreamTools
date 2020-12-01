package com.pilisiya.dreamtools.util;

import android.util.Log;

public class DreamLogUtil {
    private final static String DEBUG_TAG = "DreamLogUtil";
    private static boolean logEnable = true;
    public static boolean getDreamLogUtilsStatus() {
        return logEnable;
    }
    public static void setDreamLogUtil(boolean Enable) {
        Log.d(DEBUG_TAG, "log enable=" + Enable);
        logEnable = Enable;
    }

    public static void e(String msg) {
        if (logEnable) {
            Log.e(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            if (msg.length() > 4000) {
                for (int i = 0; i < msg.length(); i += 4000) {
                    if (i + 4000 < msg.length())
                        Log.e(DEBUG_TAG, "│" + "          " + msg.substring(i, i + 4000));
                }
            } else {
                Log.e(DEBUG_TAG, "│" + "          " + msg);
            }
            Log.e(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void e(String tips, String msg) {
        if (logEnable) {
            Log.e(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.e(DEBUG_TAG, "│" + "          " + tips);
            Log.e(DEBUG_TAG, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            if (msg.length() > 4000) {
                double totalLen = msg.length();
                double tempNum = totalLen / 4000;
                int num = (int) Math.ceil(tempNum);
                for (int i = 0; i < num; i++) {
                    int currentLen = msg.length() - i * 4000;
                    if (currentLen > 4000) {
                        currentLen = 4000;
                    }
                    String currentMsgStr = msg.substring(i * 4000, i * 4000 + currentLen);
                    Log.e(DEBUG_TAG, "│" + "          " + currentMsgStr);
                }
            } else {
                Log.e(DEBUG_TAG, "│" + "          " + msg);
            }
            Log.e(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void i(String msg) {
        if (logEnable) {
            Log.i(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.i(DEBUG_TAG, "│" + "          " + msg);
            Log.i(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void i(String tips, String msg) {
        if (logEnable) {
            Log.i(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.i(DEBUG_TAG, "│" + "          " + tips);
            Log.i(DEBUG_TAG, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            Log.i(DEBUG_TAG, "│" + "          " + msg);
            Log.i(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void v(String msg) {
        if (logEnable) {
            Log.v(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.v(DEBUG_TAG, "│" + "          " + msg);
            Log.v(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void v(String tips, String msg) {
        if (logEnable) {
            Log.v(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.v(DEBUG_TAG, "│" + "          " + tips);
            Log.v(DEBUG_TAG, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            Log.v(DEBUG_TAG, "│" + "          " + msg);
            Log.v(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void d(String msg) {
        if (logEnable) {
            Log.d(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.d(DEBUG_TAG, "│" + "          " + msg);
            Log.d(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void d(String tips, String msg) {
        if (logEnable) {
            Log.d(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.d(DEBUG_TAG, "│" + "          " + tips);
            Log.d(DEBUG_TAG, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            Log.d(DEBUG_TAG, "│" + "          " + msg);
            Log.d(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void w(String msg) {
        if (logEnable) {
            Log.w(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.w(DEBUG_TAG, "│" + "          " + msg);
            Log.w(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }

    public static void w(String tips, String msg) {
        if (logEnable) {
            Log.w(DEBUG_TAG, "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
            Log.w(DEBUG_TAG, "│" + "          " + tips);
            Log.w(DEBUG_TAG, "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
            Log.w(DEBUG_TAG, "│" + "          " + msg);
            Log.w(DEBUG_TAG, "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        }
    }
}
