/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UserDAO;
import DTO.UserDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hirosume
 */
public class UserCtrl {

    private static UserCtrl instance = null;

    public static UserCtrl getInstance() {
        if (instance == null) {
            instance = new UserCtrl();
        }
        return instance;
    }

    private UserCtrl() {

    }

    public boolean signup(String username, String password) throws SQLException {
        return UserDAO.getInstance().insert(new UserDTO(username, password));

    }
    public UserDTO login(String username, String password){
        try {
            ResultSet resultSet = UserDAO.getInstance().findOne(new UserDTO(username,password));
            while(resultSet.next()){
                return new UserDTO(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("password"));
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UserCtrl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
