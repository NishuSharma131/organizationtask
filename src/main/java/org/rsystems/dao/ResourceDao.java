package org.rsystems.dao;

import org.rsystems.model.Resource;

import java.util.ArrayList;

public interface ResourceDao {

    Resource getResourceById(int resources_id);
    int saveNewResource(Resource resources);
    void deleteResource(Resource resource);
    ArrayList<Resource> getAllResources();
    void updateResource(Resource resource);
}
