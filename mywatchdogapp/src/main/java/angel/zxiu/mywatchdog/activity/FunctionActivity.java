package angel.zxiu.mywatchdog.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import angel.zxiu.mywatchdog.R;
import angel.zxiu.mywatchdog.object.Function;

/**
 * Created by zxui on 16/10/15.
 */
public class FunctionActivity extends AppCompatActivity {
    Function func;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            func = (Function) getIntent().getSerializableExtra("Function");
            getSupportActionBar().setTitle(func.nameResId);
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, (Fragment) func.clazz.newInstance()).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
