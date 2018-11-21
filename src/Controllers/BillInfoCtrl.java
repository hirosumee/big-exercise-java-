/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.BillDAO;
import DAO.BillInfoDAO;
import DTO.BillInfoDTO;
import DTO.BillInfoPanelCustom;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ginichimaru
 */
public class BillInfoCtrl {

    private static BillInfoCtrl instance;

    public static BillInfoCtrl getInstance() {
        if (instance == null) {
            instance = new BillInfoCtrl();
        }
        return instance;
    }

    private BillInfoCtrl() {

    }

    public ArrayList<BillInfoDTO> findAll() throws SQLException {
        return BillInfoDAO.getInstance().getAll();
    }

    public ArrayList<BillInfoDTO> getAllFromBill(int idBill) throws SQLException {
        return BillInfoDAO.getInstance().getAllFromBill(idBill);
    }

    public boolean insert(int idBill, int idFood, int count) throws SQLException {
        return BillInfoDAO.getInstance().insert(idBill, idFood, count);
    }

    public ArrayList<BillInfoDTO> findByBillIdAndFoodId(int billId,int foodId) throws SQLException {
        return BillInfoDAO.getInstance().findByBillIdAndFoodId(billId,foodId);
    }

    public boolean updateCountByBillInfo(int billInfoId,int idFood, int count) throws SQLException {
        return BillInfoDAO.getInstance().updateCountByBillInfo(billInfoId,idFood, count);
    }
    public ArrayList<BillInfoPanelCustom> getFoodOfBill(int idBill) {
        return BillInfoDAO.getInstance().getFoodOfBill(idBill);
    }
}
