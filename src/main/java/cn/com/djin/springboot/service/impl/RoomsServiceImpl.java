package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.Rooms;
import cn.com.djin.springboot.service.RoomsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:快乐风男
 * time:11:39
 */
@Service
@Transactional(readOnly = false)
public class RoomsServiceImpl extends BaseServiceImpl<Rooms> implements RoomsService {


}
