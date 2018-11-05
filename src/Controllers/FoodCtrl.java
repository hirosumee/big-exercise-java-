/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.FoodDAO;
import DTO.FoodDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ginichimaru
 */
public class FoodCtrl {
    private static FoodCtrl instance;
    public static FoodCtrl getInstance(){
        if(instance == null){
            instance = new FoodCtrl();
        }
        return instance;
    }
    private FoodCtrl(){
        
    }
    public ArrayList<FoodDTO> findByCategory(int id) throws SQLException{
        return FoodDAO.getInstance().findByOneField("idCategory", new Integer(id));
    }
}
