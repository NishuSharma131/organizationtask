package org.rsystems.dao;

import org.hibernate.Criteria;

import org.hibernate.criterion.Order;
import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class ProjectDaoImpl extends AbstractDao<Integer, Project> implements ProjectDao {
    @Override
    public Project getProjectById(int projectId) {
        return getByPrimaryKey(projectId);
    }

    @Override
    public int saveNewProject(Project project) {
        return persist(project);
    }

    @Override
    public void deleteProject(Project project) {
        delete(project);
    }


    public ArrayList<Project> getAllProject() {
        // TODO Auto-generated method stub
        Criteria crit = createEntityCriteria().addOrder(Order.asc("project_id"));
        ArrayList<Project> projectsList = (ArrayList<Project>)crit.list();

        return projectsList;
    }


    @Override
    public void updateProject(Project project) {
        update(project);
    }


}
