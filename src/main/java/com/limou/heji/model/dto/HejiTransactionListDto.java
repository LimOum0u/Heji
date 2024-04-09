package com.limou.heji.model.dto;

import com.limou.heji.model.po.HejiTransaction;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link HejiTransaction}
 */
@Value
public class HejiTransactionListDto implements Serializable {
    Integer id;
    String name;
    String gist;
    String description;
    String transactionPriority;
    String transactionStatus;
    String solutionResult;
    Long epicLinkId;
    String epicLinkName;
    String reportedBy;
    String handledBy;
    String developedBy;
    String testedBy;
    Integer typeId;
    Date createdAt;
}