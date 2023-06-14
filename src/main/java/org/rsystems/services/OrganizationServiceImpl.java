package org.rsystems.services;


import org.rsystems.dao.OrganizationDao;
import org.rsystems.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationDao orgDao;

    @Override
    public int saveNewOrganization(Organization Organization) {
        return orgDao.saveNewOrganization(Organization);
    }

    @Override
    public void deleteOrganization(Organization Organization) {
        orgDao.deleteOrganization(Organization);
    }


    @Override
    public ArrayList<Organization> getAllOrganization() {
        return orgDao.getAllOrganization();
    }

    @Override
    public Organization getOrganizationById(int OrganizationId) {
        return orgDao.getOrganizationById(OrganizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        orgDao.updateOrganization(organization);
    }


}
