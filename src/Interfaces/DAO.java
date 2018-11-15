/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DAO.DataProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author a
 */
public abstract class DAO<T> {
    protected String table_name = "tpl_user";
    protected Connection connection = null;
    public DAO(){
        this.connection = DataProvider.getInstance().getConnection();
    }
    
    protected abstract boolean execute(PreparedStatement preparedStatement);
    protected abstract ArrayList<T> excuteQuery(PreparedStatement preparedStatement);
    
    
}
