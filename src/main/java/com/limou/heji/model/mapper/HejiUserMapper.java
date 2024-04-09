package com.limou.heji.model.mapper;

import com.limou.heji.model.dto.HejiUserDto;
import com.limou.heji.model.po.HejiUser;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HejiUserMapper {
    HejiUser toEntity(HejiUserDto hejiUserDto);

    HejiUserDto toDto(HejiUser hejiUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    HejiUser partialUpdate(HejiUserDto hejiUserDto, @MappingTarget HejiUser hejiUser);
}