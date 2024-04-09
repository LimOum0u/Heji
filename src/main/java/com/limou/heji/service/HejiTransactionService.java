package com.limou.heji.service;

import com.limou.heji.model.dto.HejiTransactionListDto;
import com.limou.heji.repository.HejiTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author limoum0u
 * @date 24/3/31 11:18
 */
@Service
@RequiredArgsConstructor
public class HejiTransactionService {

    private final HejiTransactionRepository hejiTransactionRepository;

    public Page<HejiTransactionListDto> findByProjectId(Long projectId, int pageNum, int pageSize) {
        return hejiTransactionRepository.findByProjectId(projectId, Pageable.ofSize(pageSize).withPage(pageNum - 1));
    }


}
