package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.RoomSale;
import cn.com.djin.springboot.service.RoomSaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:快乐风男
 * time:11:22
 */
@Service
@Transactional(readOnly = false)
public class RoomSaleServiceImpl extends BaseServiceImpl<RoomSale> implements RoomSaleService {

    //根据房间编号分组统计房间的出租总金额
    @Override
    public Map<String, Object> findSalePriceByRoomNum() throws Exception {
        //最终返回resultMap结果集,存入echarts所需数据
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("legend", "消费金额");
        //新建xAxis横轴集合
        List<String> xAxis = new ArrayList<>();
        //新建series集合
        Map<String, Object> series = new HashMap<>();
        //往series中装图表的类型和名称
        series.put("name","消费金额");
        series.put("type","line");
        //定义图表的销售金额数据集合
        List<Double> data = new ArrayList<>();
        //查询房间出租的数据
        List<Map<String, Object>> mapList = roomSaleMapper.selectSalePriceByRoomNum();
        //遍历mapList存入房间号和对应的销售额
        for (Map oneMap : mapList) {
            //取得销售额,并装入series集合
            Double saleprices = (Double) oneMap.get("saleprices");
            data.add(saleprices);
            //取得房间号,并装入xAxis横轴
            String roomNum = (String) oneMap.get("room_num");
            xAxis.add(roomNum);
        }
        //将data集合装入series中
        series.put("data",data);
        //将series和xAxis装入resultMap
        resultMap.put("series",series);
        resultMap.put("xAxis",xAxis);
        return resultMap;
    }
}
