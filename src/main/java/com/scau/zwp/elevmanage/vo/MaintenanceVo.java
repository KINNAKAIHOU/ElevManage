package com.scau.zwp.elevmanage.vo;

import com.scau.zwp.elevmanage.entity.InspectionImage;
import com.scau.zwp.elevmanage.entity.Maintenance;
import com.scau.zwp.elevmanage.entity.MaintenanceImage;
import com.scau.zwp.elevmanage.entity.MaintenanceItem;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/20
 */
@Data
public class MaintenanceVo extends Maintenance {
    List<MaintenanceImage> maintenanceImageList;
    List<InspectionImage> inspectionImageList;
    List<MaintenanceItem> maintenanceItemList;
}
