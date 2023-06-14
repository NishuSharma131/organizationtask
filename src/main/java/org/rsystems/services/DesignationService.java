package org.rsystems.services;

import org.rsystems.model.Designation;

import java.util.ArrayList;

public interface DesignationService {


    int saveNewDesignation(Designation designation);


    void deleteDesignation(Designation designation);

    ArrayList<Designation> getAllDesignation();

    Designation  getDesignationById(int designation_id);

    void updateDesignation(Designation designation);

    Designation findByUserId(int currentDesignationId);

    Designation findByDesignationId(int designationId);
}
