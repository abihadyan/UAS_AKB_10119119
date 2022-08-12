package com.example.uasakbif310119119.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uasakbif310119119.MainActivity;
import com.example.uasakbif310119119.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private EditText editName, editEmail, editPassword, editPasswordConf;
    private Button btn_register, btn_login;
    private FirebaseAuth mAuth;
//    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editPasswordConf = findViewById(R.id.password_conf);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
//        progressDialog = new ProgressDialog(RegisterActivity.this);
//        progressDialog.setTitle("Loading");
//        progressDialog.setMessage("Silahkan Tunggu");
//        progressDialog.setCancelable(false);

        btn_login.setOnClickListener(v -> {
            finish();
        });

        btn_register.setOnClickListener(v -> {
            if(editName.getText().length()>0 && editEmail.getText().length()>0 && editPassword.getText().length()>0
            && editPassword.getText().length()>0){
                if(editPassword.getText().toString().equals(editPasswordConf.getText().toString())){
                    register(editName.getText().toString(), editEmail.getText().toString(),
                            editPassword.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Isi terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(String name, String email, String password){
//        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult()!=null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if(firebaseUser!=null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Register Gagal",
                                Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}