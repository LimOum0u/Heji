package com.limou.heji.repository;

import com.limou.heji.model.dto.HejiTransactionListDto;
import com.limou.heji.model.po.HejiTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface HejiTransactionRepository extends BaseRepository<HejiTransaction, Integer> {
    Page<HejiTransactionListDto> findByProjectId(Long projectId, Pageable pageable);
}