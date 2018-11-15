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
public class BillDTO {
//    id INT PRIMARY KEY IDENTITY(1,1),
//	DateCheckIn DATE NOT NULL DEFAULT GETDATE(),
//	DateCheckOut DATE,
//	idTable INT NOT NULL,
//	status INT NOT NULL DEFAULT N'Đã Thanh toán', -- Đã Thanh Toán / Chưa thanh toán 
//	createBy INT NOT NULL,
//	FOREIGN KEY (createBy) REFERENCES dbo.tpl_Account(id),
//	FOREIGN KEY (idTable) REFERENCES dbo.tpl_TableFood(id)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCheckIn() {
        return DateCheckIn;
    }

    public void setDateCheckIn(Date DateCheckIn) {
        this.DateCheckIn = DateCheckIn;
    }

    public Date getDateCheckOut() {
        return DateCheckOut;
    }

    public void setDateCheckOut(Date DateCheckOut) {
        this.DateCheckOut = DateCheckOut;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = status;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }
    private Date DateCheckIn;
    private Date DateCheckOut;
    private int idTable;
    private String status;
    private int createBy;

    public BillDTO(int id, Date DateCheckIn, Date DateCheckOut, int idTable, String Status, int createBy) {
        this.id = id;
        this.DateCheckIn = DateCheckIn;
        this.DateCheckOut = DateCheckOut;
        this.idTable = idTable;
        this.status = Status;
        this.createBy = createBy;
    }
}
