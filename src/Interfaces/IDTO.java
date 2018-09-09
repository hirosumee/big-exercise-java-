/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DAO.DataProvider;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author a
 */
public abstract class IDTO {
    protected String table_name = "tpl_user";
    protected Connection connection = null;
    
    public IDTO(){
        this.connection = DataProvider.getInstance().getConnection();
    }
    
    protected ResultSet insert(){
        return null;
    }
    
    
}
