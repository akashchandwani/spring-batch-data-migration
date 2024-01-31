package com.akashchandwani.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobConfiguration {

    @Bean
    public Job footballJob(JobRepository jobRepository) {
        return new JobBuilder("footballJob", jobRepository)
                .start(sampleStep(jobRepository))
                .build();
    }

    @Bean
    public Step sampleStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager) {
        return new StepBuilder("sampleStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
//                .reader(itemReader())
//                .writer(itemWriter())
                .builder();
    }
}
