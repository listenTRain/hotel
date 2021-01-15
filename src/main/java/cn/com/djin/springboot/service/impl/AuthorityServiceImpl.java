package cn.com.djin.springboot.service.impl;

import cn.com.djin.springboot.entity.Authority;
import cn.com.djin.springboot.entity.User;
import cn.com.djin.springboot.service.AuthorityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:快乐风男
 * time:13:12
 */
@Service
@Transactional(readOnly = false)
public class AuthorityServiceImpl extends BaseServiceImpl<Authority> implements AuthorityService {

    /**
     *   根据用户登陆之后查询其拥有的权限菜单
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> findAuthoritiesByLogin(User user) throws Exception {
        //1.新建装入一级权限和二级权限数据的list集合
        List<Map<String,Object>> mapList = new ArrayList<>();
        //2.根据登陆用户的角色id查询其拥有的一级权限
        List<Authority> authorities = authorityMapper.selectAuthoritiesByRoleIdAndParent(user.getRoleId(), 0);
        //3.根据登陆用户拥有的一级权限分别查询出其拥有的二级权限
        for (int i = 0; i < authorities.size(); i++) {
            //新建装一级权限及二级权限的map集合
            Map<String,Object> map = new HashMap<>();
            //获取每个一级权限对象
            Authority authority = authorities.get(i);
            //获取一级权限的id
            Integer id = authority.getId();
            //获取一级权限的名称
            String authorityName = authority.getAuthorityName();
            //根据一级权限id查询出每一个一级权限下的二级权限
            List<Authority> authorityList = authorityMapper.selectAuthoritiesByRoleIdAndParent(user.getRoleId(), id);
            //将一级权限id，名称，及其二级权限装入map
           map.put("firstAId",id);
           map.put("firstAName",authorityName);
           map.put("secAuthorities",authorityList);
            //4.将装好权限的一级权限map集合装入到mapList并返回
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public List<Authority> findAuthoritiesByRoleId(Integer roleId) throws Exception {
        return authorityMapper.selectAuthoritiesByRoleId(roleId);
    }


}
