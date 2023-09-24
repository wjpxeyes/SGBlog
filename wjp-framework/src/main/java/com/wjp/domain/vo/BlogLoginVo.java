package com.wjp.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogLoginVo {
    private String token;
    private UserInfoVo userInfoVo;
}
