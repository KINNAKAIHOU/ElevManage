package com.scau.zwp.elevmanage.vo;

import com.scau.zwp.elevmanage.entity.Storage;
import com.scau.zwp.elevmanage.entity.StorageItem;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: KinnakaIhou
 * @CreateTime: 2023/2/19
 */
@Data
public class StorageVo extends Storage {
    private List<StorageItem> storageItemList;
}
