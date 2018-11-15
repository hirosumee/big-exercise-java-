/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdc.demo;

import Controllers.BillCtrl;
import Controllers.BillInfoCtrl;
import Controllers.FoodCategoryCtrl;
import Controllers.FoodCtrl;
import Controllers.TableFoodCtrl;
import DTO.BillDTO;
import DTO.BillInfoDTO;
import DTO.FoodCategoryDTO;
import DTO.FoodDTO;
import DTO.TableFoodDTO;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import jbdc.demo.Components.TablesContainer;

/**
 *
 * @author hirosume
 */
public class Home extends javax.swing.JPanel {

    /**
     * Creates new form Test
     */
    private Main parent = null;
    TablesContainer tc;
    TableFoodDTO currentTable;
    ArrayList<BillInfoDTO> currentBills;
    BillDTO currentBill;
    DefaultTableModel model;

    public Home(Main parent) {
        initComponents();
        this.parent = parent;
        initTablesContainer();
        initFoodCategory();
        blockRightPanel();
        model = (DefaultTableModel) this.jTable1.getModel();
    }

    private void blockRightPanel() {
        selectFood.setEnabled(false);
        selectFoodCategory.setEnabled(false);
        count.setEnabled(false);
        ThemMonBtn.setEnabled(false);
        ThanhToanBtn.setEnabled(false);
    }

    private void enableRightPanel() {
        selectFood.setEnabled(true);
        selectFoodCategory.setEnabled(true);
        count.setEnabled(true);
        ThemMonBtn.setEnabled(true);
        ThanhToanBtn.setEnabled(true);
    }

    private void initTablesContainer() {
        tc = new TablesContainer(this);
        tc.setSize(500, 700);
        tc.setBackground(Color.WHITE);
        jPanel1.add(tc);
    }

    private void intFood(FoodCategoryDTO e) {
        try {
            selectFood.removeAllItems();
            ArrayList<FoodDTO> foods = FoodCtrl.getInstance().findByCategory(e.getId());
            System.out.println(foods);
            for (FoodDTO i : foods) {
                selectFood.addItem(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCurrentTable(String e) {

        try {
            boolean flag = false;
            TableFoodDTO temp = null;
            ArrayList<TableFoodDTO> tableFoods = TableFoodCtrl.getInstance().getAll();
            for (TableFoodDTO f : tableFoods) {
                if (f.getName().equals(e)) {
                    flag = true;
                    temp = f;
                    break;
                }
            }
            if (!flag) {
                labelCurrentTable.setText("Không tìm thấy bàn này");
                currentTable = null;
                this.blockRightPanel();
                return;
            }
            labelCurrentTable.setText(temp.getName());
            currentTable = temp;
            currentBill = BillCtrl.getInstance().findOneOrInsert(this.currentTable.getId(), this.parent.user.getId());
            currentBills = BillInfoCtrl.getInstance().getAllFromBill(currentBill.getId());
            updateTable(currentBill.getId());
            enableRightPanel();
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initFoodCategory() {
        selectFoodCategory.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                //Do Something
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    FoodCategoryDTO temp = (FoodCategoryDTO) event.getItem();
                    System.out.println(temp);
                    intFood(temp);
                }
            }
        });
        try {
            ArrayList<FoodCategoryDTO> categories = FoodCategoryCtrl.getInstance().getAll();
            for (FoodCategoryDTO e : categories) {
                selectFoodCategory.addItem(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    FoodDTO findFood(int idFood) {
        try {
            ArrayList<FoodDTO> temp = FoodCtrl.getInstance().findById(idFood);
            if (temp.size() != 1) {
                return null;
            }
            return temp.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void updateTotal(int total) {
        this.totalPrice.setText(new Integer(total).toString());
    }

    private void updateTable(int idBill) {
        try {

            while (this.model.getRowCount() > 0) {
                this.model.removeRow(0);
            }

            ArrayList<BillInfoDTO> temp = BillInfoCtrl.getInstance().getAllFromBill(idBill);
            int totalPrice = 0;
            for (BillInfoDTO e : temp) {
                FoodDTO food = findFood(e.getIdFood());
                if (food == null || e.getCount() == 0) {
                    continue;
                }
                totalPrice += e.getCount() * food.getPrice();
                this.model.insertRow(this.model.getRowCount(), new Object[]{food.getName(), e.getCount(), food.getPrice(), e.getCount() * food.getPrice()});
            }
            updateTotal(totalPrice);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        totalPrice = new javax.swing.JTextField();
        ThanhToanBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        selectFoodCategory = new javax.swing.JComboBox<>();
        selectFood = new javax.swing.JComboBox<>();
        ThemMonBtn = new javax.swing.JButton();
        count = new javax.swing.JSpinner();
        labelCurrentTable = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(100, 100));
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 700));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Món", "Số lượng", "Đơn giá", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        totalPrice.setEditable(false);
        totalPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalPrice.setText("000000");

        ThanhToanBtn.setText("Thanh toán");
        ThanhToanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThanhToanBtnActionPerformed(evt);
            }
        });

        ThemMonBtn.setText("Thêm món ");
        ThemMonBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThemMonBtnActionPerformed(evt);
            }
        });

        count.setModel(new javax.swing.SpinnerNumberModel(1, 0, 100, 1));

        labelCurrentTable.setText("Bàn");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectFoodCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectFood, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCurrentTable)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ThemMonBtn)
                .addGap(48, 48, 48))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCurrentTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(selectFoodCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(selectFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(ThemMonBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ThanhToanBtn))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThanhToanBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ThemMonBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThemMonBtnActionPerformed
        // TODO add your handling code here:
        if (this.currentTable != null && this.currentBill != null && this.currentBills != null) {
            try {
                //check and set "Có Người"
                if (!currentTable.getStatus().equals("Có Người")) {
                    TableFoodCtrl.getInstance().updateCoNguoi(currentTable.getId());
                    tc.update();
                }

                FoodDTO f = (FoodDTO) this.selectFood.getSelectedItem();
                BillInfoDTO temp = new BillInfoDTO(this.currentBill.getId(), f.getId(), (int) this.count.getModel().getValue());
                // find this billinfo in bill 
                ArrayList<BillInfoDTO> thisFoodInBill = BillInfoCtrl.getInstance().findByBillIdAndFoodId(this.currentBill.getId(), f.getId());
                if (thisFoodInBill.isEmpty()) {
                    BillInfoCtrl.getInstance().insert(this.currentBill.getId(), f.getId(), (int) this.count.getModel().getValue());
                } else {
                    BillInfoCtrl.getInstance().updateCountByBillInfo(this.currentBill.getId(), f.getId(), (int) this.count.getModel().getValue());
                }
                //
                updateTable(this.currentBill.getId());
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_ThemMonBtnActionPerformed

    private void ThanhToanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThanhToanBtnActionPerformed
        try {
            // TODO add your handling code here:
            if (!currentBills.isEmpty()) {
                TableFoodCtrl.getInstance().updateKhongNguoi(currentTable.getId());
                //update bill
                BillCtrl.getInstance().updateDaThanhToan(currentBill.getId());
                tc.update();
                this.blockRightPanel();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ThanhToanBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ThanhToanBtn;
    private javax.swing.JButton ThemMonBtn;
    private javax.swing.JSpinner count;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCurrentTable;
    private javax.swing.JComboBox<FoodDTO> selectFood;
    private javax.swing.JComboBox<FoodCategoryDTO> selectFoodCategory;
    private javax.swing.JTextField totalPrice;
    // End of variables declaration//GEN-END:variables
}
