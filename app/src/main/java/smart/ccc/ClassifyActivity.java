package smart.ccc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/6/18 0018.
 */

public class ClassifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classifyactivity);
        ClassifyFrament classifyFrament=new ClassifyFrament();
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,classifyFrament,"classifyFrament").commit();
        getSupportFragmentManager().beginTransaction().show(classifyFrament).commit();

    }
}
