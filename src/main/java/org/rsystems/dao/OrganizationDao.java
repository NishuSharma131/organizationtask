package org.rsystems.dao;


import org.rsystems.model.Organization;

import java.util.ArrayList;

public interface OrganizationDao {

    Organization getOrganizationById(int orgId);

    int saveNewOrganization(Organization organization);

    void deleteOrganization(Organization organization);

    void updateOrganization(Organization organization);

    ArrayList<Organization> getAllOrganization();
}
