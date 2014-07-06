package kr.pe.cnagune.proguardoptimizetest;

import android.util.Log;

/**
 * Created by cnagune on 7/6/14.
 */
public class TestRunnableImpl implements Runnable {

    public static final String TAG = "TestRunnable";

    public TestRunnableImpl(MainActivity mainActivity) {
        mainActivity.registerListener(this);
    }

    @Override
    public void run() {
        Log.d(TAG, "run) hashcode: " + ((Object)this).hashCode());
    }

    @Override
    protected void finalize() throws Throwable {
        Log.d(TAG, "finalize) hashcode: " + ((Object)this).hashCode());
        super.finalize();
    }
}
