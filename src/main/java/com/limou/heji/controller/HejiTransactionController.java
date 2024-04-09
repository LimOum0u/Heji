package com.limou.heji.controller;

import com.limou.heji.model.dto.HejiTransactionListDto;
import com.limou.heji.service.HejiTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limoum0u
 * @date 24/3/31 11:33
 */

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class HejiTransactionController {

    private final HejiTransactionService hejiTransactionService;

    @GetMapping
    public Page<HejiTransactionListDto> findByProjectId(Long projectId, int pageNum, int pageSize) {
        return hejiTransactionService.findByProjectId(projectId, pageNum, pageSize);
    }
}
