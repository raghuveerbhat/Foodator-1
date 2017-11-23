package cse.com.foodator.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cse.com.foodator.R;
import cse.com.foodator.Utils.FirebaseMethods;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    //firebase authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    private TextView mPleaseWait;
    private Button btnRegister;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext=RegisterActivity.this;
        firebaseMethods = new FirebaseMethods(mContext);
        Log.d(TAG, "onCreate: Started...");

        initWidgets();
        setupFirebaseAuth();
        init();
    }

    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if(checkInputsForNull(email,username,password)){
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);
                    firebaseMethods.registerNewEmail(email,password,username);
                }
            }
        });
    }

    private boolean checkInputsForNull(String email,String username, String pwd) {
        if(email.equals("") || username.equals("") || pwd.equals("")) {
            Toast.makeText(mContext, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Initialise all activity widgets
     */
    private void initWidgets() {
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mEmail = (EditText) findViewById(R.id.input_email);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mUsername = (EditText) findViewById(R.id.input_username);
        mPassword = (EditText) findViewById(R.id.input_password);
        mContext = RegisterActivity.this;
        mProgressBar.setVisibility(View.GONE);
        mPleaseWait.setVisibility(View.GONE);
    }

    private boolean isStringNull(String str) {
        Log.d(TAG, "isStringNull: checking if string is null");

        if(str.equals("")) {
            return true;
        }
        return false;
    }


    /**
     * ---------------------------------------firebase stuff------------------------------------------
     */
    /**
     * setup firebase auth object
     */
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting firebase auth");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
