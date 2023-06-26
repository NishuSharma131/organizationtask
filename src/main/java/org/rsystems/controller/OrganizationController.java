package org.rsystems.controller;


import org.rsystems.model.Organization;
import org.rsystems.response.Status;
import org.rsystems.response.WebServiceResponse;
import org.rsystems.services.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    Logger LOG = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    OrganizationService organizationService;



    @ResponseBody
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public WebServiceResponse createNewOrganization(
            //@RequestParam(value = "organization_id", defaultValue = "0") int organization_id, @RequestParam(value = "current_organization_id") int current_organization_id,
                                                   @RequestBody Organization organization) {
        WebServiceResponse response = new WebServiceResponse();
            Organization newOrganization = new Organization();
            newOrganization.setOrg_name(organization.getOrg_name());
            newOrganization.setTotal_cost(organization.getTotal_cost());
            newOrganization.setProject_id(organization.getProject_id());
            try {
                int id = organizationService.saveNewOrganization(newOrganization);
                response.setInfo(newOrganization);
                response.setStatus(Status.SUCCESS);
                response.setMessage("Organization with"+id+" is created successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                response.setInfo(newOrganization);
                response.setStatus(Status.FAIL);
                response.setMessage("Organization is not saved.\n\n" + e.getMessage());
            }
              return response;
    }

    @ResponseBody
    @RequestMapping(value = "/updateOrganization", method = RequestMethod.PUT)
    public WebServiceResponse updateOrganization(@RequestBody Organization organization) {
        WebServiceResponse response = new WebServiceResponse();
        try {
            if (organization != null) {
            Organization organizationFromDB = organizationService.getOrganizationById(organization.getOrg_id());

                if (organizationFromDB == null ) {
                    response.setMessage("Organization id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
                }
            }
            organizationService.updateOrganization(organization);
            response.setMessage("Organization status is updated successfully.");
            response.setInfo(organization);
            response.setStatus(Status.SUCCESS);
        } catch (Exception ex) {
            response.setMessage("Organization details not updated");
            response.setStatus(Status.FAIL);
            response.setInfo(ex.getStackTrace());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.POST)
    public WebServiceResponse deleteOrganization(@RequestParam("organization_id") int organization_id) {
        WebServiceResponse webServiceResponse = new WebServiceResponse();
        Organization deleteOrganization = organizationService.getOrganizationById(organization_id);
        if (deleteOrganization != null) {
            organizationService.deleteOrganization(deleteOrganization);
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Organization is deleted successfully.");
        } else {
            webServiceResponse.setStatus(Status.FAIL);
            webServiceResponse.setMessage("Organization not found.");
        }
        return webServiceResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/listOrganizations", method= RequestMethod.GET)
    public WebServiceResponse listAllOrganizations()
    {
        WebServiceResponse webServiceResponse=new WebServiceResponse();
        Organization Organization = new Organization();

        List<Organization> organizationList=organizationService.getAllOrganization();
        if(!organizationList.isEmpty()) {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Organization Details found.");
            webServiceResponse.setInfo(organizationList);
            return webServiceResponse;
        }

        return null;
    }


}
