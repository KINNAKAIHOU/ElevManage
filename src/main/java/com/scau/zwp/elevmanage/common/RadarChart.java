package com.scau.zwp.elevmanage.common;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/3/2
 */
@Data
public class RadarChart {
    private List<Integer> value;
    private String name;
}
