package cn.com.djin.springboot.controller;

import cn.com.djin.springboot.entity.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author:快乐风男
 * time:14:50
 */
@Controller
@RequestMapping("/roles")
public class RoleController extends BaseController<Roles> {
}
