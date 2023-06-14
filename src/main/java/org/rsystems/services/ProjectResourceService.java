package org.rsystems.services;


import org.rsystems.model.ProjectResources;
import org.rsystems.model.Resource;

import java.util.ArrayList;

public interface ProjectResourceService {
    void saveAndUpdateProjectResources(ProjectResources projectResources);

    ProjectResources getResourceById(int resources_allocation_id);
    int getResourceAllocation(int resource_allocation_id);
}

