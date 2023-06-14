package org.rsystems.services;


import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;

import java.util.ArrayList;

public interface ProjectService {

    int saveNewProject(Project project);


    void deleteProject(Project project);



    ArrayList<Project> getAllProject();

    Project  getProjectById(int project_id);


    void updateProject(Project project);


}
