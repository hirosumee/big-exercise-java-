CREATE DATABASE JDBC
GO
USE JDBC
GO
-- Food
-- Table
-- FoodCategory
-- Account
-- Bill
-- BillInfo
CREATE TABLE tpl_Account (
	id INT PRIMARY KEY IDENTITY(1,1),
	username NVARCHAR(30) NOT NULL,
	password NVARCHAR(255) NOT NULL,
	role NVARCHAR(40) NOT NULL DEFAULT N'Nhân Viên Bán Hàng'
)
GO
CREATE TABLE tpl_FoodCategory (
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(20) NOT NULL DEFAULT N'Chưa đặt tên'
)
GO
CREATE TABLE tpl_Food (
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(255) NOT NULL DEFAULT N'Chưa đặt tên',
	idCategory INT NOT NULL,
	price INT NOT NULL DEFAULT 0,
	createBy INT NOT NULL,
	FOREIGN KEY (createBy) REFERENCES dbo.tpl_Account(id),
	FOREIGN KEY (idCategory) REFERENCES dbo.tpl_FoodCategory(id)
)
GO
CREATE TABLE tpl_TableFood(
	id INT PRIMARY KEY IDENTITY(1,1),
	name NVARCHAR(20) NOT NULL DEFAULT N'Chưa đặt tên',
	status NVARCHAR(20) NOT NULL DEFAULT N'Có Người' -- Có Người / Không có người
)
GO

CREATE TABLE tpl_Bill(
	id INT PRIMARY KEY IDENTITY(1,1),
	DateCheckIn DATE NOT NULL DEFAULT GETDATE(),
	DateCheckOut DATE,
	idTable INT NOT NULL,
	status NVARCHAR(30) NOT NULL DEFAULT N'Chưa Thanh toán', -- Đã Thanh Toán / Chưa thanh toán 
	createBy INT NOT NULL,
	FOREIGN KEY (createBy) REFERENCES dbo.tpl_Account(id),
	FOREIGN KEY (idTable) REFERENCES dbo.tpl_TableFood(id)

)
GO
CREATE TABLE tpl_BillInfo (
	id INT PRIMARY KEY IDENTITY(1,1),
	idBill INT NOT NULL,
	idFood INT NOT NULL,
	count INT NOT NULL DEFAULT 0

	FOREIGN KEY(idBill) REFERENCES dbo.tpl_Bill(id),
	FOREIGN KEY(idFood) REFERENCES dbo.tpl_Food(id)
)
GO


INSERT INTO dbo.tpl_Account (username,password,role) VALUES ('admin','a',N'Nhân viên bán hàng')
select * from tpl_Account

DECLARE @i INT = 21

WHILE @i <= 40
BEGIN
	INSERT INTO dbo.tpl_TableFood (name ) VALUES ( N'Bàn' + CAST(@i AS nvarchar(100)))
	SET @i = @i + 1
END
GO

UPDATE DBO.tpl_TableFood SET status = N'Không Có Người'
WHERE id % 2 = 0
SELECT * FROM DBO.tpl_TableFood


--DELETE FROM dbo.tpl_TableFood WHERE id > 20
--GO


INSERT dbo.tpl_FoodCategory (name) VALUES (N'Hải Sản')
INSERT dbo.tpl_FoodCategory (name) VALUES (N'Nông Sản')
INSERT dbo.tpl_FoodCategory (name) VALUES (N'Lâm Sản')
INSERT dbo.tpl_FoodCategory (name) VALUES (N'Hoa quả')
INSERT dbo.tpl_FoodCategory (name) VALUES (N'Đồ uống')
SELECT * FROM dbo.tpl_FoodCategory
GO


INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Tôm hùm',1,150000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Cá ngừ om dưa',1,1000000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Lẩu hải sản',1,500000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Cá thu sốt cà chua',1,150000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Mực nướng',1,50000,1)


INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Ba ba',2,1000000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Cá chép',2,100000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Gà quay',2,200000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Vịt quay Vân đình ',2,150000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Tôm chiên xù',2,200000,1)
GO

INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Thịt dê',3,100000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Thịt trâu',3,100000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Măng rừng',3,50000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Thịt trâu gác bếp',3,150000,1)
GO

INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Táo',4,100000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Lê',4,100000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Bưởi ',4,50000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Dưa hấu',4,150000,1)
GO

INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Rượu',5,20000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Bia',5,10000,1)
INSERT dbo.tpl_Food(name,idCategory,price,createBy) VALUES (N'Cocacola ',5,5000,1)
GO

--DELETE FROM dbo.tpl_BillInfo
--DELETE FROM dbo.tpl_Bill
select * from dbo.tpl_Bill where status = N'Đã Thanh Toán'
select * from dbo.tpl_BillInfo
select * from dbo.tpl_Food
GO

CREATE PROCEDURE SELECT_ALL_BILL
AS
SELECT bill.id,bill.status, bill.DateCheckIn, bill.DateCheckOut,tabl.name, SUM(food.price * billInfo.count) as price FROM dbo.tpl_Bill as bill 
	inner join dbo.tpl_TableFood as tabl on bill.idTable = tabl.id
	inner join dbo.tpl_BillInfo as billInfo on bill.id = billInfo.idBill
	inner join dbo.tpl_Food as food on food.id = billInfo.idFood
	GROUP BY bill.id,bill.status, bill.DateCheckIn, bill.DateCheckOut,tabl.name
	ORDER BY bill.DateCheckIn
GO;


CREATE PROCEDURE SELECT_ALL_FOOD_OF_BILL
@idBill INT
AS
	SELECT * FROM dbo.tpl_BillInfo AS bill
	inner join dbo.tpl_Food as food on food.id = bill.idFood
	WHERE bill.idBill = @idBill
GO

EXEC SELECT_ALL_FOOD_OF_BILL @idBill = 7589





