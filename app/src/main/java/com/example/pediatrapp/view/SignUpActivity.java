package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.fragments.ChildRegisterFragment;
import com.example.pediatrapp.fragments.ParentRegisterFragment;
import com.example.pediatrapp.fragments.RolFragment;


public class SignUpActivity extends AppCompatActivity implements OnDataSubmitted {

    private Fragment rolFragment,parentRegisterFragment, childRegisterFragment;
    private LinearLayout layout;
    private String datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        layout = findViewById(R.id.layout);

        rolFragment = new RolFragment();
        ((RolFragment) rolFragment).setListener(this);
        parentRegisterFragment = new ParentRegisterFragment();
        ((ParentRegisterFragment) parentRegisterFragment).setListener(this);
        childRegisterFragment = new ChildRegisterFragment();
        ((ChildRegisterFragment) childRegisterFragment).setListener(this);
        showFragment(rolFragment);


    }

    public void showFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.layout, fragment);
        transaction.commit();


    }

    public String getDatos(){

        return datos;

    }

    @Override
    public void onData(Fragment fragment, String type, String... args) {

        if(fragment.equals(rolFragment)){

            if(type.equals("padre")){

                showFragment(parentRegisterFragment);


            } else if(type.equals("pediatra")){



            } else if(type.equals("back")){

                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent, 0);

            }

        } else if(fragment.equals(parentRegisterFragment)){

            if(type.equals("next")){

                showFragment(childRegisterFragment);
                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";

                }


            } else if(type.equals("back")){

                showFragment(rolFragment);

            }


        } else if(fragment.equals(childRegisterFragment)){

            if(type.equals("next")){

                //Se hace launch a la nueva actividad donde esté el chat
                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";

                }

            } else if(type.equals("back")){

                showFragment(parentRegisterFragment);

            }


        }



    }
}
