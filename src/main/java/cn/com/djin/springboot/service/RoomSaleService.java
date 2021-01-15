package cn.com.djin.springboot.service;

import cn.com.djin.springboot.entity.RoomSale;

import java.util.Map;

/**
 * author:快乐风男
 * time:11:21
 */
public interface RoomSaleService extends BaseService<RoomSale>{

    //根据房间编号分组统计房间的出租总金额
    Map<String,Object> findSalePriceByRoomNum() throws Exception;
}
