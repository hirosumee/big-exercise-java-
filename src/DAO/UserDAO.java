/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import Interfaces.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author a
 */
public class UserDAO extends DAO <UserDAO> {

    protected String table_name = "tpl_Account";
    private static UserDAO instance = null;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    private UserDAO() {
        super();
    }

    public boolean insert(UserDTO user) throws SQLException {

        String sql = String.format("INSERT INTO %s (username,password) VALUES (?,?)", this.table_name);

        PreparedStatement preparedStatement = this.connection
                .prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement.execute();
    }
    
    public ResultSet findOne(UserDTO user) throws SQLException {
        String sql = String.format("SELECT TOP 1 * FROM %s WHERE username = ? AND password = ?", this.table_name);

        PreparedStatement preparedStatement = DataProvider.getInstance()
                .getConnection()
                .prepareStatement(sql);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement.executeQuery();
    }

   

    @Override
    public ArrayList<UserDAO> excuteQuery(PreparedStatement preparedStatement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean execute(PreparedStatement preparedStatement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
