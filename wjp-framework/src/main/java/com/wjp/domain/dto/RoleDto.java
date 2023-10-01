package com.wjp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * {
 * "roleName":"测试新增角色",
 * "roleKey":"wds",
 * "roleSort":0,
 * "status":"0",
 * "menuIds":[
 * "1",
 * "100"
 * ],
 * "remark":"我是角色备注"
 * }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private List<Long> menuIds;
    private String remark;
}
