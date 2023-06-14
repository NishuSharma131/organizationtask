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
public class ProjectResourceServiceImpl implements ProjectResourceService {


    @Autowired
    ProjectResourcesDao projectResourcesDao;


    @Override
    public void saveAndUpdateProjectResources(ProjectResources projectResources) {
        projectResourcesDao.saveAndUpdateProjectResources(projectResources);
    }
    @Override
    public ProjectResources getResourceById(int resources_allocation_id) {
        return projectResourcesDao.getResourceById(resources_allocation_id);
    }

    @Override
    public int getResourceAllocation(int resource_allocation_id) {
        return projectResourcesDao.getResourceAllocation(resource_allocation_id);
    }


}
