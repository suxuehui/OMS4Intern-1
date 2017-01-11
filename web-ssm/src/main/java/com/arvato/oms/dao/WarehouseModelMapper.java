package com.arvato.oms.dao;

import com.arvato.oms.model.WarehouseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WarehouseModelMapper {

    int insert(WarehouseModel record);

    int insertSelective(WarehouseModel record);

    int updateByPrimaryKey(WarehouseModel record);


    //获取仓库的总数
    long Count();

    long Countwhnum(@Param(value = "warehousenum") String warehousenum);

    long Countwhname(@Param(value = "warehousename") String warehousename);

    //分页  传递pagesize pagenow参数
    List<WarehouseModel> selectAll(@Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //通过仓库编码获取列表
    List<WarehouseModel> selectAllByWhnum(@Param(value = "warehousenum") String warehousenum, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //通过仓库名字获取列表
    List<WarehouseModel> selectAllByWhname(@Param(value = "warehousename") String warehousename, @Param(value = "startPos") Integer startPos, @Param(value = "pageSize") Integer pageSize);

    //通过warehousenum获取该仓库详细数据
    WarehouseModel selectBywarehousenum(String warehousenum);

    //添加仓库
    int addWarehouse(@Param(value = "warehousenum") String warehousenum, @Param(value = "warehousename") String warehousename);

    //根据id得到一个仓库的所有数据
    WarehouseModel listupdateWarehouse(Integer id);

    //根据id和num验证数据库是否存在数据
    List<String> selectBywhIdandNum(@Param(value = "id") Integer id, @Param(value = "warehousenum") String warehousenum);

    //编辑仓库
    int updateWarehouse(@Param(value = "id") Integer id, @Param(value = "warehousenum") String warehousenum, @Param(value = "warehousename") String warehousename);

    //删除仓库
    int deleteByPrimaryKey(Integer id);

}