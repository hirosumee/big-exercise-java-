/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TableFoodDTO;
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
public class TableFoodDAO extends DAO<TableFoodDTO> {

    protected String table_name = "tpl_TableFood";
    private static TableFoodDAO instance = null;

    public static TableFoodDAO getInstance() {
        if (instance == null) {
            instance = new TableFoodDAO();
        }
        return instance;
    }

    private TableFoodDAO() {
        super();
    }

    @Override
    protected ArrayList<TableFoodDTO> excuteQuery(PreparedStatement preparedStatement) {
        ArrayList<TableFoodDTO> tableFoods = new ArrayList<TableFoodDTO>();
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TableFoodDTO temp = new TableFoodDTO(rs.getInt("id"), rs.getString("name"), rs.getString("status"));
                tableFoods.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableFoods;
    }

    public ArrayList<TableFoodDTO> getAll() throws SQLException {
        String sql = String.format("SELECT * FROM  %s", this.table_name);
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        return this.excuteQuery(preparedStatement);
    }

    @Override
    protected boolean execute(PreparedStatement preparedStatement) {
        try {
            return preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TableFoodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateStatus(int idTable,String status) throws SQLException {
        String sql = String.format("UPDATE %s SET status = ? WHERE id = ?", this.table_name);
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, idTable);
        return this.execute(preparedStatement);
    }
    public static String CO_NGUOI = "Có Người";
    public static String KHONG_CO_NGUOI = "Không Có Người";
}
