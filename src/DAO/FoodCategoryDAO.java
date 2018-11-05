/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.FoodCategoryDTO;
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
public class FoodCategoryDAO extends DAO<FoodCategoryDTO> {

    protected String table_name = "tpl_FoodCategory";
    private static FoodCategoryDAO instance = null;

    public static FoodCategoryDAO getInstance() {
        if (instance == null) {
            instance = new FoodCategoryDAO();
        }
        return instance;
    }

    private FoodCategoryDAO() {
        super();
    }

    public ArrayList<FoodCategoryDTO> getAll() throws SQLException {
        String sql = String.format("SELECT * FROM  %s", this.table_name) ;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            return this.find(preparedStatement);
    }

    @Override
    protected boolean insert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected FoodCategoryDTO findOne() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<FoodCategoryDTO> find(PreparedStatement preparedStatement) {
        ArrayList<FoodCategoryDTO> foodCategories = new ArrayList<FoodCategoryDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                FoodCategoryDTO temp = new FoodCategoryDTO(rs.getInt("id"),rs.getString("name"));
                foodCategories.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodCategories;
    }

    @Override
    protected ArrayList<FoodCategoryDTO> update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
