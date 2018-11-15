/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.BillDAO;
import DTO.BillDTO;
import java.sql.SQLException;

/**
 *
 * @author ginichimaru
 */
public class BillCtrl {
    private static BillCtrl instance;
    public static BillCtrl getInstance(){
        if(instance == null){
            instance = new BillCtrl();
        }
        return instance;
    }
    private BillCtrl(){
        
    }
    public BillDTO findByidTableAndStatus(int id) throws SQLException{
        return BillDAO.getInstance().findByTable(id);
    }
    public BillDTO findOneOrInsert(int idTable,int createBy) throws SQLException{
        BillDTO b =BillDAO.getInstance().findByTable(idTable);
        if(b == null){
            BillDAO.getInstance().insert(idTable, createBy);
            return BillDAO.getInstance().findByTable(idTable);
        }
        return b; 
    }
    public boolean updateDaThanhToan(int id) throws SQLException{
        return BillDAO.getInstance().updateStatus(id, BillDAO.DA_THANH_TOAN);
    }
}
