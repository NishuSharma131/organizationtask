package org.rsystems.services;

import org.rsystems.model.Organization;

import java.util.ArrayList;

public interface OrganizationService {
    int saveNewOrganization(Organization Organization);


    void deleteOrganization(Organization organization);

    ArrayList<Organization> getAllOrganization();

    Organization  getOrganizationById(int Organization_id);

    void updateOrganization(Organization organization);
    
}
