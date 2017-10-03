package br.com.collegenotebook.controller;

import android.content.Context;

import br.com.collegenotebook.model.User;

/**
 * Created by GRodrigues17 on 01/02/2017.
 */

public class LoginController {
    private Context context;
    private BaseController baseController;
    private User user;

    public LoginController(Context context){
        this.context = context;
    }

    public boolean validaLogin(String userEmail, String userPassword)throws Exception{

        baseController = new BaseController(context);
        baseController.open();
        // User user = baseController.findByLogin(userEmail, userPassword);


        String storedPassword=baseController.getSinlgeEntry(userEmail);

        // check if the Stored password matches with  Password entered by user
        if(userPassword.equals(storedPassword))
        {
            return true;
        }
        else
        {
            return false;
            //Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
        }
    }

    public void saveUser(String newEmail, String newPassword) {
        baseController = new BaseController(context);

        baseController.open();
        user = new User();
        user.setUserEmail(newEmail);
        user.setUserName("");
        user.setUserPassword(newPassword);
        user.setUserPhoto("");

        baseController.insertUser(user);
        baseController.close();
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
