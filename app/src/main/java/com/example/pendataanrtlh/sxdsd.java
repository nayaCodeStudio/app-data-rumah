//package com.example.ta;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.text.method.HideReturnsTransformationMethod;
//import android.text.method.PasswordTransformationMethod;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.example.ta.model.User;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//public class SignUp extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
//
//    EditText edtPhone, edtName, edtPassword;
//    Button btnSignUp;
//    CheckBox unHidePass;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        edtPhone = findViewById(R.id.no_hp);
//        edtName = findViewById(R.id.name);
//        edtPassword = findViewById(R.id.password);
//
//        unHidePass = findViewById(R.id.check_box);
//        unHidePass.setOnCheckedChangeListener(this);
//
//        btnSignUp = findViewById(R.id.btn_signUp);
//
//        //Init firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference table_user = database.getReference("User");
//
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phone = edtPhone.getText().toString();
//                String name = edtName.getText().toString();
//                final String pass = edtPassword.getText().toString();
//                if (TextUtils.isEmpty(phone)) {
//                    edtPhone.setError("This field is can't empty!");
//                    Toast.makeText(getApplicationContext(), "Masukkin No. HP Kamu dong!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(pass)) {
//                    edtPassword.setError("This field is can't empty!");
//                    Toast.makeText(getApplicationContext(), "Masukkin password Kamu dong!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(name)) {
//                    edtName.setError("This field is can't empty!");
//                    Toast.makeText(getApplicationContext(), "Masukkin nama Kamu dong!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
//                mDialog.setMessage("Please Waiting....");
//                mDialog.show();
//
//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        //Check if already userphone
//                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
//                            mDialog.dismiss();
//                            Toast.makeText(SignUp.this, "Phone Number Already Register !", Toast.LENGTH_SHORT).show();
//                        } else {
//                            mDialog.dismiss();
//                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
//                            table_user.child(edtPhone.getText().toString()).setValue(user);
//                            Toast.makeText(SignUp.this, "Sign Up Successfully !", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//        });
//
//    }
//
//
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (!isChecked) {
//            edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        } else {
//            edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//        }
//    }
//}