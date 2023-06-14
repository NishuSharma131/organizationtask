package org.rsystems.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.rsystems.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class ResourceDaoImpl extends AbstractDao<Integer, Resource> implements ResourceDao {
    @Override
    public Resource getResourceById(int resourceId) {
        return getByPrimaryKey(resourceId);
    }

    @Override
    public int saveNewResource(Resource resource) {
        return persist(resource);
    }

    @Override
    public void deleteResource(Resource resource) {
        delete(resource);
    }


    public ArrayList<Resource> getAllResources() {
        // TODO Auto-generated method stub
        Criteria crit = createEntityCriteria().addOrder(Order.asc("resource_id"));
        ArrayList<Resource> resourcessList = (ArrayList<Resource>)crit.list();

        return resourcessList;
    }


    @Override
    public void updateResource(Resource resource) {
        update(resource);
    }

}
