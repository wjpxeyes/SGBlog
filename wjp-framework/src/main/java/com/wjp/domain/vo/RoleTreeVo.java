package com.wjp.domain.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleTreeVo {
    private List<RoleTreeVo> children;
    private Long id;
    @JsonProperty(value = "label")
    private String menuName;
    private Long parentId;

}
