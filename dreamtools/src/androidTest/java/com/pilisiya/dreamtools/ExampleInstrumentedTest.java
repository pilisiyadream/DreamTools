package com.pilisiya.dreamtools;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.pilisiya.dreamtools.test", appContext.getPackageName());
    }

    @Test
    public void test() {
        Looper.prepare();
        CountDownTimer time = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("xj", "millisUntilFinished:" + millisUntilFinished);
            }

            @Override
            public void onFinish() {
                Log.e("xj", "---onFinish---");
            }
        }.start();
    }
}
