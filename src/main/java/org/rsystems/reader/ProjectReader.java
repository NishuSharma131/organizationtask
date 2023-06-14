package org.rsystems.reader;

import org.rsystems.configuration.HibernateConfiguration;
import org.rsystems.model.Project;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ProjectReader extends JdbcCursorItemReader<Project> implements ItemReader<Project> {

    @Autowired
    HibernateConfiguration hibernateConfiguration;

    public ProjectReader() {
        //setDataSource(hibernateConfiguration.dataSource());
        setSql("SELECT id, name, salary FROM Project");
        setFetchSize(100);
        setRowMapper(new ProjectRowMapper());
    }

    public class ProjectRowMapper implements RowMapper<Project> {
        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
            Double userCapital=0.0;
            Project project  = new Project();
            project.setProject_id(rs.getInt("project_id"));
            project.setProject_name(rs.getString("project_name"));
            project.setAllocated_capital(rs.getDouble("allocated_capital"));
            project.setUsedCapital(userCapital);
            return project;
        }


    }

}
