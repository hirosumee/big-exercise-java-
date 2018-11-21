/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author ginichimaru
 */
public class BillInfoPanelCustom {
    String name;
    int count;
    int price;

    public BillInfoPanelCustom(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }
    public Object[] getObjectArray(){
        return new Object[]{this.name,this.count,this.price,this.count * this.price};
    }
    
}
