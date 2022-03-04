
package com.example.batch;

import com.example.dto.TitlePrincipals;
import com.example.dto.TitlePrincipalsEntity;
import com.example.dto.TitlePrincipalsMongo;
import com.example.repository.PersonRepository;
import com.example.repository.TitlePrincipalsMongoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created By: Ali Mohammadi
 * Date: 29 Nov, 2021
 */

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class BatchScheduling {
  
  @Autowired
  JobBuilderFactory jobBuilderFactory;
  
 //@Autowired
  PersonRepository personRepository;
  
  @Autowired
  JobLauncher jobLauncher;
  
  
  @Autowired
  TitleBasicsBatch titleBasicsBatch;
  @Scheduled( initialDelay = 300,fixedDelay= 2000000000)
  public void launchJob() throws Exception{
  
    try {
      JobExecution jobExecution=jobLauncher.run( jobBatch(), new JobParametersBuilder().addDate(
      "launchDate", new Date())
      .toJobParameters() );
    } finally {
    
    }
  
  }
  @Bean("jobBatch")
  public Job jobBatch() {
    return jobBuilderFactory
    .get("jobBatch")
    //.start(titlePrincipalBatch.stepPrincipal())
    .start( titleBasicsBatch.stepTitleBasics() )
    .build();
  }

}

