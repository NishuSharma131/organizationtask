package org.rsystems.writer;

import org.rsystems.model.Project;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ProjectWriter extends FlatFileItemWriter<Project> {

    public ProjectWriter() {
        setResource(new FileSystemResource("c://data"));
        setLineAggregator(getDelimitedLineAggregator());
    }


    public DelimitedLineAggregator<Project> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<Project> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Project>();
        beanWrapperFieldExtractor.setNames(new String[] {"Project ID", "Project Name", "Allocated Capital","Allocation"});

        DelimitedLineAggregator<Project> delimitedLineAggregator = new DelimitedLineAggregator<Project>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }
}
