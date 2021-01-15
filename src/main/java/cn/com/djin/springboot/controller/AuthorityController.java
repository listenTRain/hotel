package cn.com.djin.springboot.controller;

import cn.com.djin.springboot.entity.Authority;
import cn.com.djin.springboot.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 权限控制器
 *
 * author:快乐风男
 * time:11:32
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController extends BaseController<Authority>{

    //登陆完成后加载用户拥有的权限跳转到平台首页
    @RequestMapping("/toIndex")
    public String toIndex(Model model, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        try {
            List<Map<String, Object>> mapList = authorityService.findAuthoritiesByLogin(loginUser);
            model.addAttribute("mapList",mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     *   根据角色id查询角色拥有的权限
     * @param roleId  角色id
     * @return   权限的集合
     * @throws Exception
     */
    @RequestMapping("/loadAuthoritiesByRoleId")
    public @ResponseBody List<Authority> loadAuthoritiesByRoleId(Integer roleId){
        try {
            return authorityService.findAuthoritiesByRoleId(roleId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
