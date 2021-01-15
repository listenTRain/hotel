package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.InRoomInfo;
import cn.com.djin.springboot.entity.Rooms;
import cn.com.djin.springboot.service.InRoomInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入住信息的业务层实现类
 */
@Service
@Transactional(readOnly = false)
public class InRoomInfoServiceImpl extends BaseServiceImpl<InRoomInfo> implements InRoomInfoService {

    @Override
    public String save(InRoomInfo inRoomInfo) throws Exception {

        //1.完成入住信息添加
        Integer insert = baseMapper.insert(inRoomInfo);

        //2.完成房间状态修改
        Rooms rooms = new Rooms();
        rooms.setId(inRoomInfo.getRoomId());
        rooms.setRoomStatus("1");//房间的状态0空闲，1已入住.
        Integer integer = roomsMapper.updateByPrimaryKeySelective(rooms);
        if (insert > 0 && integer > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
