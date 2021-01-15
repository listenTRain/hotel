package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.RoomType;
import cn.com.djin.springboot.service.RoomTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:快乐风男
 * time:14:33
 */
@Service
@Transactional(readOnly = false)
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType> implements RoomTypeService{
}
