/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.FoodCategoryDAO;
import DTO.FoodCategoryDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ginichimaru
 */
public class FoodCategoryCtrl {
    private static FoodCategoryCtrl instance;
    public static FoodCategoryCtrl getInstance(){
        if(instance == null){
            instance = new FoodCategoryCtrl();
        }
        return instance;
    }
    private FoodCategoryCtrl(){
        
    }
    public ArrayList<FoodCategoryDTO> getAll() throws SQLException{
        return FoodCategoryDAO.getInstance().getAll();
    }
}
