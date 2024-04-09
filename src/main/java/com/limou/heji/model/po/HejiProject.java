package com.limou.heji.model.po;

import com.limou.heji.common.domain.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author limoum0u
 * @date 24/3/30 23:00
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "heji_project")
public class HejiProject extends BaseModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * project_name
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 关键字
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 项目描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 项目主管
     */
    @Column(name = "manager")
    private String manager;

    /**
     * 项目状态
     */
    @Column(name = "status")
    private String status;


    public HejiProject() {
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ?
                ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        HejiProject that = (HejiProject) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
                getClass().hashCode();
    }
}