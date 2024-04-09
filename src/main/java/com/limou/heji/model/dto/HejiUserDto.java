package com.limou.heji.model.dto;

import com.limou.heji.model.po.HejiUser;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link HejiUser}
 */
@Value
public class HejiUserDto implements Serializable {
    Long userId;
    String username;
    String avatar;
}