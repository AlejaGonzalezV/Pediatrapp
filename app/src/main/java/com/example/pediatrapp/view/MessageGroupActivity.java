package com.example.pediatrapp.view;

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

import com.example.pediatrapp.R;
import com.example.pediatrapp.controller.MessageGController;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageGroupActivity extends AppCompatActivity {

    private CircleImageView profile_image;
    private TextView username;
    private EditText text_send;
    private ImageButton btn_send;
    private ImageButton btn_media;
    private ImageView messageIV;
    private String type;
    private String chatGrup;
    private String idPadre;
    private String nombre_Chat;
    private ListView message_list;
    private MessageGController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Intent intent = getIntent();
        chatGrup = intent.getStringExtra("chat");
        type = intent.getStringExtra("type");
        idPadre = intent.getStringExtra("idpadre");
        nombre_Chat = intent.getStringExtra("nombre_chat");

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(">>>", "tooooooolbaaaar pop");
                finish();
            }
        });

        profile_image = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        text_send = findViewById(R.id.text_send);
        btn_send = findViewById(R.id.btn_send);
        btn_media = findViewById(R.id.btn_media);
        message_list = findViewById(R.id.message_list);
        messageIV = findViewById(R.id.messageIV);

        controller = new MessageGController(this);
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

    public ImageView getMessageIV() {
        return messageIV;
    }

    public void setMessageIV(ImageView messageIV) {
        this.messageIV = messageIV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChatGrup() {
        return chatGrup;
    }

    public void setChatGrup(String chatGrup) {
        this.chatGrup = chatGrup;
    }

    public ListView getMessage_list() {
        return message_list;
    }

    public void setMessage_list(ListView message_list) {
        this.message_list = message_list;
    }

    public MessageGController getController() {
        return controller;
    }

    public void setController(MessageGController controller) {
        this.controller = controller;
    }

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombre_Chat() {
        return nombre_Chat;
    }

    public void setNombre_Chat(String nombre_Chat) {
        this.nombre_Chat = nombre_Chat;
    }
}
