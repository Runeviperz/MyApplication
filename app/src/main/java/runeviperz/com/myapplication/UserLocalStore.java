package runeviperz.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    /**********************************************************************************************


                                            USER DATA


     *********************************************************************************************/


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


    /**********************************************************************************************


                                        SCORES


     *********************************************************************************************/

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

    /**********************************************************************************************


                                            ACHIEVEMENTS


     *********************************************************************************************/

    public void setWon10Games() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("won10Games", true);
        spEditor.commit();
    }

    public void setWon20Games() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("won20Games", true);
        spEditor.commit();
    }

    public void setWon50Games() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("won50Games", true);
        spEditor.commit();
    }

    public void setWon100Games() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("won100Games", true);
        spEditor.commit();
    }

    public void setWon250Games() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("won250Games", true);
        spEditor.commit();
    }

    public void setWonInExactly1() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInExactly1", true);
        spEditor.commit();
    }

    public void setWonInExactly69() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInExactly69", true);
        spEditor.commit();
    }

    public void setWonInExactly100() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInExactly100", true);
        spEditor.commit();
    }

    public void setWonInExactly420() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInExactly420", true);
        spEditor.commit();
    }

    public void setWonInOver500() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver500", true);
        spEditor.commit();
    }

    public void setWonInOver700() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver700", true);
        spEditor.commit();
    }

    public void setWonInOver800() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver800", true);
        spEditor.commit();
    }

    public void setWonInOver900() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver900", true);
        spEditor.commit();
    }

    public void setWonInOver1000() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver1000", true);
        spEditor.commit();
    }

    public void setWonInOver9000() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("wonInOver9000", true);
        spEditor.commit();
    }


    /*
    GET METHODS
     */

    public boolean getWon10Games() {
        return userLocalDatabase.getBoolean("won10Games", false);
    }

    public boolean getWon20Games() {
        return userLocalDatabase.getBoolean("won20Games", false);
    }

    public boolean getWon50Games() {
        return userLocalDatabase.getBoolean("won50Games", false);
    }

    public boolean getWon100Games() {
        return userLocalDatabase.getBoolean("won100Games", false);
    }

    public boolean getWon250Games() {
        return userLocalDatabase.getBoolean("won250Games", false);
    }

    public boolean getWonInExactly1() {
        return userLocalDatabase.getBoolean("wonInExactly1", false);
    }

    public boolean getWonInExactly69() {
        return userLocalDatabase.getBoolean("wonInExactly69", false);
    }

    public boolean getWonInExactly100() {
        return userLocalDatabase.getBoolean("wonInExactly100", false);
    }

    public boolean getWonInExactly420() {
        return userLocalDatabase.getBoolean("wonInExactly420", false);
    }

    public boolean getWonInOver500() {
        return userLocalDatabase.getBoolean("wonInOver500", false);
    }

    public boolean getWonInOver700() {
        return userLocalDatabase.getBoolean("wonInOver700", false);
    }

    public boolean getWonInOver800() {
        return userLocalDatabase.getBoolean("wonInOver800", false);
    }

    public boolean getWonInOver900() {
        return userLocalDatabase.getBoolean("wonInOver900", false);
    }

    public boolean getWonInOver1000() {
        return userLocalDatabase.getBoolean("wonInOver1000", false);
    }

    public boolean getWonInOver9000() {
        return userLocalDatabase.getBoolean("wonInOver9000", false);
    }


}