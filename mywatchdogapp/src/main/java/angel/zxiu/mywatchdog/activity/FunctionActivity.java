package angel.zxiu.mywatchdog.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.object.Function;

/**
 * Created by zxui on 16/10/15.
 */
public class FunctionActivity extends AppCompatActivity {
    Function func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        try {
            func = (Function) getIntent().getSerializableExtra("Function");
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, (Fragment) func.clazz.newInstance()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
