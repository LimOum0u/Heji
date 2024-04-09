package com.limou.heji.service;

import com.limou.heji.model.po.HejiProject;
import com.limou.heji.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author limoum0u
 * @date 24/3/30 22:43
 */
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public HejiProject findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Page<HejiProject> findAllProjectWithPage(int pageNum, int pageSize) {
        return projectRepository.findAll(Pageable.ofSize(pageSize).withPage(pageNum));
    }

    public HejiProject saveProject(HejiProject project) {
        return projectRepository.save(project);
    }
}
