package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.InRoomInfo;
import cn.com.djin.springboot.entity.Orders;

import cn.com.djin.springboot.entity.RoomSale;
import cn.com.djin.springboot.entity.Rooms;
import cn.com.djin.springboot.mapper.InRoomInfoMapper;
import cn.com.djin.springboot.mapper.RoomSaleMapper;
import cn.com.djin.springboot.service.OrdersService;
import cn.com.djin.springboot.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:快乐风男
 * time:9:43
 */
@Service
@Transactional(readOnly = false)//开启读写事务,若某一行代码报错了，则此方法里所有的操作都会被回滚
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Override
    public String save(Orders orders) throws Exception {
        //1.完成订单添加
        Integer insert = baseMapper.insert(orders);
        //2.完成入住信息的修改
        InRoomInfo inRoomInfo = new InRoomInfo();
        //获取入住信息主键
        Integer iriId = orders.getIriId();
        inRoomInfo.setId(iriId);
        inRoomInfo.setOutRoomStatus("1");//1表示已退房
        Integer integer = inRoomInfoMapper.updateByPrimaryKeySelective(inRoomInfo);
        //3.完成房间状态的修改
        //3.1.根据入住信息id查询出房间id
        InRoomInfo inRoomInfo1 = inRoomInfoMapper.selectByPrimaryKey(iriId);
        Integer roomId = inRoomInfo1.getRoomId();
        //3.3.执行房间状态的修改
        Rooms rooms = new Rooms();
        rooms.setId(roomId);
        rooms.setRoomStatus("2");
        Integer integer1 = roomsMapper.updateByPrimaryKeySelective(rooms);
        if (insert > 0 && integer > 0 && integer1 > 0) {
            return "success";
        } else {
            return "fail";
        }
    }

    //支付成功之后的操作（事物.1.修改订单状态，2.生成消费记录）
    @Override
    public String afterOrdersPay(String out_trade_no) throws Exception {
        //1.开始修改订单状态
        Orders praOrders = new Orders();
        praOrders.setOrderNum(out_trade_no);
        //2.根据订单编号查询订单数据
        Orders orders = baseMapper.selectByPramas(praOrders);
        //3.修改订单状态为已支付
        orders.setOrderStatus("1");
        Integer integer = baseMapper.updateByPrimaryKeySelective(orders);

        //4.完成消费记录的生成
        //分隔获取订单中其他字段数组,填充进roomSale表
        String[] orderOther = orders.getOrderOther().split(",");
        //分隔获取订单中其他金额数组
        String[] orderPrice = orders.getOrderPrice().split(",");
        //消费记录对象信息
        RoomSale roomSale = new RoomSale();
        roomSale.setRoomNum(orderOther[0]);
        roomSale.setCustomerName(orderOther[1]);
        roomSale.setStartDate(DateUtils.stringToDate(orderOther[2]));
        roomSale.setEndDate(DateUtils.stringToDate(orderOther[3]));
        roomSale.setDays(Integer.valueOf(orderOther[4]));
        roomSale.setRoomPrice(Double.valueOf(orderPrice[0]));
        roomSale.setOtherPrice(Double.valueOf(orderPrice[1]));
        roomSale.setRentPrice(Double.valueOf(orderPrice[2]));
        roomSale.setSalePrice(orders.getOrderMoney());
        //优惠金额:单价*天数-租金
        Double discountPrice = Double.valueOf(orderPrice[0]) * Integer.valueOf(orderOther[4]) - Double.valueOf(orderPrice[2]);
        roomSale.setDiscountPrice(discountPrice);
        //完成添加消费记录
        Integer insert = roomSaleMapper.insert(roomSale);

        if (integer > 0 && insert > 0) {
            return "redirect:/authority/toIndex";
        }else {
            return "payError";
        }
    }

}
