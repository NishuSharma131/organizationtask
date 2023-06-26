package org.rsystems.controller;


import org.rsystems.enums.ResourceAllocation;
import org.rsystems.model.Resource;
import org.rsystems.response.Status;
import org.rsystems.response.WebServiceResponse;
import org.rsystems.services.ProjectResourceService;
import org.rsystems.services.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/resource")
public class ResourceController {
    Logger LOG = LoggerFactory.getLogger(ResourceController.class);
    @Autowired
    ResourceService resourceService;

    @Autowired
    ProjectResourceService projectResourceService;


    @ResponseBody
    @RequestMapping(value = "/createResource", method = RequestMethod.POST)
    public WebServiceResponse createNewResource( @RequestBody Resource resource) {
        WebServiceResponse response = new WebServiceResponse();

        Resource newResource = new Resource();
        newResource.setResource_name(resource.getResource_name());
        newResource.setDesignation_id(resource.getDesignation_id());
        try {
            int id = resourceService.saveNewResource(newResource);
            response.setInfo(newResource);
            response.setStatus(Status.SUCCESS);
            response.setMessage("Resource with "+id+" is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setInfo(newResource);
            response.setStatus(Status.FAIL);
            response.setMessage("Resource is not saved.\n\n" + e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/updateResource", method = RequestMethod.POST)
    public WebServiceResponse updateResource(@RequestBody Resource resource) {
        WebServiceResponse response = new WebServiceResponse();
        try {
             if (resource != null) {
                 Resource resourceFromDB = resourceService.getResourceById(resource.getResource_id());

                 if (resourceFromDB == null ) {
                    response.setMessage("Resource id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
                }
            }
            resourceService.updateResource(resource);
            response.setMessage("Resource status is updated successfully.");
            response.setInfo(resource);
            response.setStatus(Status.SUCCESS);
        } catch (Exception ex) {
            response.setMessage("Resource details not updated");
            response.setStatus(Status.FAIL);
            response.setInfo(ex.getStackTrace());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteResource", method = RequestMethod.POST)
    public WebServiceResponse deleteResource(@RequestParam("resource_id") int resource_id) {
        WebServiceResponse webServiceResponse = new WebServiceResponse();
        Resource deleteResource = resourceService.getResourceById(resource_id);
        if (deleteResource != null) {
            resourceService.deleteResource(deleteResource);
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Resource is deleted successfully.");
        } else {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Resource not found.");
        }
        return webServiceResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/listResources", method= RequestMethod.GET)
    public WebServiceResponse listAllResources()
    {
        WebServiceResponse webServiceResponse=new WebServiceResponse();
        Resource Resource = new Resource();

        List<Resource> resourceList=resourceService.getAllResources();
        if(!resourceList.isEmpty()) {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Resource Details found.");
            webServiceResponse.setInfo(resourceList);
            return webServiceResponse;
        }

        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/updateProjectResource", method = RequestMethod.POST)
    public WebServiceResponse updateProjectResource(@RequestBody Resource resource) {
        WebServiceResponse response = new WebServiceResponse();
        try {
            Resource resourceFromDB = resourceService.getResourceById(resource.getResource_id());
           int allocationValue= projectResourceService.getResourceAllocation(resource.getResource_id());
            if (resourceFromDB == null) {

                    response.setMessage("Resource id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
            } else if (allocationValue== ResourceAllocation.Full) {

                response.setMessage("Resource is fully allocated");
                response.setStatus(Status.FAIL);
                return response;
            }
            resourceService.updateResource(resource);
            response.setMessage("Resource status is updated successfully.");
            response.setInfo(resourceFromDB);
            response.setStatus(Status.SUCCESS);
        } catch (Exception ex) {
            response.setMessage("Resource details not updated");
            response.setStatus(Status.FAIL);
            response.setInfo(ex.getStackTrace());
        }
        return response;
    }

}
