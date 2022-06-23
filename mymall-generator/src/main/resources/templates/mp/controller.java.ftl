package ${package.Controller};

import com.mymall.framework.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Entity}.dto.ResultBean;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import javax.annotation.Resource;


/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 * @version v1.0
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/api/v1/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 查询分页数据
    */
<#--    @RequestMapping(method = RequestMethod.GET)-->
    @GetMapping("/list")
    public Result listByPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                    @RequestParam(name = "factor", defaultValue = "") String factor) {
        return Result.success(${table.serviceName?uncap_first}.list${entity}sByPage(page, pageSize,factor));
    }


    /**
    * 根据id查询
    */
<#--    @RequestMapping(method = RequestMethod.GET, value = "/{id}")-->
    @GetMapping(value = { "/", "/{id}" })
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(${table.serviceName?uncap_first}.get${entity}ById(id));
    }

    /**
    * 新增
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result insert(@RequestBody ${entity} ${entity?uncap_first}) {
        return Result.success(${table.serviceName?uncap_first}.insert${entity}(${entity?uncap_first}));
    }

    /**
    * 删除
    */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        return Result.success(${table.serviceName?uncap_first}.delete${entity}ById(id));
    }

    /**
    * 修改
    */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateById(@RequestBody ${entity} ${entity?uncap_first}) {
        return Result.success(${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}));
    }
}
</#if>