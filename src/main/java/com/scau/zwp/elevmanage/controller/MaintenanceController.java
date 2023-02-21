package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Maintenance;
import com.scau.zwp.elevmanage.entity.MaintenanceItem;
import com.scau.zwp.elevmanage.entity.StorageItem;
import com.scau.zwp.elevmanage.service.MaintenanceService;
import com.scau.zwp.elevmanage.vo.MaintenanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 维修报告 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "维修报告对象功能接口")
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Resource
    private MaintenanceService maintenanceService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "维修报告ID", required = true, paramType = "query", dataType = "Integer")
    public R<MaintenanceVo> queryById(@RequestParam(value = "id") Integer id) {
        return maintenanceService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public R<List<Maintenance>> getAll() {
        List<Maintenance> maintenanceList = maintenanceService.list();
        if (maintenanceList != null)
            return R.success(maintenanceList);
        else
            return R.error("查询所有失败");
    }

    /**
     * 分页查询
     *
     * @param maintenance 筛选条件
     * @param current     页码
     * @param size        元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public R<Page<Maintenance>> paginQuery(@RequestBody Maintenance maintenance, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Maintenance> pageResult = maintenanceService.paginQuery(maintenance, current, size);
        return R.success(pageResult);
    }


    /**
     * 新增数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestPart("maintenance") Maintenance maintenance, @RequestPart("maintenanceItemList") List<MaintenanceItem> maintenanceItemList, @RequestPart(name = "files", required = false) MultipartFile[] files) {
        return maintenanceService.insert(maintenance, maintenanceItemList,files);
    }


    /**
     * 更新数据
     *
     * @param maintenance 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestPart("maintenance") Maintenance maintenance, @RequestPart("maintenanceItemList") List<MaintenanceItem> maintenanceItemList, @RequestPart(name = "files", required = false) MultipartFile[] files) {
        return maintenanceService.update(maintenance,maintenanceItemList ,files);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "维修报告ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> deleteById(@RequestParam(value = "id") Integer id) {
        return maintenanceService.deleteById(id);
    }

}
