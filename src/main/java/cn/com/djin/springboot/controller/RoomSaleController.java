package cn.com.djin.springboot.controller;

import cn.com.djin.springboot.entity.RoomSale;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * author:快乐风男
 * time:10:52
 */
@Controller
@RequestMapping("/roomSale")
public class RoomSaleController extends BaseController<RoomSale> {
    //根据房间编号分组统计房间的出租总金额
    @RequestMapping("/loadSalePriceByRoomNum")
    public @ResponseBody
    Map<String,Object> loadSalePriceByRoomNum(){
        try {
            return roomSaleService.findSalePriceByRoomNum();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
