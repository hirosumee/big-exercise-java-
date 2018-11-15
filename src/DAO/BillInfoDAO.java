/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BillInfoDTO;
import DTO.FoodDTO;
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
public class BillInfoDAO extends DAO<BillInfoDTO> {
    protected String table_name = "tpl_BillInfo";
    private static BillInfoDAO instance = null;

    public static BillInfoDAO getInstance() {
        if (instance == null) {
            instance = new BillInfoDAO();
        }
        return instance;
    }

    private BillInfoDAO() {
        super();
    }

   
    @Override
    protected ArrayList<BillInfoDTO> excuteQuery(PreparedStatement preparedStatement) {
        ArrayList<BillInfoDTO> temps = new ArrayList<BillInfoDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                BillInfoDTO temp = new BillInfoDTO(rs.getInt("id"),rs.getInt("idBill"),rs.getInt("idFood"),rs.getInt("count"));
                temps.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temps;
    }

    public ArrayList<BillInfoDTO> getAll() throws SQLException{
            String sql = String.format("SELECT * FROM  %s", this.table_name) ;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            return this.excuteQuery(preparedStatement);
    }
    public ArrayList<BillInfoDTO> getAllFromBill(int idBill) throws SQLException{
            String sql = String.format("SELECT * FROM  %s WHERE idBill = ?", this.table_name) ;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idBill);
            return this.excuteQuery(preparedStatement);
    }
    public boolean insert(int idBill,int idFood,int count) throws SQLException{
        String sql = String.format("INSERT INTO %s (idBill,idFood,count) VALUES (?,?,?)", this.table_name);

        PreparedStatement preparedStatement = this.connection
                .prepareStatement(sql);
        System.out.println(idBill);
        System.out.println(idFood);
        System.out.println(count);
        preparedStatement.setInt(1, idBill);
        preparedStatement.setInt(2, idFood);
        preparedStatement.setInt(3, count);
        return preparedStatement.execute();
    }
    public ArrayList<BillInfoDTO> findByBillIdAndFoodId(int idBill,int idFood) throws SQLException{
            String sql = String.format("SELECT * FROM  %s WHERE idFood = ? AND idBill = ?", this.table_name) ;
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, idFood);
            preparedStatement.setInt(2, idBill);
            return this.excuteQuery(preparedStatement);
    }
    public boolean updateCountByBillInfo(int idBill,int idFood,int increase) throws SQLException{
        String sql = String.format("UPDATE %s SET  count = ?   WHERE idBill = ? AND idFood = ?", this.table_name) ;
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        System.out.println(increase);
        preparedStatement.setInt(1, increase);
        preparedStatement.setInt(2, idBill);
        preparedStatement.setInt(3, idFood);
        return this.execute(preparedStatement);
    }

    @Override
    protected boolean execute(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BillInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
