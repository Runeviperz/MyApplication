package runeviperz.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 16/07/2015.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putInt("age", user.age);
        spEditor.commit();
    }

//    public User getLoggedInUser() {
//        String name = userLocalDatabase.getString("name", "");
//        String username = userLocalDatabase.getString("username", "");
//        String password = userLocalDatabase.getString("password", "");
//        int age = userLocalDatabase.getInt("age", -1);
//
//        User storedUser = new User(name, username, password, age);
//
//        return storedUser;
//    }

    public User getRegisteredUser() {
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        return new User(username, password);
    }

    public String getUsername() {
        return userLocalDatabase.getString("username", "");
    }

    public String getName() {
        return userLocalDatabase.getString("name","");
    }

    public void logOut() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", "");
        spEditor.putString("username", "");
        spEditor.putString("password", "");
        spEditor.putString("age", "");
        spEditor.commit();
    }
//    public void setUserLoggedIn(boolean loggedIn) {
//        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
//        spEditor.putBoolean("loggedIn", loggedIn);
//        spEditor.commit();
//    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

    public void setHighScore(int score) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("highscore", score);
        spEditor.commit();
    }

    public int getHighScore() {
        return userLocalDatabase.getInt("highscore", 0);
    }

    public void setTotalRolls(int rolls) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("totalRolls", rolls);
        spEditor.commit();
    }

    public int getTotalRolls() {
        return userLocalDatabase.getInt("totalRolls", 0);
    }

    public int getLeastRolls() {
        return userLocalDatabase.getInt("leastRolls", 0);
    }

    public void setLeastRolls(int rolls) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("leastRolls", rolls);
        spEditor.commit();
    }

    public void setMostRolls(int rolls) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("mostRolls", rolls);
        spEditor.commit();
    }

    public int getMostRolls() {
        return userLocalDatabase.getInt("mostRolls", 0);
    }

}

