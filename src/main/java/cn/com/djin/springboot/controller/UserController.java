package cn.com.djin.springboot.controller;

import cn.com.djin.springboot.entity.User;
import cn.com.djin.springboot.utils.MD5;
import cn.com.djin.springboot.utils.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录验证
 *
 * author:快乐风男
 * time:10:13
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User>{
    //获取用户登陆时的验证码
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        //1.通过工具类产生随机验证码
        String s = VerifyCodeUtils.generateVerifyCode(5);
        //2.将服务器端产生的随机验证码中的英文字母转为小写并放在session容器中
        session.setAttribute("verifyCode",s.toLowerCase());
        //3.将产生的验证码转为图片显示（响应）到页面中
        VerifyCodeUtils.outputImage(250,35,response.getOutputStream(),s);
    }

    //验证用户输入的验证码
    @RequestMapping("/checkVerifyCode")
    public @ResponseBody
    String checkVerifyCode(HttpSession session, String verifyCodeIpt){
        //1.从session容器中取出之前装入的验证码
        String verifyCode = (String)session.getAttribute("verifyCode");
        //2.此时将用户输入的验证码与session中取出的验证码进行比较
        if (verifyCode.equals(verifyCodeIpt)){
            return "success";
        }else {
            return "fail";
        }
    }

    //执行登陆
    @RequestMapping("/login")
    public @ResponseBody String login(User user,HttpSession session){
        //加密输入的密码
        user.setPwd(MD5.md5crypt(user.getPwd()));
        //比对数据库
        try {
            User loginUser = baseService.findByPramas(user);
           // System.out.println(loginUser);
            if (loginUser != null){
                session.setAttribute("loginUser",loginUser);
                return "success";
            }else {
                return "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //执行退出
    @RequestMapping("/exitUser")
    public @ResponseBody String exitUser(HttpSession session){
        try {
            //移除session容器中的用户登陆对象
            session.removeAttribute("loginUser");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //重写父类添加方法,加密密码
    @Override
    public String save(User user) {
        //对密码进行MD5加密
        user.setPwd(MD5.md5crypt(user.getPwd()));
        return super.save(user);
    }
}

