package com.example.btgiuaki;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_Activity extends AppCompatActivity {
    EditText editText_user,editText_pass;
    private DatabaseReference mDatabase;

    public static class User {

        public String username;
        public String password;
        public User() {
        }
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void btn_DK(View view){
        editText_user = findViewById(R.id.EditText_user);
        editText_pass = findViewById(R.id.EditText_pass);
        String user_name = editText_user.getText().toString().trim();
        String pass_word = editText_pass.getText().toString().trim();
        Toast.makeText(login_Activity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        writeNewUser(user_name,pass_word);
    }
    public void writeNewUser(String name, String password) {
        User user = new User(name, password);
        mDatabase.child("users").push().setValue(user);
    }

    public void btn_login(View view){
        editText_user = findViewById(R.id.EditText_user);
        editText_pass = findViewById(R.id.EditText_pass);
        String user_name = editText_user.getText().toString().trim();
        String pass_word = editText_pass.getText().toString().trim();
        if (user_name.isEmpty() || pass_word.isEmpty()){
            Toast.makeText(login_Activity.this, "Please Enter Your Phone Number Or Password", Toast.LENGTH_SHORT).show();
        } else {
            mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean userFound = false;
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String username = userSnapshot.child("username").getValue(String.class);
                        Log.d(TAG, "Value is: " + username);
                        if (user_name.equals(username)) {
                            userFound = true;
                            String getpassword = userSnapshot.child("password").getValue(String.class);
                            if (getpassword != null && getpassword.equals(pass_word)){
                                Intent myintent = new Intent(login_Activity.this , tinh_BMI_Activity.class);
                                startActivity(myintent);
                            } else {
                                Toast.makeText(login_Activity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        }
                    }
                    if (!userFound) {
                        Toast.makeText(login_Activity.this, "Wrong name!", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}