package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.pagercontroller.PagerController;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class PediatraPerfil extends AppCompatActivity {

    private CircleImageView pediatra_foto;
    private TextView nombre_pediatra;
    private TextView email_pediatra;
    private TextView numeroUnico_pediatra;
    private Fragment fragmentChat;
    private String idPediatra;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pediatra_perfil);

        Intent intent = getIntent();
        idPediatra = intent.getStringExtra("userid");

        pediatra_foto = findViewById(R.id.pediatra_foto);
        nombre_pediatra = findViewById(R.id.nombre_pediatra);
        email_pediatra = findViewById(R.id.email_pediatra);
        numeroUnico_pediatra = findViewById(R.id.numeroID);
        back = findViewById(R.id.back);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Pediatra ped = child.getValue(Pediatra.class);
                    if(ped.getId().equals(idPediatra)){

                        nombre_pediatra.setText(ped.getNombre());
                        email_pediatra.setText(ped.getCorreo());
                        numeroUnico_pediatra.setText(ped.getIdV());

                        File imageFile = new File(getApplicationContext().getExternalFilesDir(null) + "/" + ped.getFoto());

                        if(imageFile.exists()){

                            loadImage(pediatra_foto, imageFile);

                        }else {

                            storage.getReference().child("Doctor").child(ped.getFoto()).getDownloadUrl().addOnSuccessListener(
                                    uri -> {
                                        Glide.with(getApplicationContext()).load(uri).centerCrop().into(pediatra_foto);
                                    }
                            );

                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        back.setOnClickListener(

                (v)->{

                    this.finish();

                }

        );

    }

    public void loadImage(ImageView imageView, File file){
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        imageView.setImageBitmap(bitmap);

    }
}
