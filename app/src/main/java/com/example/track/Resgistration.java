package com.example.track;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Resgistration extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mName, mEmail, mPassword;
    String userID;
    Button mregisterButton;
    TextView mLogInButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistration);
        setContentView(R.layout.activity_resgistration);

        mName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mregisterButton = findViewById(R.id.register);
        mLogInButton = findViewById(R.id.LogIn);

        firebaseAuth = FirebaseAuth.getInstance();

        //fireStore = FirebaseAuth.getInstance();


        mregisterButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                final String name = mName.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email field is empty");
                    return;

                }

                if (TextUtils.isEmpty(Password)) {
                    mPassword.setError("Password field is empty");
                    return;
                }
                //password checker
                //at least one number,at least one uppercase
                //at least one lower case, no spaces at all
                //betweenn 6-10 char long(including 6 and 10)
                if ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,10}$".matches(Password)) {
                    mPassword.setError("Password must contain at least one number and be 6-10 characters");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete (@NonNull Task <AuthResult>task){

                    if (task.isSuccessful()) {
                        Toast.makeText(Resgistration.this, "User Created.", Toast.LENGTH_SHORT).show();
                        userID = firebaseAuth.getCurrentUser().getUid();
                        DocumentReference documentReference;
                        documentReference = fStore.collection("customers").document(userID);
                        Map<String, Object> user = new HashMap<>();

                        user.put("email", email);
                        user.put("FullName",name);

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                            }

                        });
                    }


                }

                })
            ;}
        });
    }
}


