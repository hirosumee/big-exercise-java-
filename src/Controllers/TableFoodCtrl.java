/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.TableFoodDAO;
import DTO.TableFoodDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ginichimaru
 */
public class TableFoodCtrl {
    private static TableFoodCtrl instance;
    public static TableFoodCtrl getInstance(){
        if(instance == null){
            instance = new TableFoodCtrl();
        }
        return instance;
    }
    private TableFoodCtrl(){
        
    }
    public ArrayList<TableFoodDTO> getAll() throws SQLException{
        return TableFoodDAO.getInstance().getAll();
    }
}
