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
public class FoodDTO {
    private int id;
    private String name;
    private int idCategory;
    private int price;
    private int createBy;

    public FoodDTO(int id, String name, int idCategory, int price, int createBy) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
        this.createBy = createBy;
    }

    

    public FoodDTO(String name, int idCategory, int price, int createBy) {
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
        this.createBy = createBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public FoodDTO() {
    }

    @Override
    public String toString() {
        return name;
    }

}
