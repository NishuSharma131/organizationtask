package org.rsystems.services;


import org.rsystems.model.ProjectResources;
import org.rsystems.model.Resource;

import java.util.ArrayList;

public interface ResourceService {

    int saveNewResource(Resource resource);
    void deleteResource(Resource resources);
    ArrayList<Resource> getAllResources();
    Resource getResourceById(int resources_id);
    void updateResource(Resource resource);

}
