package com.wjp.domain.vo;

import com.wjp.domain.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {
    private List<TagDTO> rows;
    private Long total;
}
