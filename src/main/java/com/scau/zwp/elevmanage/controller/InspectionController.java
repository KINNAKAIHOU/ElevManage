package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.R;
import com.scau.zwp.elevmanage.entity.Inspection;
import com.scau.zwp.elevmanage.service.InspectionService;
import com.scau.zwp.elevmanage.vo.InspectionVo;
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
 * 检查报告 前端控制器
 * </p>
 *
 * @author KinnakaIhou
 * @since 2023-02-19 10:47:51
 */
@Api(tags = "检查报告对象功能接口")
@RestController
@RequestMapping("/inspection")
public class InspectionController {
    @Resource
    private InspectionService inspectionService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "检查报告ID", required = true, paramType = "query", dataType = "Integer")
    public R<InspectionVo> queryById(@RequestParam(value = "id") Integer id) {
        return inspectionService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public R<List<Inspection>> getAll() {
        List<Inspection> inspectionList = inspectionService.list();
        if (inspectionList != null)
            return R.success(inspectionList);
        else
            return R.error("查询所有失败");
    }

    /**
     * 分页查询
     *
     * @param inspection 筛选条件
     * @param current    页码
     * @param size       元素
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @PostMapping("/pagin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页码", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "元素", required = true, paramType = "query", dataType = "Integer"),
    })
    public R<Page<Inspection>> paginQuery(@RequestBody Inspection inspection, @RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<Inspection> pageResult = inspectionService.paginQuery(inspection, current, size);
        return R.success(pageResult);
    }


    /**
     * 新增数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestPart("inspection") Inspection inspection, @RequestPart(name = "files", required = false) MultipartFile[] files) {
        return inspectionService.insert(inspection, files);
    }


    /**
     * 更新数据
     *
     * @param inspection 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestPart("inspection") Inspection inspection, @RequestPart(name = "files", required = false) MultipartFile[] files) {
        return inspectionService.update(inspection, files);
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping
    @ApiImplicitParam(name = "id", value = "检查报告ID", required = true, paramType = "query", dataType = "Integer")
    public R<Boolean> deleteById(@RequestParam(value = "id") Integer id) {
        return inspectionService.deleteById(id);
    }

    /**
     * 确认完成
     *
     * @param inspection 主键
     * @return 是否成功
     */
    @ApiOperation("确认完成")
    @PostMapping ("/finish")
    public R<Boolean> finish(@RequestBody Inspection inspection) {
        return inspectionService.finish(inspection);
    }


}
