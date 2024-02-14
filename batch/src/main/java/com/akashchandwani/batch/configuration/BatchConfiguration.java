package com.akashchandwani.batch.configuration;

import com.akashchandwani.batch.model.Person;
import com.akashchandwani.batch.model.mappers.EmployeeProfileRowMapper;
import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class BatchConfiguration {

    @Bean
    public JdbcCursorItemReader<Person> itemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Person>()
                .dataSource(dataSource)
                .name("EmployeeProfileReader")
                .sql("""
                        select
                            p.id,
                            p.first_name,
                            p.last_name,
                            p.email,
                            jh.start_date,
                            jh.end_date,
                            jh.job_title,
                            c.name as company_name,
                            c.industry,
                            c.address as company_address
                        from person p
                        left join job_history jh on jh.person_id = p.id
                        left join company c on c.id = jh.company_id;
                        """)
                .rowMapper(new EmployeeProfileRowMapper())
                .build();
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "test");
        MappingMongoConverter conv = (MappingMongoConverter) mongoTemplate.getConverter();
        conv.afterPropertiesSet();
        return mongoTemplate;
    }

    @Bean
    public MongoItemWriter<Person> mongoUpsertWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Person>()
                .mode(MongoItemWriter.Mode.UPSERT)
                .template(mongoTemplate)
                .collection("employee-profiles")
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("postgresToMongoDbLoad", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      JdbcCursorItemReader<Person> reader, MongoItemWriter<Person> mongoUpsertWriter) {
        return new StepBuilder("step1", jobRepository)
                .<Person, Person> chunk(3, transactionManager)
                .reader(reader)
                .writer(mongoUpsertWriter)
                .build();
    }
}
