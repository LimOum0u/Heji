package com.limou.heji.repository;

import com.limou.heji.model.po.HejiProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author limoum0u
 * @date 24/3/30 22:44
 */
@Repository
public interface ProjectRepository extends BaseRepository<HejiProject, Long> {

//    Page<HejiProject> findALll(Pageable pageable);
}
