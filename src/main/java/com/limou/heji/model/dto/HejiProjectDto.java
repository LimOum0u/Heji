package com.limou.heji.model.dto;

import com.limou.heji.model.po.HejiProject;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link HejiProject}
 */
@Value
public class HejiProjectDto implements Serializable {
    @NotBlank(message = "项目名字不能为空")
    String projectName;
    String icon;
    @NotBlank(message = "关键字不能为空")
    String keyword;
    String description;
    @NotBlank(message = "负责人不能为空")
    String manager;
}