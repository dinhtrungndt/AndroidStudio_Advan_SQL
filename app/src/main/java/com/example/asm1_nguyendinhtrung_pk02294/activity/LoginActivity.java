package com.example.asm1_nguyendinhtrung_pk02294.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm1_nguyendinhtrung_pk02294.R;
import com.example.asm1_nguyendinhtrung_pk02294.dao.NguoiDungDAO;
import com.example.asm1_nguyendinhtrung_pk02294.service.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser,edtPass;
    private Button btnRegister,btnLogin,btnLogout;
    private IntentFilter intentFilter;
    boolean passwordEye;

    GoogleSignInClient mGoogleSignInClient;
    SignInButton btnSignInButton;

    CallbackManager callbackManager;
    LoginButton btnLoginFB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        // GG
        btnSignInButton = findViewById(R.id.btnSignInGG);
        btnLogout = findViewById(R.id.btnLogout);

        //FB
        btnLoginFB = findViewById(R.id.btnLoginFB);
        callbackManager = CallbackManager.Factory.create();

        //check người dùng đã đăng nhập trước đó hay chưa?
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            getUserProfile(accessToken);
            Toast.makeText(this, "Đã đăng nhập", Toast.LENGTH_SHORT).show();
        }

        //nút đăng nhập/logout
        btnLoginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Hủy đăng nhập", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Lỗi trong quá trình đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Khai báo intentFillter
        intentFilter = new IntentFilter();
        intentFilter.addAction("dangNhap");

        edtPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX() >= edtPass.getRight() - edtPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = edtPass.getSelectionEnd();
                        if (passwordEye){
                            // set drawable image here
                            edtPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eye_off,0);
                            // for hide password
                            edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordEye = false;
                        }else{
                            // set drawable image here
                            edtPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.eye_onl,0);
                            // for show password
                            edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordEye = true;
                        }
                        edtPass.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                Intent intent = new Intent(LoginActivity.this, LoginService.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",user);
                bundle.putString("pass",pass);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        btnSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                checkLogin.launch(signInIntent);
            }
        });



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                Toast.makeText(LoginActivity.this, "Wellcome Register", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btnSignInButton.setVisibility(View.VISIBLE);
                        btnLogout.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    ActivityResultLauncher<Intent> checkLogin = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                        try {
                            //đăng nhập thành công
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            String displayName = account.getDisplayName();
                            String email = account.getEmail();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công - " + displayName + " - " + email, Toast.LENGTH_SHORT).show();
                            btnSignInButton.setVisibility(View.GONE);
                            btnLogout.setVisibility(View.VISIBLE);
                            startActivity(new Intent(LoginActivity.this, Support_Activity.class));
                        } catch (ApiException e) {
                            //đăng nhập thất bại
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(myBroadcast,intentFilter);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            Toast.makeText(this, "Đã đăng nhập tài khoản " + account.getEmail(), Toast.LENGTH_SHORT).show();
            btnSignInButton.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcast);
    }

    public BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // check action thực hiện chức năng
            if (intent.getAction().equals("dangNhap")){
                Bundle bundle = intent.getExtras();
                int idNguoiDung = bundle.getInt("idNguoiDung");

                if(idNguoiDung == -1){
                    Toast.makeText(context, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    Toast.makeText(context, "Đăng nhâp thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

                SharedPreferences sharedPreferences = getSharedPreferences("DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idNguoiDung",idNguoiDung);
                editor.apply();
                startActivity(new Intent(LoginActivity.this,Support_Activity.class));
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("id");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                    Toast.makeText(LoginActivity.this, name + " - " + email + " - " + image, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void saveLogin(String user, String pass){
        user = edtUser.getText().toString();
        pass = edtPass.getText().toString();
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        int id = nguoiDungDAO.Login(user,pass);
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.putInt("id",id);
        editor.commit();
    }

    private void readLogin(){
        SharedPreferences preferences = getSharedPreferences("LOGIN_STATUS", MODE_PRIVATE);
        Boolean isLoggedIn = preferences.getBoolean("isLoggedIn",false);
        int id = preferences.getInt("id",-1);
        if(isLoggedIn){
            if(id != -1){
                startActivity(new Intent(LoginActivity.this,Support_Activity.class));
                finish();
            }
        }
    }

}