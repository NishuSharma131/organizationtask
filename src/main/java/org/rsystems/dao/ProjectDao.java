package org.rsystems.dao;


import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;

import java.util.ArrayList;

public interface ProjectDao {
    Project getProjectById(int project_id);
    int saveNewProject(Project project);
    void deleteProject(Project project);
    ArrayList<Project> getAllProject();
    void updateProject(Project project);

   }
