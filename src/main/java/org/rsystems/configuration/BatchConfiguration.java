package org.rsystems.configuration;

import org.hibernate.SessionFactory;
import org.rsystems.model.Project;
import org.rsystems.reader.ProjectReader;
import org.rsystems.writer.ProjectWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    ProjectReader projectReader;

    @Autowired
    ProjectWriter projectWriter;


    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("ProjectJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("ProjectStep").<Project,Project>chunk(1).
                reader(projectReader).writer(projectWriter).build();
    }
}
