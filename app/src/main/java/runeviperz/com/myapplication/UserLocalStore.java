package runeviperz.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

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

//    public User getRegisteredUser() {
//        String username = userLocalDatabase.getString("username", "");
//        String password = userLocalDatabase.getString("password", "");
//        return new User(username, password);
//    }

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

    public void setTempTotalRolls(int rolls) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("tempTotalRolls", rolls);
        spEditor.commit();
    }

    public int getTempTotalRolls() {
        return userLocalDatabase.getInt("tempTotalRolls", 0);
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

    public void updateTotalGames() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("totalGames", getTotalGames()+1);
        spEditor.commit();
    }

    public int getTotalGames() {
        return userLocalDatabase.getInt("totalGames", 0);
    }

    public void updateGameRolls(int rolls) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("totalGameRolls", getTotalGameRolls()+rolls);
        spEditor.commit();
    }

    public int getTotalGameRolls() {
        return userLocalDatabase.getInt("totalGameRolls", 0);
    }

    public void resetAllScores() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("highscore", 0);
        spEditor.putInt("totalRolls", 0);
        spEditor.putInt("leastRolls", 0);
        spEditor.putInt("tempTotalRolls", 0);
        spEditor.putInt("mostRolls", 0);
        spEditor.putInt("totalGames", 0);
        spEditor.putInt("totalGameRolls", 0);
        spEditor.commit();
    }

}

