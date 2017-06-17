package smart.ccc;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import smart.ccc.Frament1;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class main extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout bt_1,bt_2,bt_3,bt_4,bt_5;
    private Toolbar toolbar1;
    private Frament1 tab1;
    private Frament2 tab2;
    /*private Frament3 tab3;*/
    private Frament4 tab4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tab1=new Frament1();
        tab2=new Frament2();
        tab4=new Frament4();
        initView();
    /*    toolbar1.setTitle("主页");
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        bt_1.setBackgroundResource(R.drawable.main_clicked);
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab1,"tab1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab2,"tab2").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab4,"tab4").commit();
        getSupportFragmentManager().beginTransaction().hide(tab2).hide(tab4).show(tab1).commit();
 /*       getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab1,"tab1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab1,"tab1").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.rmlayout,tab1,"tab1").commit();*/



    }

    private void initView() {
        bt_1= (LinearLayout) findViewById(R.id.bt_1);
        bt_2= (LinearLayout) findViewById(R.id.bt_2);
        bt_3= (LinearLayout) findViewById(R.id.bt_3);
        bt_4= (LinearLayout) findViewById(R.id.bt_4);
        bt_5= (LinearLayout) findViewById(R.id.bt_5);

        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);

        toolbar1= (Toolbar) findViewById(R.id.toolbar1);
    }

    private  void  initImage()
    {
        bt_1.setBackgroundResource(R.drawable.main);
        bt_2.setBackgroundResource(R.drawable.search);
        bt_3.setBackgroundResource(R.drawable.comments);
        bt_4.setBackgroundResource(R.drawable.account);
        bt_5.setBackgroundResource(R.drawable.account);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_1:
                if (tab2 != null&&tab4!=null)
                    getSupportFragmentManager().beginTransaction().hide(tab2).hide(tab4).show(tab1).commit();
                else {
                    tab2= (Frament2) getSupportFragmentManager().findFragmentByTag("tab2");
                    tab4= (Frament4) getSupportFragmentManager().findFragmentByTag("tab4");
                    getSupportFragmentManager().beginTransaction().hide(tab2).hide(tab4).show(tab1).commit();
                }
                initImage();
                bt_1.setBackgroundResource(R.drawable.main_clicked);
                  break;

            case R.id.bt_2:
                if(tab1!=null&&tab4!=null)
                    getSupportFragmentManager().beginTransaction().hide(tab1).hide(tab4).show(tab2).commit();
                else{
                    tab1= (Frament1) getSupportFragmentManager().findFragmentByTag("tab1");
                    tab4= (Frament4) getSupportFragmentManager().findFragmentByTag("tab4");
                    getSupportFragmentManager().beginTransaction().hide(tab1).hide(tab4).show(tab2).commit();
                }
                initImage();
                bt_2.setBackgroundResource(R.drawable.search_clicked);
                break;

            case R.id.bt_4:

                if(tab1!=null&&tab2!=null)
                    getSupportFragmentManager().beginTransaction().hide(tab1).hide(tab2).show(tab4).commit();
                else{
                    tab1= (Frament1) getSupportFragmentManager().findFragmentByTag("tab1");
                    tab2= (Frament2) getSupportFragmentManager().findFragmentByTag("tab2");
                    getSupportFragmentManager().beginTransaction().hide(tab1).hide(tab2).show(tab4).commit();
                }
                initImage();
                bt_4.setBackgroundResource(R.drawable.account_clicked);
                break;

            case R.id.bt_5:
                startActivity(new Intent(main.this,SendArticleActivity.class));



        }




    }
}
