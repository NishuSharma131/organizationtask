package org.rsystems.controller;


import org.rsystems.enums.ResourceAllocation;
import org.rsystems.model.Project;
import org.rsystems.model.ProjectResources;
import org.rsystems.model.Resource;
import org.rsystems.model.Resource;
import org.rsystems.response.Status;
import org.rsystems.response.WebServiceResponse;
import org.rsystems.services.ProjectResourceService;
import org.rsystems.services.ProjectService;
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
    ProjectService projectService;

    @Autowired
    ProjectResourceService projectResourceService;



    @ResponseBody
    @RequestMapping(value = "/createResource", method = RequestMethod.POST)
    public WebServiceResponse createNewResource(@RequestParam(value="project_id",required = true) int project_id,
                                              @RequestParam(value = "allocation") int allocation, @RequestBody Resource resource) {
        WebServiceResponse response = new WebServiceResponse();
        Project project=projectService.getProjectById(project_id);
        if(project==null){
            response.setMessage("Project id is not available. Please try other unique id.");
            response.setStatus(Status.FAIL);
            return response;
        } else if (allocation!=ResourceAllocation.Full||allocation!=ResourceAllocation.HALF) {
            response.setMessage("Resource allocation should be 50 or 100 .");
            response.setStatus(Status.FAIL);
            return response;
        }
        Resource newResource = new Resource();
        newResource.setResource_name(resource.getResource_name());
        //newResource.setProject_id(resource.getProject_id());
        newResource.setDesignation_id(resource.getDesignation_id());
        try {
            int id = resourceService.saveNewResource(newResource);
           ProjectResources projectResources=new ProjectResources();
           projectResources.setResource_allocate_id(id);
           projectResources.setProject_allocate_id(project_id);
            projectResources.setAllocation(allocation);
            updateProjectResource(projectResources);
            response.setInfo(newResource);
            response.setStatus(Status.SUCCESS);
            response.setMessage("Resource with "+id+" is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setInfo(newResource);
            response.setStatus(Status.FAIL);
            response.setMessage("Organization is not saved.\n\n" + e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/updateResource", method = RequestMethod.POST)
    public WebServiceResponse updateResource(@RequestBody Resource resource) {
        WebServiceResponse response = new WebServiceResponse();
        try {
            Resource resourceFromDB = resourceService.getResourceById(resource.getResource_id());
            if (resource != null) {
                if (resourceFromDB != null ) {
                    response.setMessage("Resource id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
                }
            }
            resourceService.updateResource(resourceFromDB);
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
    public WebServiceResponse updateProjectResource(@RequestBody ProjectResources projectResources) {
        WebServiceResponse response = new WebServiceResponse();
        try {
            ProjectResources resourceFromDB = projectResourceService.getResourceById(projectResources.getResource_allocate_id());
           int allocationValue=projectResourceService.getResourceAllocation(projectResources.getResource_allocate_id());
            if (resourceFromDB == null) {

                    response.setMessage("Resource id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
            } else if (allocationValue== ResourceAllocation.Full) {

                response.setMessage("Resource is fully allocated");
                response.setStatus(Status.FAIL);
                return response;
            }
            projectResourceService.saveAndUpdateProjectResources(resourceFromDB);
            response.setMessage("ProjectResources status is updated successfully.");
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
