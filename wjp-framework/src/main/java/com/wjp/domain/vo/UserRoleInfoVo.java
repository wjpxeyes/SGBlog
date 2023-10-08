package com.wjp.domain.vo;

import com.wjp.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleInfoVo {
    private List<Long> roleIds;
    private List<SysRole> roles;
}
