package com.limou.heji.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * @author limoum0u
 * @date 23/10/15 15:37
 */

/**
 * 公共实体类
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {
    /**
     * created_at
     */
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "created_at", columnDefinition = "datetime(3)")
    private Date createdAt;


    /**
     * updated_at
     */
    @LastModifiedDate
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "updated_at", columnDefinition = "datetime(3)")
    private Date updatedAt;

    /**
     * created_by
     */
    @CreatedBy
    private String createdBy;

    /**
     * updated_by
     */
    @LastModifiedBy
    private String updatedBy;
}
