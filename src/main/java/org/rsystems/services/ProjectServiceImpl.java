package org.rsystems.services;


import org.rsystems.dao.ProjectDao;
import org.rsystems.dao.ProjectResourcesDao;
import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;


    @Override
    public int saveNewProject(Project project) {
        return projectDao.saveNewProject(project);
    }

    @Override
    public void deleteProject(Project project) {
        projectDao.deleteProject(project);
    }


    @Override
    public ArrayList<Project> getAllProject() {
        return projectDao.getAllProject();
    }

    @Override
    public Project getProjectById(int projectId) {
        return projectDao.getProjectById(projectId);
    }

    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }



}
