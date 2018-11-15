/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.FoodDTO;
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
public class FoodDAO extends DAO<FoodDTO> {
    protected String table_name = "tpl_Food";
    private static FoodDAO instance = null;

    public static FoodDAO getInstance() {
        if (instance == null) {
            instance = new FoodDAO();
        }
        return instance;
    }

    private FoodDAO() {
        super();
    }
    public ArrayList<FoodDTO> findByOneField(String where, Object value) throws SQLException{
        String sql = String.format("SELECT * FROM %s WHERE %s = ?", this.table_name, where);
        PreparedStatement ps = this.connection.prepareStatement(sql);
        if(value instanceof String) {
            ps.setString(1,(String) value );
        }
        if(value instanceof Integer) {
            ps.setInt(1,(int) value );
        }
        return this.excuteQuery(ps);
    }
  
    @Override
    protected ArrayList<FoodDTO> excuteQuery(PreparedStatement preparedStatement) {
        ArrayList<FoodDTO> foods = new ArrayList<FoodDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                FoodDTO temp = new FoodDTO(rs.getInt("id"),rs.getString("name"),rs.getInt("idCategory"),rs.getInt("price"),rs.getInt("createBy"));
                foods.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foods;
    }

    @Override
    protected boolean execute(PreparedStatement preparedStatement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
