package cn.com.djin.springboot.controller;

import cn.com.djin.springboot.service.AuthorityService;
import cn.com.djin.springboot.service.BaseService;
import cn.com.djin.springboot.service.OrdersService;
import cn.com.djin.springboot.service.RoomSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *   基础控制器层  要进行数据交互
 * @param <T>  传入的实体对象泛型
 */
public class BaseController<T> {

    //依赖基础的业务层对象
    @Autowired
    protected BaseService<T> baseService;

    //订单的业务层对象
    @Autowired
    protected OrdersService ordersService;

    //权限的业务层对象
    @Autowired
    protected AuthorityService authorityService;

    //房间销售记录业务层对象
    @Autowired
    protected RoomSaleService roomSaleService;
    /**
     *   根据主键删除单个数据
     * @param id  主键id
     * @return  删除结果
     */
    @RequestMapping("/delByPrimaryKey")
    public @ResponseBody String delByPrimaryKey(Integer id){
        try {
            return baseService.removeByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   添加数据
     * @param t  添加数据对象
     * @return  添加结果
     */
    @RequestMapping("/save")
    public @ResponseBody String save(T t){
        try {
            return baseService.save(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   动态添加数据
     * @param t  添加数据对象
     * @return  添加结果
     */
    @RequestMapping("/saveSelective")
    public @ResponseBody String saveSelective(T t) {
        try {
            return baseService.saveSelective(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   根据主键查询单个数据
     * @param id  主键id
     * @return  单个数据结果
     */
    @RequestMapping("/loadByPrimaryKey")
    public @ResponseBody T loadByPrimaryKey(Integer id) {
        try {
            return baseService.findByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *   根据主键动态修改数据
     * @param t  要修改的对象数据
     * @return  修改结果
     */
    @RequestMapping("/updByPrimaryKeySelective")
    public @ResponseBody String updByPrimaryKeySelective(T t) {
        try {
            return baseService.updateByPrimaryKeySelective(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   根据主键修改所有字段数据
     * @param t  要修改的对象数据
     * @return 修改结果
     */
    @RequestMapping("/updateByPrimaryKey")
    public @ResponseBody String updateByPrimaryKey(T t){
        try {
            return baseService.updateByPrimaryKey(t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   根据条件分页加载数据
     * @param page  当前页
     * @param limit  每一页数据条数
     * @param t  查询的条件
     * @return  Layui的table方法渲染需要的分页数据集合
     */
    @RequestMapping("/loadPageByPramas")
    public @ResponseBody Map<String,Object> loadPageByPramas(Integer page,Integer limit,T t){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            map = baseService.findPageByPramas(page,limit,t);
            map.put("code",0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",200);
            map.put("msg","数据加载异常。。");
        }
        return map;
    }

    /**
     *   根据条件加载单个数据
     * @param t  条件参数对象
     * @return  查询的单个数据
     */
    @RequestMapping("/loadByPramas")
    public @ResponseBody T loadByPramas(T t){
        try {
            return baseService.findByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    /**
     *   根据条件加载多个数据
     * @param t  条件参数
     * @return  多条数据
     */
    @RequestMapping("/loadManyByPramas")
    public @ResponseBody
    List<T> loadManyByPramas(T t){
        try {
            return baseService.findManyByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *   根据多个主键ids动态批量修改数据
     * @param ids  多个主键ids数组
     * @param t  修改的数据
     * @return  操作结果
     *   js中的34,28,32字符串数据可以在springMVC中通过Integer[]接收到[34,28,32]
     */
    @RequestMapping("/updBatchByPrimaryKeySelective")
    public @ResponseBody String updBatchByPrimaryKeySelective(Integer[] ids,T t){
        try {
            return baseService.updBatchByPrimaryKeySelective(ids,t);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     *   根据条件查询数据条数
     * @param t  查询的条件
     * @return  数据条数
     */
    @RequestMapping("/getCountByPramas")
    public @ResponseBody Integer getCountByPramas(T t){
        try {
            return baseService.getCountByPramas(t);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *   查询表的所有数据
     * @return  数据结果集
     */
    @RequestMapping("/loadAll")
    public @ResponseBody List<T> loadAll(){
        try {
            return baseService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
