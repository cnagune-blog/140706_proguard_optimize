package kr.pe.cnagune.proguardoptimizetest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Map;
import java.util.WeakHashMap;

public class MainActivity extends Activity {

    private TestRunnableImpl testRunnableImpl;
    private TextView textView;
    private Map<Runnable, Object> runnableMap = new WeakHashMap<Runnable, Object>();

    private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        testRunnableImpl = new TestRunnableImpl(this);

        handler = new Handler();
        handler.postDelayed(handlerRunner, 0);
	}

    public void registerListener(Runnable r) {
        synchronized (runnableMap) {
            runnableMap.put(r, null);
        }
    }

    Runnable handlerRunner = new Runnable() {
        @Override
        public void run() {
            for (Runnable r : runnableMap.keySet()) {
                    r.run();
            }
            textView.setText("Listener count: " + runnableMap.size());
            handler.postDelayed(handlerRunner, 1000);

            System.gc();
        }
    };
}
