package br.com.collegenotebook.view.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.LoginController;

/**
 * Created by GRodrigues17 on 28/01/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    SignInButton btnSignGoogle;
    Button btnLoginIn;


    private EditText edtLoginUser;
    private EditText edtLoginPassword;
    private String userEmail;
    private String userPassword;
    private View viewSignUp;
    private TextInputLayout inputLogin;
    private TextInputLayout inputPassword;
    private AnimationDrawable anim;

    private LoginController loginController;
    private Resources resources;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //loginController = new LoginController(this);
        initViews();
        loginController = new LoginController(this);

        //btnSignGoogle = (SignInButton) findViewById(R.id.btnSignGoogle);


        RelativeLayout container = (RelativeLayout) findViewById(R.id.root_layout);

        anim = (AnimationDrawable) container.getBackground();
        anim.setEnterFadeDuration(6000);
        anim.setExitFadeDuration(2000);

        viewSignUp = (View) findViewById(R.id.layoutFooter);
        viewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);


            }

        });

    }

    private void initViews() {
        resources = getResources();
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                callClearErrors(s);
            }
        };

        inputLogin = (TextInputLayout) findViewById(R.id.inputLogin);
        inputPassword = (TextInputLayout) findViewById(R.id.inputPassword);
        edtLoginUser = (EditText) findViewById(R.id.edtLoginUser);
        edtLoginUser.addTextChangedListener(textWatcher);
        edtLoginPassword = (EditText) findViewById(R.id.edtLoginPassword);
        edtLoginPassword.addTextChangedListener(textWatcher);
        btnLoginIn = (Button) findViewById(R.id.btnLoginIn);
        btnLoginIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnLoginIn) {

            if (validateLoginFields()) {
                try {
                    if(hasLoginValid()){
                        Bundle bundle = new Bundle();
                        bundle.putString("userEmail", userEmail);
                        bundle.putString("userPassword", userPassword);
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else
                        inputLogin.setError(resources.getString(R.string.email_size_invalid)); // show error
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean validateLoginFields()  {
        userEmail = edtLoginUser.getText().toString().trim();
        userPassword = edtLoginPassword.getText().toString().trim();
        return (!isEmptyLogin(userEmail,userPassword));
    }


    private boolean isEmptyLogin(String userEmail, String userPassword) {
        if (TextUtils.isEmpty(userEmail)) {
            edtLoginUser.requestFocus(); //seta o foco para o campo password
            inputLogin.setError(resources.getString(R.string.email_required)); // show error
            return true;
        } else if (TextUtils.isEmpty(userPassword)) {
            edtLoginPassword.requestFocus(); //seta o foco para o campo password
            inputPassword.setError(resources.getString(R.string.password_required));
            return true;
        }
        return false;
    }

    private boolean hasLoginValid() {
        userEmail = edtLoginUser.getText().toString().trim();
        userPassword = edtLoginPassword.getText().toString().trim();

        try {
            if (loginController.validaLogin(userEmail, userPassword)!=null){
                Toast.makeText(this, "loginExiste", Toast.LENGTH_SHORT).show();
                return true;

            } else {
                Toast.makeText(this, "loginnão exite", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void clearErrorFields(TextInputLayout... textInputLayouts) {
        for (TextInputLayout inputLayout : textInputLayouts) {
            inputLayout.setError(null);
        }
    }

    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(inputLogin);
            clearErrorFields(inputPassword);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }


}
