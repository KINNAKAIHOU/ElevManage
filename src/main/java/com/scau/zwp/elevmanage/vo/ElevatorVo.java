package com.scau.zwp.elevmanage.vo;

import com.scau.zwp.elevmanage.entity.Elevator;
import com.scau.zwp.elevmanage.entity.ElevatorImage;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/19
 */
@Data
public class ElevatorVo extends Elevator {
    private List<ElevatorImage> elevatorImageList;
}
