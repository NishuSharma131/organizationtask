package org.rsystems.controller;


import org.rsystems.model.Designation;
import org.rsystems.response.Status;
import org.rsystems.response.WebServiceResponse;
import org.rsystems.services.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/designation")
public class DesignationController {

    @Autowired
    DesignationService designationService;


    @ResponseBody
    @RequestMapping(value = "/createDesignation", method = RequestMethod.POST)
    public WebServiceResponse createNewDesignation(
            //@RequestParam(value = "designation_id", defaultValue = "0") int designation_id, @RequestParam(value = "current_designation_id") int current_designation_id,
                                            @RequestBody Designation designation) {
        WebServiceResponse response = new WebServiceResponse();
       /* Designation existedDesignation = designationService.findByDesignationId(current_designation_id);
        if (existedDesignation == null) {
            response.setMessage("Designation Not Found.");
            response.setStatus(Status.FAIL);
            return response;
        }
*/
        Designation newDesignation = new Designation();
        newDesignation.setDesignation_name(designation.getDesignation_name());
        newDesignation.setCapital(designation.getCapital());
        newDesignation.setLast_modified(new Date());
        try {
            int id = designationService.saveNewDesignation(newDesignation);
           // newDesignation.setDesignation_id(designation_id);
            response.setInfo(newDesignation);
            response.setStatus(Status.SUCCESS);
            response.setMessage("Designation is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setInfo(newDesignation);
            response.setStatus(Status.FAIL);
            response.setMessage("Designation is not saved.\n\n" + e.getMessage());
        }

        return response;
    }
    
    @ResponseBody
    @RequestMapping(value = "/updateDesignation", method = RequestMethod.PUT)
    public WebServiceResponse updateDesignation(@RequestBody Designation designation) {
        WebServiceResponse response = new WebServiceResponse();
        //Designation designationFromDB=null;
        try {if (designation != null) {
            Designation designationFromDB= designationService.findByDesignationId(designation.getDesignation_id());

                if (designationFromDB == null ) {
                          response.setMessage("Designation id is not available. Please try other unique id.");
                            response.setStatus(Status.FAIL);
                            return response;
                        }
                }
            designationService.updateDesignation(designation);
            response.setMessage("Designation status is updated successfully.");
            response.setInfo(designation);
            response.setStatus(Status.SUCCESS);
        } catch (Exception ex) {
            response.setMessage("Designation details not updated"+ex);
            response.setStatus(Status.FAIL);
            //response.setInfo(ex.getStackTrace());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDesignation", method = RequestMethod.POST)
    public WebServiceResponse deleteDesignation(@RequestParam("designation_id") int designation_id) {
        WebServiceResponse webServiceResponse = new WebServiceResponse();
        Designation deleteDesignation = designationService.findByDesignationId(designation_id);
        if (deleteDesignation != null) {
            designationService.deleteDesignation(deleteDesignation);
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Designation is deleted successfully.");
        } else {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Designation not found.");
        }
        return webServiceResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/listDesignations", method= RequestMethod.GET)
    public WebServiceResponse listAllDesignations()
    {
        WebServiceResponse webServiceResponse=new WebServiceResponse();
            Designation Designation = new Designation();

            List<Designation> designationList=designationService.getAllDesignation();
            if(!designationList.isEmpty()) {
                webServiceResponse.setStatus(Status.SUCCESS);
                webServiceResponse.setMessage("Designation Details found.");
                webServiceResponse.setInfo(designationList);
                return webServiceResponse;
            }

        return null;
    }



}
