package org.rsystems.processors;

import org.rsystems.model.Project;
import org.rsystems.model.Resource;
import org.springframework.batch.item.ItemProcessor;

public class ProjectItemProcessor implements ItemProcessor<Project, Project> {


    @Override
    public Project process(Project project) throws Exception {
        return project;
    }
}
