package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.Vip;

import cn.com.djin.springboot.service.VipService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:快乐风男
 * time:14:30
 */
@Service
@Transactional(readOnly = false)
public class VipServiceImpl extends BaseServiceImpl<Vip> implements VipService {

}
