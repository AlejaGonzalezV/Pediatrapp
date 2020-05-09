package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.fragments.ChildRegisterFragment;
import com.example.pediatrapp.fragments.DoctorPhotoFragment;
import com.example.pediatrapp.fragments.DoctorRegisterFragment;
import com.example.pediatrapp.fragments.ParentRegisterFragment;
import com.example.pediatrapp.fragments.RolFragment;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.UUID;


public class SignUpActivity extends AppCompatActivity implements OnDataSubmitted {

    private Fragment rolFragment,parentRegisterFragment, childRegisterFragment, doctorRegisterFragment, doctorPhotoFragment;
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
        doctorRegisterFragment = new DoctorRegisterFragment();
        ((DoctorRegisterFragment) doctorRegisterFragment).setListener(this);
        doctorPhotoFragment = new DoctorPhotoFragment();
        ((DoctorPhotoFragment) doctorPhotoFragment).setListener(this);


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

                datos = "";
                showFragment(parentRegisterFragment);


            } else if(type.equals("pediatra")){

                datos = "";
                showFragment(doctorRegisterFragment);

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


        } else if(fragment.equals(doctorRegisterFragment)){

            if(type.equals("next")){

                showFragment(doctorPhotoFragment);
                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";

                }

            } else if(type.equals("back")){

                showFragment(rolFragment);

            }


        } else if(fragment.equals(doctorPhotoFragment)){


            if(type.equals("nextF")){

                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";

                }

                createUserDoctorF();
                //LAuncheo a la pag principal y guardo los docs

            }else if(type.equals("back")){

                showFragment(doctorRegisterFragment);

            }else if(type.equals("next")){

                for(int j=0; j<args.length; j++) {

                    datos += args[j] + ",";

                }

                createUserDoctor();

            }

        }

    }

    public void createUserDoctorF(){

            String[] str = datos.split(",");
            String nombre = str[0];
            String cedula = str[1];
            String email = str[2];
            String password = str[3];
            String idV = str[4];
            String foto = str[5];
            String firma = str[6];

            Uri uriP = Uri.parse(foto);
            Uri uriF = Uri.parse(firma);

            String id = FirebaseDatabase.getInstance().getReference().child("Pediatras").push().getKey();

            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference().child("Doctor").child(id+"*"+"Foto").putFile(uriP);
            storage.getReference().child("Doctor").child(id+"*"+"Firma").putFile(uriF);
            foto=storage.getReference().child("Doctor").child(id+"*"+"Foto").getDownloadUrl().toString();
            firma=storage.getReference().child("Doctor").child(id+"*"+"Firma").getDownloadUrl().toString();

            Pediatra pediatra = new Pediatra(id,nombre,cedula,email,password,idV,firma,foto);

            //Escribir en la base de datos

            FirebaseDatabase.getInstance().getReference().child("Pediatras").child(id).setValue(pediatra);
            //pediatra.addPadres("1007554028");
            //pediatra.addChat("chat1");
            //pediatra.addChatGrupal("chatGrupal1");

            //String keyChat = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("chats").push().getKey();
            //FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("chats").child(keyChat).setValue(pediatra.getChats());

            //String keyChatG = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("chats_grupales").push().getKey();
            //FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("chats_grupales").child(keyChatG).setValue(pediatra.getChats_grupales());

            //String keyPadres = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("padres_asignados").push().getKey();
            //FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("padres_asignados").child(keyPadres).setValue(pediatra.getPadres_asignados() );

    }

    public void createUserDoctor(){

        String[] str = datos.split(",");
        String nombre = str[0];
        String cedula = str[1];
        String email = str[2];
        String password = str[3];
        String idV = str[4];
        String firma = str[6];

        Uri uriF = Uri.parse(firma);

        String id = FirebaseDatabase.getInstance().getReference().child("Pediatras").push().getKey();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storage.getReference().child("Doctor").child(id+"*"+"Firma").putFile(uriF);
        firma=storage.getReference().child("Doctor").child(id+"*"+"Firma").getDownloadUrl().toString();

        Pediatra pediatra = new Pediatra(id,nombre,cedula,email,password,idV,firma,null);

        //Escribir en la base de datos
        FirebaseDatabase.getInstance().getReference().child("Pediatras").child(id).setValue(pediatra);

    }


}
