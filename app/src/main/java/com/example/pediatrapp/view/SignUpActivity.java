package com.example.pediatrapp.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.fragments.ChildRegisterFragment;
import com.example.pediatrapp.fragments.DoctorPhotoFragment;
import com.example.pediatrapp.fragments.DoctorRegisterFragment;
import com.example.pediatrapp.fragments.FotoPadreFragment;
import com.example.pediatrapp.fragments.ParentRegisterFragment;
import com.example.pediatrapp.fragments.RolFragment;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.ChatGrupal;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity implements OnDataSubmitted {

    private Fragment rolFragment,parentRegisterFragment, childRegisterFragment, doctorRegisterFragment, doctorPhotoFragment, fotoPadreFragment;
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
        fotoPadreFragment = new FotoPadreFragment();
        ((FotoPadreFragment) fotoPadreFragment).setListener(this);


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

                datos = null;
                showFragment(rolFragment);

            }


        } else if(fragment.equals(childRegisterFragment)){

            if(type.equals("next")){


                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";
                    showFragment(fotoPadreFragment);

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

                datos = null;
                showFragment(rolFragment);

            }


        } else if(fragment.equals(doctorPhotoFragment)){


            if(type.equals("next")){

                for(int j=0; j<args.length; j++){

                    datos += args[j] + ",";

                }

                createUserDoctor();


            }else if(type.equals("back")){

                showFragment(doctorRegisterFragment);

            }

        } else if(fragment.equals(fotoPadreFragment)){

            if(type.equals("next")){

                datos += args[0];
                createUserParent();


            }

        }

    }

    public void createUserParent(){

            Log.e("ENTRE AQUI HOLA", "eNTREEEEEEEEEEEEEEEEEEEEEEEE");
            String[] str = datos.split(",");
            String nombre = str[0];
            String cedula = str[1];
            String email = str[2];
            String password = str[3];
            String direccion = str[4];
            String cel = str[5];
            String nombreH = str[6];
            String identH = str[7];
            String fechaH = str[8];
            String generoH = str[9];
            String idDoc = str[10];
            String foto1 = str[11];


            //Registro en firebase
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {


                FirebaseUser user = auth.getCurrentUser();
                String id = user.getUid();

                String idH = FirebaseDatabase.getInstance().getReference().child("Padres").child(id).child("hijos").push().getKey();
                Hijo hijo = new Hijo(idH, identH, fechaH, generoH, nombreH);

                HashMap<String, Hijo> hijos = new HashMap<>();
                hijos.put(idH, hijo);

                HashMap<String,String> pediatrasAsig = new HashMap<>();
                pediatrasAsig.put(idDoc, idDoc);

                Uri uriP;

                if(foto1.equals("no")){

                    uriP = Uri.parse("android.resource://" + this.getPackageName()
                            + "/" + R.drawable.user);
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    storage.getReference().child("Padre").child(id).putFile(uriP);

                } else {

                    uriP = Uri.parse(foto1);
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    storage.getReference().child("Padre").child(id).putFile(uriP);

                }



                String foto= id;

                Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot child: dataSnapshot.getChildren()){

                            Pediatra ped = child.getValue(Pediatra.class);

                            if(ped.getId().equals(idDoc)){

                                String idc = FirebaseDatabase.getInstance().getReference().child("chat").push().getKey();
                                Chat chat = new Chat(foto, ped.getFoto(), nombre, ped.getNombre(), id, idDoc, idc);
                                HashMap<String, String> hashChat = new HashMap<>();
                                hashChat.put(idc,idc);
                                Padre padre = new Padre(id,cedula,nombre,email,password,direccion,cel,foto,pediatrasAsig, hijos, hashChat);
                                FirebaseDatabase.getInstance().getReference().child("Padres").child(id).setValue(padre);
                                FirebaseDatabase.getInstance().getReference().child("Pediatras").child(idDoc).child("Padres_asignados").child(id).setValue(id);
                                FirebaseDatabase.getInstance().getReference().child("Pediatras").child(idDoc).child("chats").child(idc).setValue(idc);
                                FirebaseDatabase.getInstance().getReference().child("chat").child(idc).setValue(chat);
                                cargar();

                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                }).addOnFailureListener(e -> {
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                });


    }

    public void cargar(){

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void cargarPed(){

        Intent intent = new Intent(this, ActivityMainPediatra.class);
        intent.addFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    public void createUserDoctor(){

            String[] str = datos.split(",");
            String nombre = str[0];
            String cedula = str[1];
            String email = str[2];
            String password = str[3];
            String idV = str[4];


            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {
                FirebaseUser user = auth.getCurrentUser();
                String id = user.getUid();

                String foto = str[5];
                String firma = str[6];

                Uri uriP = Uri.parse(foto);
                Uri uriF = Uri.parse(firma);

                FirebaseStorage storage = FirebaseStorage.getInstance();
                storage.getReference().child("Doctor").child(id+"*"+"Foto").putFile(uriP);
                storage.getReference().child("Doctor").child(id+"*"+"Firma").putFile(uriF);
                foto= id+"*"+"Foto";
                firma= id+"*"+"Firma";

                HashMap<String,String>chatsGrupales = new HashMap<>();
                HashMap<String, ChatGrupal>objChatGrupal = new HashMap<>();

                Query query = FirebaseDatabase.getInstance().getReference().child("Chat_grupal");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                             for(DataSnapshot child: dataSnapshot.getChildren()){

                                                                 chatsGrupales.put(child.getKey(),child.getKey());
                                                                 objChatGrupal.put(child.getKey(), child.getValue(ChatGrupal.class));


                                                             }


                                                         }

                                                         @Override
                                                         public void onCancelled(@NonNull DatabaseError databaseError) {

                                                         }
                                                     });

                        Pediatra pediatra = new Pediatra(id, nombre, cedula, email, password, idV, firma, foto, "offline", chatsGrupales);


                        //Recorrido hash por revisar
                for(ChatGrupal chat: objChatGrupal.values()){

                    chat.addPediatra(id, id);

                }

                //Se sube hash chat grupal. por revisar
                FirebaseDatabase.getInstance().getReference().child("Chat_grupal").setValue(objChatGrupal);
                FirebaseDatabase.getInstance().getReference().child("Pediatras").child(id).setValue(pediatra);
                cargarPed();

            }).addOnFailureListener(e -> {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            });

    }


}
