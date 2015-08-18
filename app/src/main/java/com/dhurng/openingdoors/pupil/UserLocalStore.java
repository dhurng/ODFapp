package com.dhurng.openingdoors.pupil;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by davidhurng on 8/14/15.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    //allows to store data on the form
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    //methods to get and set data

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);

        spEditor.putInt("age", user.age);


        //persist it
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        int age = userLocalDatabase.getInt("age", -1);

        User storedUser = new User(name, age, username, password);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if(userLocalDatabase.getBoolean("loggedIn", false)){
            return true;
        }
        else {
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
