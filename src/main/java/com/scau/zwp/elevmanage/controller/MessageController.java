package com.scau.zwp.elevmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scau.zwp.elevmanage.common.Result;
import com.scau.zwp.elevmanage.common.StatusCode;
import com.scau.zwp.elevmanage.entity.Manufacturer;
import com.scau.zwp.elevmanage.entity.Message;
import com.scau.zwp.elevmanage.service.ManufacturerService;
import com.scau.zwp.elevmanage.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 消息 前端控制器
 * </p>
 *
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/24
 */
@Api(tags = "消息对象功能接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping
    @ApiImplicitParam(name = "id", value = "消息ID", required = true, paramType = "query", dataType = "Integer")
    public Result queryById(@RequestParam(value = "id") Integer id) {
        return messageService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Message> messageList = messageService.list();
        if (messageList != null)
            return new Result(true, StatusCode.OK, "查询消息全部数据成功", messageList);
        else
            return new Result(false, StatusCode.ERROR, "查询消息全部数据失败");
    }

    /**
     * 查询最近的数据
     *
     * @return 实例对象
     */
    @ApiOperation("查询全部数据")
    @GetMapping("/getNew")
    public Result getNew() {
        Page<Message> page = new Page<>(1, 10);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time");
        IPage<Message> iPage = messageService.page(page, queryWrapper);
        if (iPage.getRecords() != null)
            return new Result(true, StatusCode.OK, "查询消息全部数据成功", iPage.getRecords());
        else
            return new Result(false, StatusCode.ERROR, "查询消息全部数据失败");
    }

}
