package org.rsystems.dao;



import org.rsystems.model.ProjectResources;

import java.util.ArrayList;

public interface ProjectResourcesDao {

    void  saveAndUpdateProjectResources(ProjectResources projectResources);
    ProjectResources getResourceById(int resources_allocation_id);

    int getResourceAllocation(int resource_allocation_id);
   }
