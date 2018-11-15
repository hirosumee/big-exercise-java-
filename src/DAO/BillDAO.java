/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BillDTO;
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
public class BillDAO extends DAO<BillDTO> {

    protected String table_name = "tpl_Bill";
    private static BillDAO instance = null;

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }

    private BillDAO() {
        super();
    }

   
    public boolean insert(int idTable,int createBy) throws SQLException {
        String sql = String.format("INSERT INTO %s (idTable,createBy) VALUES (?,?)", this.table_name);

        PreparedStatement preparedStatement = this.connection
                .prepareStatement(sql);
        preparedStatement.setInt(1, idTable);
        preparedStatement.setInt(2, createBy);
        return preparedStatement.execute();
    }


    @Override
    protected ArrayList<BillDTO> excuteQuery(PreparedStatement preparedStatement) {
        ArrayList<BillDTO> temps = new ArrayList<BillDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BillDTO temp = new BillDTO(rs.getInt("id"), rs.getDate("DateCheckIn"), rs.getDate("DateCheckOut"), rs.getInt("idTable"), rs.getString("status"), rs.getInt("createBy"));
                temps.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temps;
    }


    public BillDTO findByTable(int idTable) throws SQLException {
        String sql = String.format("SELECT * FROM %s WHERE idTable = ? AND status = N'Chưa thanh toán'", this.table_name);
        
        PreparedStatement ps = this.connection.prepareStatement(sql);
        ps.setInt(1, idTable);
        ArrayList<BillDTO> temps = this.excuteQuery(ps);
        if(temps.size() != 1){
            return null;
        }
        return temps.get(0);
    }

    public boolean updateStatus(int idTable,String status) throws SQLException {
        String sql = String.format("UPDATE %s SET status = ?, DateCheckout = GETDATE() WHERE id = ?", this.table_name);
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, idTable);
        return this.execute(preparedStatement);
    }
    public static String DA_THANH_TOAN = "Đã Thanh Toán";
    public static String CHUA_THANH_TOAN = "Chưa Thanh toán";

    @Override
    protected boolean execute(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
