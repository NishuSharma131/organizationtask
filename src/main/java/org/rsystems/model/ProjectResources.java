package org.rsystems.model;

import javax.persistence.*;

@Entity
@Table(name = "resource_project")
public class ProjectResources {
    @Column(name = "resource_allocate_id")
    int resource_allocate_id;
    @Column(name="project_allocate_id")
    int project_allocate_id;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "project_id")
    int id;
    @Column(name="allocation")
    int allocation;


    public int getResource_allocate_id() {
        return resource_allocate_id;
    }

    public void setResource_allocate_id(int resource_allocate_id) {
        this.resource_allocate_id = resource_allocate_id;
    }

    public int getProject_allocate_id() {
        return project_allocate_id;
    }

    public void setProject_allocate_id(int project_allocate_id) {
        this.project_allocate_id = project_allocate_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllocation() {
        return allocation;
    }

    public void setAllocation(int allocation) {
        this.allocation = allocation;
    }
}
