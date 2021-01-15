package cn.com.djin.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author:快乐风男
 * time:20:30
 */
@Controller
@RequestMapping("/model")
public class ModelController {

      /*//跳转到首页
        @RequestMapping("/toIndex")
        public String toIndex() {
            return "index";
        }*/

    //跳转到入住信息显示页面
    @RequestMapping("/toShowInRoomInfo")
    public String toShowInRoomInfo() {
        return "inroominfo/showInRoomInfo";
    }

    //跳转到入住信息添加页面
    @RequestMapping("/toSaveInRoomInfo")
    public String toSaveRoomInfo() {
        return "inroominfo/saveInRoomInfo";
    }

    //跳转订单查询界面
    @RequestMapping("/toShowOrders")
    public String toShowOrders() {
        return "orders/showOrders";
    }

    //跳转消费记录显示页面
    @RequestMapping("/toShowRoomSale")
    public String toShowRoomSale() {
        return "roomSale/showRoomSale";
    }

    //跳转会员显示页面
    @RequestMapping("/toShowVip")
    public String toShowVip(){
        return "vip/showVip";
    }

    //跳转到添加会员页面
    @RequestMapping("/toSaveVip")
    public String toSaveVip(){
        return "vip/saveVip";
    }

    //跳转客房显示页面
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){
        return "rooms/showRooms";
    }

    //跳转登录界面
    @RequestMapping("/loginUI")
    public String loginUI(){
        return "login/login";
    }

    //跳转角色信息管理
    @RequestMapping("/toShowRole")
    public String toShowRole(){
        return "role/showRole";
    }

    //跳转用户信息管理
    @RequestMapping("/toShowUser")
    public String toShowUser(){
        return "user/showUser";
    }

    //跳转添加用户页面
    @RequestMapping("/toSaveUser")
    public String toSaveUser(){
        return "user/saveUser";
    }

    //跳转数据分析
    @RequestMapping("/toShowIdd")
    public String toShowIdd(){
        return "idd/showIdd";
    }

    //跳转房型显示页面
    @RequestMapping("/toShowRoomType")
    public String toShowRoomType(){
        return "roomType/showRoomType";
    }

}

