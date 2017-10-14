package br.com.collegenotebook.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.collegenotebook.R;
import br.com.collegenotebook.controller.LoginController;

/**
 * Created by GRodrigues17 on 06/02/2017.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfPass;

    String newEmail;
    String newPassword;
    String confPass;
    Button btnCreateAccount;

    TextInputLayout inputEmail;
    TextInputLayout inputPass;
    TextInputLayout inputConfPass;

    private Resources resources;
    private LoginController loginController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        loginController = new LoginController(this);
        initViews();
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

        inputEmail = (TextInputLayout) findViewById(R.id.inputNewEmail);
        inputPass= (TextInputLayout) findViewById(R.id.inputPass);
        inputConfPass = (TextInputLayout) findViewById(R.id.inputConfirmPassword);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtEmail.addTextChangedListener(textWatcher);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword.addTextChangedListener(textWatcher);
        edtConfPass = (EditText) findViewById(R.id.edtConfirmPassword);
        edtConfPass.addTextChangedListener(textWatcher);
        btnCreateAccount = (Button) findViewById(R.id.btnCreate);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCreate) {
            if (validateFields()) {
                if (verifyEmail()){
                    loginController.saveUser(newEmail, newPassword);
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                    bundle.putString("userEmail", newEmail);
                    i.putExtras(bundle);
                    startActivity(i);
                }

            }
        }

    }

    private boolean validateFields() {
        newEmail = edtEmail.getText().toString().trim();
        newPassword = edtPassword.getText().toString().trim();
        confPass = edtConfPass.getText().toString().trim();
        return (!isEmptyFields(newEmail,newPassword,confPass) && hasSizeValid(newEmail,newPassword,confPass) && passwordConfirm(newPassword,confPass));
    }

    private boolean isEmptyFields(String userEmail, String userPassword,String confPass) {
        if (TextUtils.isEmpty(userEmail)) {
            edtEmail.requestFocus(); //seta o foco para o campo password
            inputEmail.setError(resources.getString(R.string.email_required));
            return true;
        } else if (TextUtils.isEmpty(userPassword)) {
            edtPassword.requestFocus(); //seta o foco para o campo password
            inputPass.setError(resources.getString(R.string.password_required));
            return true;
        } else if (TextUtils.isEmpty(confPass)) {
            edtConfPass.requestFocus(); //seta o foco para o campo password
            inputConfPass.setError(resources.getString(R.string.password_required));
        }
        return false;
    }


    private boolean hasSizeValid(String userEmail,String userPassword,String confPass) {

        if (!(userEmail.length() > 5)) {
            edtEmail.requestFocus();
            inputEmail.setError(resources.getString(R.string.email_size_invalid));
            return false;
        } else if (!(userPassword.length() > 5)) {
            edtPassword.requestFocus();
            inputPass.setError(resources.getString(R.string.password_size_invalid));
            return false;
        }else if (!(confPass.length() > 5)) {
            edtPassword.requestFocus();
            inputConfPass.setError(resources.getString(R.string.password_size_invalid));
            return false;
        }
        return true;
    }

    private boolean passwordConfirm(String userPassword,String confPass) {

        if (!userPassword.equals(confPass)) {
            edtPassword.requestFocus();
            inputPass.setError(resources.getString(R.string.password_not_corresponding));
            return false;
        }
        return true;
    }

    private void clearErrorFields(TextInputLayout... textInputLayouts) {
        for (TextInputLayout inputLayout : textInputLayouts) {
            inputLayout.setError(null);
        }
    }

    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(inputEmail);
            clearErrorFields(inputPass);
            clearErrorFields(inputConfPass);
        }
    }

    public boolean verifyEmail() {
        if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()){
            inputEmail.setError("Please enter a Valid E-Mail Address!");
            return false;
        }else {
            inputEmail.setError("Email válido");
            return true;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
