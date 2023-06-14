package org.rsystems.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.rsystems.model.Organization;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class OrganizationDaoImpl extends AbstractDao<Integer, Organization> implements OrganizationDao {


    @Override
    public Organization getOrganizationById(int organizationId) {
        return getByPrimaryKey(organizationId);
    }

    @Override
    public int saveNewOrganization(Organization organization) {
        return persist(organization);
    }

    @Override
    public void deleteOrganization(Organization organization) {
        delete(organization);
    }


    public ArrayList<Organization> getAllOrganization() {
        // TODO Auto-generated method stub
        Criteria crit = createEntityCriteria().addOrder(Order.asc("org_id"));
        ArrayList<Organization> organizationsList = (ArrayList<Organization>)crit.list();

        return organizationsList;
    }


    @Override
    public void updateOrganization(Organization organization) {
        update(organization);
    }

}
