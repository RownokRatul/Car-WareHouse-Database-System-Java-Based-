package DatabasePack;

import java.io.Serializable;

public class userLoginInfo implements Serializable {
    private String userName;
    private String password;

    public userLoginInfo(String user,String pass) {
        userName = user;
        password = pass;
    }

    public String getUserName() {
        return userName;
    }

    public boolean checkLoginInfo(Database database) {
        return database.executeCheckUserInfo(userName,password);
    }

}
