package com.limou.heji.model.po;

import com.limou.heji.common.domain.BaseModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author limoum0u
 * @date 24/3/31 10:50
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "heji_transaction", indexes = {
        @Index(name = "idx_transaction_name", columnList = "transaction_name", unique = true)
})
public class HejiTransaction extends BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    /**
     * name
     */
    @Column(name = "transaction_name")
    private String name;

    /**
     * gist
     */
    @Column(name = "gist")
    private String gist;

    /**
     * description
     */
    @Column(name = "description", columnDefinition = "varchar(1024)")
    private String description;

    /**
     * issue_priority
     */
    @Column(name = "transaction_priority")
    private String transactionPriority;

    /**
     * issue_status
     */
    @Column(name = "transaction__status")
    private String transactionStatus;

    /**
     * solution_result
     */
    @Column(name = "solution_result")
    private String solutionResult;

    /**
     * epic_link_id
     */
    @Column(name = "epic_link_id")
    private Long epicLinkId;

    /**
     * epic_link_name
     */
    @Column(name = "epic_link_name")
    private String epicLinkName;

    /**
     * reported_by
     */
    @Column(name = "reported_by")
    private String reportedBy;

    /**
     * handled_by
     */
    @Column(name = "handled_by")
    private String handledBy;

    /**
     * developed_by
     */
    @Column(name = "developed_by")
    private String developedBy;

    /**
     * tested_by
     */
    @Column(name = "tested_by")
    private String testedBy;

    /**
     * type_id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * project_id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * affected_version_id
     */
    @Column(name = "affected_version_id")
    private Long affectedVersionId;

    /**
     * fix_version_id
     */
    @Column(name = "fix_version_id")
    private Long fixVersionId;

    @Column(name = "del_flag")
    private String delFlag;

    public HejiTransaction() {
    }
}
