package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.Authority;
import cn.com.djin.springboot.entity.User;
import cn.com.djin.springboot.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;



/**
 * author:快乐风男
 * time:10:29
 */
@Service
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
    @Override
    public Map<String, Object> findPageByPramas(Integer page, Integer limit, User user) throws Exception {
        Map<String, Object> pageByPramas = super.findPageByPramas(page, limit, user);
        List<User> data = (List<User>) pageByPramas.get("data");
        data.forEach(oneUser->{
            //得到user的角色id
            Integer id = oneUser.getRoleId();
            try {
                //根据角色id查询其角色拥有的一级权限数据
                List<Authority> authorities = authorityMapper.selectAuthoritiesByRoleIdAndParent(id, 0);
                //将一级权限循环得到权限名称字符串
                StringBuffer stringBuffer = new StringBuffer();
                authorities.forEach(firstAName->{
                    String authorityName = firstAName.getAuthorityName();
                    stringBuffer.append(authorityName + ",");
                });
                //将权限字符串赋值给user属性，并截取（去除逗号）
                oneUser.setFirstANames(stringBuffer.toString().substring(0,stringBuffer.lastIndexOf(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return pageByPramas;
    }
}
