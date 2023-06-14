package org.rsystems.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;


@Repository
public class ProjectResourcesDaoImpl extends AbstractDao<Integer, ProjectResources> implements ProjectResourcesDao {

    @Override
    public void saveAndUpdateProjectResources(ProjectResources projectResources) {
        saveOrUpdate(projectResources);
    }

    @Override
    public ProjectResources getResourceById(int resources_allocation_id) {
        return getByPrimaryKey(resources_allocation_id);
    }

    @Override
    public int getResourceAllocation(int resource_allocation_id) {
        Query query = getSession().createQuery("select sum(allocation) from " + ProjectResources.class.getSimpleName() + " where resource_allocation_id =:sprintName");
        query.setParameter("resource_allocation_id", resource_allocation_id);

        return query.getFirstResult();
    }

}
