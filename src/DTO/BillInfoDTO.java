/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Controllers.FoodCtrl;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ginichimaru
 */
public class BillInfoDTO {
    private int id;

    public BillInfoDTO() {
    }

    public BillInfoDTO(int idBill, int idFood, int count) {
        this.idBill = idBill;
        this.idFood = idFood;
        this.count = count;
    }

    public BillInfoDTO(int id, int idBill, int idFood, int count) {
        this.id = id;
        this.idBill = idBill;
        this.idFood = idFood;
        this.count = count;
    }
    private int idBill;
    private int idFood;
    private int count;
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdFood() {
        return idFood;
    }
    public FoodDTO getFood() throws SQLException{
        ArrayList<FoodDTO> temp = FoodCtrl.getInstance().findById(id);
        if(temp.size() != 0) {
            return temp.get(0);
        }
        return null;
    }

    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
