package org.rsystems.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.rsystems.model.Designation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class DesignationDaoImpl extends AbstractDao<Integer, Designation> implements DesignationDao {

    @Override
    public Designation getDesignationById(int designationId) {
        return getByPrimaryKey(designationId);
    }

    @Override
    public int saveNewDesignation(Designation designation) {
        return persist(designation);
    }

    @Override
    public void deleteDesignation(Designation designation) {
        delete(designation);
    }


    public ArrayList<Designation> getAllDesignation() {
        // TODO Auto-generated method stub
        Criteria crit = createEntityCriteria().addOrder(Order.asc("designation_id"));
        ArrayList<Designation> designationsList = (ArrayList<Designation>)crit.list();

        return designationsList;
    }


    @Override
    public void updateDesignation(Designation designation) {
        update(designation);
    }

}
