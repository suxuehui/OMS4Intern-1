package com.arvato.oms.model;

public class WarehouseModel {
    private Integer id;

    private String warehousenum;

    private String warehousename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehousenum() {
        return warehousenum;
    }

    public void setWarehousenum(String warehousenum) {
        this.warehousenum = warehousenum == null ? null : warehousenum.trim();
    }

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename == null ? null : warehousename.trim();
    }
}