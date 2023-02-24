package com.scau.zwp.elevmanage.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/23
 */
@Data
public class IndexData implements Serializable {

    private int elevatorCount;
    private int notFinishInspection;
    private int notFinishMaintenance;

    public IndexData() {
    }

    public IndexData(int elevatorCount, int notFinishInspection, int notFinishMaintenance) {
        this.elevatorCount = elevatorCount;
        this.notFinishInspection = notFinishInspection;
        this.notFinishMaintenance = notFinishMaintenance;
    }

}
