package com.scau.zwp.elevmanage.vo;

import com.scau.zwp.elevmanage.entity.ElevatorImage;
import com.scau.zwp.elevmanage.entity.Inspection;
import com.scau.zwp.elevmanage.entity.InspectionImage;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/20
 */
@Data
public class InspectionVo extends Inspection {
    private List<InspectionImage> inspectionImageList;
}
