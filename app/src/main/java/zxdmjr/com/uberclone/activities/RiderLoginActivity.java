package zxdmjr.com.uberclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zxdmjr.com.uberclone.R;
import zxdmjr.com.uberclone.custom.Toaster;

/**
 * Created by SWAJAN on 11/12/2017.
 */

public class RiderLoginActivity extends AppCompatActivity {

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    @BindView(R.id.et_rider_email)
    EditText etEmail;

    @BindView(R.id.et_rider_password)
    EditText etPassword;

    FirebaseAuth mAuth;
    DatabaseReference riderRef;

    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_login);

        ButterKnife.bind(this);

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppBar);

        collapsingToolbarLayout.setTitle("Rider");

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user != null){
                    gotoMapActivity();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

    @OnClick(R.id.tv_register_rider)
    void register(){

        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString();

        if(email.length() > 0 && password.length() > 0) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RiderLoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toaster.toast(getApplicationContext(), "Sign up error");

                    } else {

                        String user_id = mAuth.getCurrentUser().getUid();

                        riderRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Riders").child(user_id);
                        riderRef.setValue(true);

                    }
                }
            });
        } else{
            Toaster.toast(getApplicationContext(), "Enter email and password");
        }

    }

    @OnClick(R.id.btn_rider_login)
    void doLogin(){

        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString();

        if(email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(RiderLoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toaster.toast(getApplicationContext(), "Wrong credential");

                    }
                }
            });
        } else{
            Toaster.toast(getApplicationContext(), "Enter email and password");
        }

    }


    private void gotoMapActivity() {

        Intent intent = new Intent(this, RiderMapActivity.class);
        startActivity(intent);
        finish();
        return;
    }
}
