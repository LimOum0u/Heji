package com.limou.heji.controller;

import com.limou.heji.model.mapper.ProjectMapper;
import com.limou.heji.model.po.HejiProject;
import com.limou.heji.model.dto.HejiProjectDto;
import com.limou.heji.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author limoum0u
 * @date 24/3/30 14:01
 */
@RestController
@RequestMapping("projects")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Page<HejiProject> findAllProjectWithPage(@RequestParam(value = "pageNum", required = false, defaultValue =
            "1") int pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue
                                                            = "10") int pageSize) {
        return projectService.findAllProjectWithPage(pageNum - 1, pageSize);
    }

    @PostMapping
    @Transactional
    public HejiProject saveProject(@RequestBody @Validated HejiProjectDto projectDto) {
        HejiProject hejiProject = ProjectMapper.INSTANCE.toEntity(projectDto);
        return projectService.saveProject(hejiProject);
    }


}
