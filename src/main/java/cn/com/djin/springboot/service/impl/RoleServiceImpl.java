package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.Authority;
import cn.com.djin.springboot.entity.Roles;
import cn.com.djin.springboot.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * author:快乐风男
 * time:14:10
 */
@Service
@Transactional(readOnly = false)
public class RoleServiceImpl extends BaseServiceImpl<Roles> implements RoleService{

    //重写角色根据条件分页查询的方法

    @Override
    public Map<String, Object> findPageByPramas(Integer page, Integer limit, Roles roles) throws Exception {
        //1.执行父类的分页查询方法，得到分页数据
        Map<String, Object> pageByPramas = super.findPageByPramas(page, limit, roles);
        //2.取出data数据
        List<Roles> rolesData =(List<Roles>) pageByPramas.get("data");
        //取出roles数据中的id
        rolesData.forEach(oneRole->{
            Integer roleId = oneRole.getId();
            System.out.println(roleId);
            //根据角色id查询其角色拥有的一级权限数据
            try {
                List<Authority> authorities = authorityMapper.selectAuthoritiesByRoleIdAndParent(roleId,0);
                //将每个角色拥有的一级权限名称拼接成字符串
                StringBuffer firstANames = new StringBuffer();
                authorities.forEach(firstAName->{
                    String authorityName = firstAName.getAuthorityName();
                    firstANames.append(authorityName+",");
                });
                //将权限字符串赋值给role属性，并截取（去除逗号）
                oneRole.setFirstANames(firstANames.toString().substring(0,firstANames.lastIndexOf(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return pageByPramas;
    }
}
