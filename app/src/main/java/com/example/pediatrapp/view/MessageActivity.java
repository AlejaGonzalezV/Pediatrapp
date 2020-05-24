package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.controller.MessageController;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    private CircleImageView profile_image;
    private TextView username;



    private EditText text_send;
    private ImageButton btn_send;
    private ImageButton btn_media;
    private ImageView messageIV;
    private String type;
    private String userid;
    private ListView message_list;
    private MessageController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
         userid = intent.getStringExtra("userid");
        type = intent.getStringExtra("type");

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "tooooooolbaaaar pop");
                finish();
                //CREO QUE AQUI ES DONDE SE PUEDE DECIR QUE ABRA LA VISTA DEL PERFIL DEL USUARIO CON EL QUE HABLA
            }
        });

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        text_send = findViewById(R.id.text_send);
        btn_send = findViewById(R.id.btn_send);
        btn_media = findViewById(R.id.btn_media);
        message_list = findViewById(R.id.message_list);
        messageIV = findViewById(R.id.messageIV);

        controller = new MessageController(this);

    }

    public CircleImageView getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(CircleImageView profile_image) {
        this.profile_image = profile_image;
    }

    public TextView getUsername() {
        return username;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public EditText getText_send() {
        return text_send;
    }

    public void setText_send(EditText text_send) {
        this.text_send = text_send;
    }

    public ImageButton getBtn_send() {
        return btn_send;
    }

    public void setBtn_send(ImageButton btn_send) {
        this.btn_send = btn_send;
    }

    public ImageButton getBtn_media() {
        return btn_media;
    }

    public void setBtn_media(ImageButton btn_media) {
        this.btn_media = btn_media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public MessageController getController() {
        return controller;
    }

    public void setController(MessageController controller) {
        this.controller = controller;
    }

    public ListView getMessage_list() {
        return message_list;
    }

    public void setMessage_list(ListView message_list) {
        this.message_list = message_list;
    }

    public ImageView getMessageIV() {
        return messageIV;
    }

    public void setMessageIV(ImageView messageIV) {
        this.messageIV = messageIV;
    }

    @Override
    protected void onPause() {
        controller.beforePause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.e(">>>", "On Resume");
        controller.beforeResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        controller.onActivityResult(requestCode, resultCode, data);
    }

    public void hideImage(){
        messageIV.setVisibility(View.GONE);
    }

    public void showImage(){
        messageIV.setVisibility(View.VISIBLE);
    }
}
