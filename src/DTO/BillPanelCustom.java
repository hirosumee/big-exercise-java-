/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author ginichimaru
 */
public class BillPanelCustom {
    int id;
    String status;
    Date DateCheckIn;
    Date DateCheckOut;
    String name;
    int price;

    public BillPanelCustom(int id, String status, Date DateCheckIn, Date DateCheckOut, String name, int price) {
        this.id = id;
        this.status = status;
        this.DateCheckIn = DateCheckIn;
        this.DateCheckOut = DateCheckOut;
        this.name = name;
        this.price = price;
    }
    public Object[] getObjectArray(){
        return new Object[]{this.id,this.name,this.status,this.DateCheckIn,this.DateCheckOut,this.price};
    }
}
