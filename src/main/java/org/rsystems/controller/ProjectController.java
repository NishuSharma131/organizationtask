package org.rsystems.controller;


import org.rsystems.model.Organization;
import org.rsystems.model.Project;
import org.rsystems.response.Status;
import org.rsystems.response.WebServiceResponse;
import org.rsystems.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    Logger LOG = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    ProjectService projectService;



    @ResponseBody
    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public WebServiceResponse createNewProject(
           // @RequestParam(value = "project_id", defaultValue = "0") int project_id, @RequestParam(value = "current_project_id") int current_project_id,
    @RequestBody Project project) {
        WebServiceResponse response = new WebServiceResponse();
        Project newProject = new Project();
        newProject.setProject_name(project.getProject_name());
        newProject.setAllocated_capital(project.getAllocated_capital());
       // newProject.setResource_id(project.getResource_id());
        //newProject.setUsed_capital(project.getUsed_capital());//need to add it on runtime
        try {
            int id = projectService.saveNewProject(newProject);
            //  newProject.setOrganization_id(organization_id);
            response.setInfo(newProject);
            response.setStatus(Status.SUCCESS);
            response.setMessage("Organization with"+id+" is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.setInfo(newProject);
            response.setStatus(Status.FAIL);
            response.setMessage("Organization is not saved.\n\n" + e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    public WebServiceResponse updateProject(@RequestBody Project project) {
        WebServiceResponse response = new WebServiceResponse();
        try {
            Project projectFromDB = projectService.getProjectById(project.getProject_id());
            if (project != null) {
                if (projectFromDB != null ) {
                    response.setMessage("Project id is not available. Please try other unique id.");
                    response.setStatus(Status.FAIL);
                    return response;
                }
            }
            projectService.updateProject(projectFromDB);
            response.setMessage("Project status is updated successfully.");
            response.setInfo(projectFromDB);
            response.setStatus(Status.SUCCESS);
        } catch (Exception ex) {
            response.setMessage("Project details not updated");
            response.setStatus(Status.FAIL);
            response.setInfo(ex.getStackTrace());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteProject", method = RequestMethod.POST)
    public WebServiceResponse deleteProject(@RequestParam("project_id") int project_id) {
        WebServiceResponse webServiceResponse = new WebServiceResponse();
        Project deleteProject = projectService.getProjectById(project_id);
        if (deleteProject != null) {
            projectService.deleteProject(deleteProject);
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Project is deleted successfully.");
        } else {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Project not found.");
        }
        return webServiceResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/listProjects", method= RequestMethod.GET)
    public WebServiceResponse listAllProjects()
    {
        WebServiceResponse webServiceResponse=new WebServiceResponse();
        Project Project = new Project();

        List<Project> projectList=projectService.getAllProject();
        if(!projectList.isEmpty()) {
            webServiceResponse.setStatus(Status.SUCCESS);
            webServiceResponse.setMessage("Project Details found.");
            webServiceResponse.setInfo(projectList);
            return webServiceResponse;
        }

        return null;
    }



}
