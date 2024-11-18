package com.example.btgiuaki;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class tinh_BMI_Activity extends AppCompatActivity {
    EditText editTextText_HVT,editTextText_Tuoi,editTextText_CC,editTextText_CN,editTextText_GT;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tinh_bmi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        editTextText_HVT = findViewById(R.id.editTextText_HVT);
        editTextText_Tuoi = findViewById(R.id.editTextText_Tuoi);
        editTextText_CC = findViewById(R.id.editTextText_CC);
        editTextText_CN = findViewById(R.id.editTextText_CN);
        editTextText_GT = findViewById(R.id.editTextText_GT);
        editTextText_HVT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (input.length() < 2) {
                    editTextText_HVT.setError("Nhập tối thiểu 2 kí tự");
                }
            }
        });
        editTextText_GT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if (input.length() < 2) {
                    editTextText_GT.setError("Nhập tối thiểu 2 kí tự");
                }
                if (!input.equals("nam") && !input.equals("nu")){
                    editTextText_GT.setError("Nhập 'nam' hoặc 'nu'");
                }
            }
        });
    }
    public void btn_TT(View view) {
        editTextText_HVT = findViewById(R.id.editTextText_HVT);
        editTextText_Tuoi = findViewById(R.id.editTextText_Tuoi);
        editTextText_CC = findViewById(R.id.editTextText_CC);
        editTextText_CN = findViewById(R.id.editTextText_CN);
        editTextText_GT = findViewById(R.id.editTextText_GT);
        String HVT = editTextText_HVT.getText().toString().trim();
        String gioitinh = editTextText_GT.getText().toString().trim();
        int tuoi = Integer.parseInt(editTextText_Tuoi.getText().toString().trim());
        double chieucao = Double.parseDouble(editTextText_CC.getText().toString().trim());
        int chieucao1 = Integer.parseInt(editTextText_CC.getText().toString().trim());
        int cannang = Integer.parseInt(editTextText_CN.getText().toString().trim());
        chieucao = chieucao / 100;
        double BMI = cannang / (chieucao * chieucao);
        String S_BMI;
        if (BMI < 18.5) {
            S_BMI = "Thiếu cân";
        } else if (BMI >= 18.5 && BMI < 25) {
            S_BMI = "Bình thường";
        } else {
            S_BMI = "Thừa cân";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cảnh báo BMI cao");
            builder.setMessage("BMI của bạn thuộc loại 'Thừa cân'. Hãy duy trì chế độ ăn uống và tập luyện lành mạnh!");
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss()); // Đóng dialog
            builder.show();
        }
        writeNewUser(HVT, tuoi,chieucao1, cannang, gioitinh, S_BMI);
    }

    public void writeNewUser(String name, int age, int height, int weight, String gender, String bmi) {
        Person user = new Person(name, gender, bmi, age, height, weight);

        mDatabase.child("TT_Person").push().setValue(user)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Thêm người dùng thành công!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi khi thêm người dùng: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void btn_XTT(View view){
        Intent myIntent = new Intent(this, TT_Activity.class);
        startActivity(myIntent);
    }
}