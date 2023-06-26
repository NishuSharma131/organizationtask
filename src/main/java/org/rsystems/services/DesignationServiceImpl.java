package org.rsystems.services;


import org.rsystems.dao.DesignationDao;
import org.rsystems.model.Designation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
@Transactional
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    DesignationDao designationDao;

    @Override
    public int saveNewDesignation(Designation designation) {
        return designationDao.saveNewDesignation(designation);
    }

    @Override
    public void deleteDesignation(Designation designation) {
        designationDao.deleteDesignation(designation);
    }


    @Override
    public ArrayList<Designation> getAllDesignation() {
        return designationDao.getAllDesignation();
    }

    @Override
    public Designation getDesignationById(int designationId) {
        return designationDao.getDesignationById(designationId);
    }

    @Override
    public void updateDesignation(Designation designation) {
    designationDao.updateDesignation(designation);
    }

    @Override
    public Designation findByUserId(int currentDesignationId) {
        return null;
    }

    @Override
    public Designation findByDesignationId(int id) {
        Designation designation = designationDao.getDesignationById(id);
        System.out.println(designation);
        return designation;
    }


}
