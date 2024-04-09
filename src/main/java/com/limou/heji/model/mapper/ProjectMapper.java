package com.limou.heji.model.mapper;

import com.limou.heji.model.dto.HejiProjectDto;
import com.limou.heji.model.po.HejiProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author limoum0u
 * @date 24/4/7 9:28
 */
@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);


    @Mapping(source = "projectName", target = "projectName")
    @Mapping(source = "keyword", target = "keyword")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "manager", target = "manager")
    @Mapping(source = "icon", target = "icon")
    HejiProject toEntity(HejiProjectDto projectDto);
}
