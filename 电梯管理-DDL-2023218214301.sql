DROP TABLE IF EXISTS em_elevator;
CREATE TABLE em_elevator(
    id VARCHAR(32) NOT NULL   COMMENT '电梯id' ,
    elevator_number VARCHAR(32) NOT NULL   COMMENT '电梯编号' ,
    elevator_name VARCHAR(90) NOT NULL   COMMENT '电梯名称' ,
    location_name VARCHAR(90) NOT NULL   COMMENT '场所名称' ,
    manufacturer_name VARCHAR(90) NOT NULL   COMMENT '生产厂家' ,
    model VARCHAR(32) NOT NULL   COMMENT '设备型号' ,
    load_speed INT NOT NULL   COMMENT '载重速度' ,
    product_number VARCHAR(32) NOT NULL   COMMENT '产品编号' ,
    address VARCHAR(90) NOT NULL   COMMENT '详细地址' ,
    manufacturer_id VARCHAR(32) NOT NULL   COMMENT '电梯厂家ID' ,
    location_id VARCHAR(32) NOT NULL   COMMENT '场所ID' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '电梯';

DROP TABLE IF EXISTS em_manufacturer;
CREATE TABLE em_manufacturer(
    id VARCHAR(32) NOT NULL   COMMENT '厂家ID' ,
    manufacturer_name VARCHAR(90) NOT NULL   COMMENT '厂家名称' ,
    contact_person VARCHAR(90) NOT NULL   COMMENT '联系人' ,
    load_speed INT NOT NULL   COMMENT '联系电话' ,
    telephone INT   DEFAULT NULL COMMENT '手机' ,
    mobile_phone VARCHAR(90) NOT NULL   COMMENT '编号前缀' ,
    fax INT   DEFAULT NULL COMMENT '传真' ,
    address VARCHAR(90) NOT NULL   COMMENT '公司地址' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '电梯厂家';

DROP TABLE IF EXISTS em_location;
CREATE TABLE em_location(
    id VARCHAR(32) NOT NULL   COMMENT '场所ID' ,
    location_name VARCHAR(90) NOT NULL   COMMENT '场所名称' ,
    contact_person VARCHAR(90) NOT NULL   COMMENT '联系人' ,
    load_speed INT NOT NULL   COMMENT '联系电话' ,
    telephone INT   DEFAULT NULL COMMENT '手机' ,
    fax INT   DEFAULT NULL COMMENT '传真' ,
    address VARCHAR(90) NOT NULL   COMMENT '场所地址' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL   COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '场所';

DROP TABLE IF EXISTS em_elevator_image;
CREATE TABLE em_elevator_image(
    id VARCHAR(32) NOT NULL   COMMENT '电梯图片ID' ,
    elevator_id VARCHAR(32) NOT NULL   COMMENT '电梯ID' ,
    image_name VARCHAR(255) NOT NULL   COMMENT '图片名' ,
    image_position VARCHAR(900)    COMMENT '图片位置' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '电梯图片';

DROP TABLE IF EXISTS em_accessory;
CREATE TABLE em_accessory(
    id VARCHAR(32) NOT NULL   COMMENT '配件ID' ,
    accessory_number VARCHAR(32) NOT NULL   COMMENT '配件编号' ,
    accessory_person VARCHAR(90) NOT NULL   COMMENT '配件名称' ,
    specification VARCHAR(90) NOT NULL   COMMENT '规格型号' ,
    type VARCHAR(90) NOT NULL   COMMENT '配件类型' ,
    unit VARCHAR(90) NOT NULL   COMMENT '单位' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '配件';

DROP TABLE IF EXISTS em_supplier;
CREATE TABLE em_supplier(
    id VARCHAR(32) NOT NULL   COMMENT '供货商ID' ,
    supplier_name VARCHAR(90) NOT NULL   COMMENT '供货商名称' ,
    contact_person VARCHAR(90) NOT NULL   COMMENT '联系人' ,
    load_speed INT NOT NULL   COMMENT '联系电话' ,
    telephone INT   DEFAULT NULL COMMENT '手机' ,
    fax INT   DEFAULT NULL COMMENT '传真' ,
    address VARCHAR(90) NOT NULL   COMMENT '场所地址' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '供货商';

DROP TABLE IF EXISTS em_storage;
CREATE TABLE em_storage(
    id VARCHAR(32) NOT NULL   COMMENT '配件入库ID' ,
    storage_number VARCHAR(32) NOT NULL   COMMENT '入库编号' ,
    storage_time DATETIME NOT NULL   COMMENT '入库日期' ,
    operator_person VARCHAR(90) NOT NULL   COMMENT '经办人' ,
    supplier_name VARCHAR(90) NOT NULL   COMMENT '供货商名称' ,
    total_price DECIMAL(24,6) NOT NULL   COMMENT '总金额' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '配件入库';

DROP TABLE IF EXISTS em_storage_item;
CREATE TABLE em_storage_item(
    id VARCHAR(32) NOT NULL   COMMENT '入库详情ID' ,
    storage_id VARCHAR(32) NOT NULL   COMMENT '配件入库ID' ,
    accessory_name VARCHAR(90) NOT NULL   COMMENT '配件名称' ,
    specification VARCHAR(90) NOT NULL   COMMENT '规格型号' ,
    type VARCHAR(90) NOT NULL   COMMENT '配件类型' ,
    unit VARCHAR(90) NOT NULL   COMMENT '单位' ,
    quantity INT NOT NULL   COMMENT '数量' ,
    purchase_price DECIMAL(24,6) NOT NULL   COMMENT '进货价' ,
    total_price DECIMAL(24,6) NOT NULL   COMMENT '金额' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '入库详情';

DROP TABLE IF EXISTS em_inventory;
CREATE TABLE em_inventory(
    id VARCHAR(32) NOT NULL   COMMENT '库存管理ID' ,
    accessory_id VARCHAR(32) NOT NULL   COMMENT '配件ID' ,
    quantity INT NOT NULL   COMMENT '库存数量' ,
    warning_quantity INT NOT NULL   COMMENT '预警数量' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '库存管理';

DROP TABLE IF EXISTS em_maintenance;
CREATE TABLE em_maintenance(
    id VARCHAR(32) NOT NULL   COMMENT '维修报告ID' ,
    maintenance_number VARCHAR(32) NOT NULL   COMMENT '维修编号' ,
    elevator_number VARCHAR(32) NOT NULL   COMMENT '电梯编号' ,
    location_name VARCHAR(90) NOT NULL   COMMENT '电梯地点' ,
    contact_person VARCHAR(90) NOT NULL   COMMENT '联系人' ,
    load_speed INT NOT NULL   COMMENT '联系电话' ,
    maintenance_price DECIMAL(24,6)   DEFAULT NULL COMMENT '维修工费' ,
    accessory_price DECIMAL(24,6)   DEFAULT NULL COMMENT '配件费用' ,
    total_price DECIMAL(24,6)   DEFAULT NULL COMMENT '总费用' ,
    fault_description VARCHAR(900)   DEFAULT NULL COMMENT '故障描述' ,
    maintenance_data DATETIME   DEFAULT NULL COMMENT '维修日期' ,
    maintenance_person VARCHAR(90)   DEFAULT NULL COMMENT '维修人' ,
    is_finish VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否完成' ,
    elevator_id VARCHAR(32) NOT NULL   COMMENT '电梯ID' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '维修报告';

DROP TABLE IF EXISTS em_maintenance_item;
CREATE TABLE em_maintenance_item(
    id VARCHAR(32) NOT NULL   COMMENT '维修报告详情ID' ,
    maintenance_id VARCHAR(32) NOT NULL   COMMENT '维修报告ID' ,
    accessory_name VARCHAR(90) NOT NULL   COMMENT '配件名称' ,
    specification VARCHAR(90) NOT NULL   COMMENT '规格型号' ,
    type VARCHAR(90) NOT NULL   COMMENT '配件类型' ,
    unit VARCHAR(90) NOT NULL   COMMENT '单位' ,
    quantity INT NOT NULL   COMMENT '数量' ,
    purchase_price DECIMAL(24,6) NOT NULL   COMMENT '进货价' ,
    total_price DECIMAL(24,6) NOT NULL   COMMENT '金额' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '维修报告详情';

DROP TABLE IF EXISTS em_inspection;
CREATE TABLE em_inspection(
    id VARCHAR(32) NOT NULL   COMMENT '检查报告ID' ,
    maintenance_number VARCHAR(32) NOT NULL   COMMENT '检查编号' ,
    elevator_number VARCHAR(32) NOT NULL   COMMENT '电梯编号' ,
    location_name VARCHAR(90) NOT NULL   COMMENT '电梯地点' ,
    contact_person VARCHAR(90) NOT NULL   COMMENT '联系人' ,
    load_speed INT NOT NULL   COMMENT '联系电话' ,
    fault_description VARCHAR(900)   DEFAULT NULL COMMENT '检查描述' ,
    inspection_data DATETIME   DEFAULT NULL COMMENT '检查日期' ,
    inspection_person VARCHAR(90)   DEFAULT NULL COMMENT '检查人' ,
    is_finish VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否完成' ,
    is_fault VARCHAR(1)   DEFAULT NULL COMMENT '是否故障' ,
    elevator_id VARCHAR(32) NOT NULL   COMMENT '电梯ID' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '检查报告';

DROP TABLE IF EXISTS em_maintenance_image;
CREATE TABLE em_maintenance_image(
    id VARCHAR(32) NOT NULL   COMMENT '维修报告图片ID' ,
    maintenance_id VARCHAR(32) NOT NULL   COMMENT '维修报告ID' ,
    image_name VARCHAR(255) NOT NULL   COMMENT '图片名' ,
    image_position VARCHAR(900)    COMMENT '图片位置' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '维修报告图片';

DROP TABLE IF EXISTS em_inspection_image;
CREATE TABLE em_inspection_image(
    id VARCHAR(32) NOT NULL   COMMENT '检查报告图片ID' ,
    inspection_id VARCHAR(32) NOT NULL   COMMENT '检查报告ID' ,
    maintenance_id VARCHAR(32)    COMMENT '维修报告ID' ,
    image_name VARCHAR(255) NOT NULL   COMMENT '图片名' ,
    image_position VARCHAR(900)    COMMENT '图片位置' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '检查报告图片';

DROP TABLE IF EXISTS em_plan;
CREATE TABLE em_plan(
    id VARCHAR(255) NOT NULL   COMMENT '检查计划ID' ,
    plan_number VARCHAR(255) NOT NULL   COMMENT '计划编号' ,
    start_time DATETIME NOT NULL   COMMENT '开始日期' ,
    end_time DATETIME NOT NULL   COMMENT '结束日期' ,
    next_time DATETIME NOT NULL   COMMENT '下一次检查日期' ,
    interval_time INT NOT NULL   COMMENT '间隔时间' ,
    Interval_unit INT NOT NULL   COMMENT '间隔时间单位' ,
    is_finish VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否完成' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '检查计划';

DROP TABLE IF EXISTS em_user;
CREATE TABLE em_user(
    id VARCHAR(255) NOT NULL   COMMENT '用户ID' ,
    user_number VARCHAR(32) NOT NULL   COMMENT '用户编号' ,
    user_name VARCHAR(90) NOT NULL   COMMENT '用户名称' ,
    password VARCHAR(255) NOT NULL   COMMENT '用户密码' ,
    gender VARCHAR(255)   DEFAULT NULL COMMENT '性别' ,
    age VARCHAR(255)   DEFAULT NULL COMMENT '年龄' ,
    remarks VARCHAR(900)   DEFAULT NULL COMMENT '备注' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '用户';

DROP TABLE IF EXISTS em_permission;
CREATE TABLE em_permission(
    id VARCHAR(255) NOT NULL   COMMENT '权限ID' ,
    permission_name VARCHAR(90) NOT NULL   COMMENT '权限名字' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (id)
)  COMMENT = '权限';

DROP TABLE IF EXISTS t_user_permission;
CREATE TABLE t_user_permission(
    user_permission_id VARCHAR(255) NOT NULL   COMMENT '用户权限ID' ,
    user_id VARCHAR(32) NOT NULL   COMMENT '用户ID' ,
    permission_id VARCHAR(32) NOT NULL   COMMENT '权限ID' ,
    create_time DATETIME NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    update_time DATETIME   DEFAULT NULL COMMENT '更新时间' ,
    is_deleted VARCHAR(1) NOT NULL  DEFAULT 0 COMMENT '是否删除' ,
    PRIMARY KEY (user_permission_id)
)  COMMENT = '用户权限';

