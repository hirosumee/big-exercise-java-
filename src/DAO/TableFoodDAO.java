/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TableFoodDTO;
import Interfaces.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ginichimaru
 */
public class TableFoodDAO extends DAO<TableFoodDTO> {

    protected String table_name = "tpl_TableFood";
    private static TableFoodDAO instance = null;

    public static TableFoodDAO getInstance() {
        if (instance == null) {
            instance = new TableFoodDAO();
        }
        return instance;
    }

    private TableFoodDAO() {
        super();
    }

    @Override
    protected boolean insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TableFoodDTO findOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<TableFoodDTO> find(PreparedStatement preparedStatement) {
        ArrayList<TableFoodDTO> tableFoods = new ArrayList<TableFoodDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                TableFoodDTO temp = new TableFoodDTO(rs.getInt("id"),rs.getString("name"),rs.getString("status"));
                tableFoods.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableFoods;
    }
    public ArrayList<TableFoodDTO> getAll() throws SQLException{
            String sql = String.format("SELECT * FROM  %s", this.table_name) ;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            return this.find(preparedStatement);
    }

    @Override
    protected ArrayList<TableFoodDTO> update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
