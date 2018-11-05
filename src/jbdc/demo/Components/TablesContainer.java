/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbdc.demo.Components;

import Controllers.TableFoodCtrl;
import DTO.TableFoodDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import jbdc.demo.Home;

/**
 *
 * @author ginichimaru
 */
public class TablesContainer extends JPanel implements MouseListener {
    
    ArrayList<TableFoodDTO> foodTable;
    
    int rec = 90;
    int distance = 5;
    int tileOneLine = 5;
    Home parent;
    public TablesContainer(JPanel parent) {
        super();
        this.addMouseListener(this);
        this.setLayout(null);
        initTable();
        this.parent =(Home) parent;
    }
    protected void clearPanel(){
        this.removeAll();
        this.repaint();

    }
    private void initTable() {
        try {
            foodTable = TableFoodCtrl.getInstance().getAll();
        } catch (SQLException ex) {
            Logger.getLogger(TablesContainer.class.getName()).log(Level.SEVERE, null, ex);
        }
        int x = distance, y = distance;
        for (int i = 1; i <= foodTable.size(); i++) {
            TableFoodDTO temp = this.foodTable.get(i - 1);
            JButton btn = new JButton(temp.getName());
            //btn.setPreferredSize(new Dimension(recW, recH));
            btn.setBounds(x, y, rec, rec);
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    initTable();
                    parent.updateCurrentTable(((JButton)e.getSource()).getText());
                }
            });
            if (temp.getStatus().equals("Có Người")) {
                btn.setBackground(Color.RED);
            } else {
                btn.setBackground(Color.CYAN);
            }
            this.add(btn);
            
            x += (rec + distance);
            if (i % 5 == 0) {
                y += (rec + distance);
                x = distance;
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println(e.getX());
//        System.out.println(e.getY());
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
