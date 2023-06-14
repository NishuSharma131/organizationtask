package org.rsystems.services;


import org.rsystems.dao.ProjectResourcesDao;
import org.rsystems.dao.ResourceDao;
import org.rsystems.model.ProjectResources;
import org.rsystems.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    ResourceDao resourceDao;
    @Autowired
    ProjectResourcesDao projectResourcesDao;

    @Override
    public int saveNewResource(Resource resource) {
        return resourceDao.saveNewResource(resource);
    }

    @Override
    public void deleteResource(Resource resource) {
    resourceDao.deleteResource(resource);
    }

    @Override
    public ArrayList<Resource> getAllResources() {
        return resourceDao.getAllResources();
    }

    @Override
    public Resource getResourceById(int resources_id) {
        return resourceDao.getResourceById(resources_id);
    }

    @Override
    public void updateResource(Resource resource) {
        resourceDao.updateResource(resource);
    }

}
