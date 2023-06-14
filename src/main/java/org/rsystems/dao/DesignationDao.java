package org.rsystems.dao;


import org.rsystems.model.Designation;

import java.util.ArrayList;

public interface DesignationDao {

    Designation getDesignationById(int designation_id);
    int saveNewDesignation(Designation designation);
    void deleteDesignation(Designation designation);
    ArrayList<Designation> getAllDesignation();
    void updateDesignation(Designation designation);

   }
