
package com.example.batch;

import com.example.dto.NameBasicsDto;
import com.example.repository.NameBasicsReactiveRepository;
import com.example.repository.PersonRepository;
import org.springframework.batch.core.Step;
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
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created By: Ali Mohammadi
 * Date: 29 Nov, 2021
 */


public class NameBasicsBatch {
  
  @Autowired
  JobBuilderFactory jobBuilderFactory;
  
  AtomicLong atomicLong=new AtomicLong(0);
  
  @Autowired
  StepBuilderFactory stepBuilderFactory;
  @Autowired
  PersonRepository personRepository;
  
  @Autowired
  JobLauncher jobLauncher;
  //@Autowired
  NameBasicsReactiveRepository nameBasicsReactiveRepository;

  
  public Step stepName(){
    return  stepBuilderFactory.get( "stepName" )
    .<NameBasicsDto, NameBasicsDto>chunk( 10000 )
    .reader( readerName() )
    .processor( new ItemProcessor<NameBasicsDto, NameBasicsDto>() {
      @Override
      public NameBasicsDto process(NameBasicsDto nameBasicsDto) throws Exception {
        return nameBasicsDto;
      }
    } )
    .writer( writerName() )
    .faultTolerant().skipPolicy( new FileVerificationSkipper() )
    .taskExecutor( taskExecutor() )
    .build();
  }
  
  public TaskExecutor taskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(4);
    taskExecutor.afterPropertiesSet();
    taskExecutor.setCorePoolSize(20);
    taskExecutor.setQueueCapacity(8);
    return taskExecutor;
  }
  @Bean
  public FlatFileItemReader<NameBasicsDto> readerName() {
    FlatFileItemReader<NameBasicsDto> itemReader = new FlatFileItemReader<NameBasicsDto>();
    itemReader.setLineMapper(lineName());
    itemReader.setLinesToSkip(1);
    itemReader.setResource(new ClassPathResource( "datas.tsv" ) );
    return itemReader;
  }
  
  @Bean
  public LineMapper<NameBasicsDto> lineName() {
    DefaultLineMapper<NameBasicsDto> lineMapper = new DefaultLineMapper<NameBasicsDto>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer( DelimitedLineTokenizer.DELIMITER_TAB);
    lineTokenizer.setNames("nconst","primaryName","birthYear","deathYear","primaryProfession",
                           "knownForTitles");
    lineTokenizer.setIncludedFields(0,1,2,3,4,5);
    BeanWrapperFieldSetMapper<NameBasicsDto> fieldSetMapper = new BeanWrapperFieldSetMapper<NameBasicsDto>();
    fieldSetMapper.setStrict( true );
    fieldSetMapper.setTargetType( NameBasicsDto.class);
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }
  
  
  
@Bean
  public ItemWriter<NameBasicsDto> writerName(){
    return new ItemWriter<NameBasicsDto>() {
      @Override
      public void write(List<? extends NameBasicsDto> list) throws Exception {
        System.out.println("writer.....>"+atomicLong.addAndGet( list.size() )+"===>"+Thread.currentThread().getName());
        
        /*list.stream().map( p-> {
          NameBasicsMongo nameBasicsMongo = new NameBasicsMongo();
          nameBasicsMongo.setNconst( p.getNconst() );
          nameBasicsMongo.setPrimaryName( p.getPrimaryName().equals( "\\N" )?"":p.getPrimaryName() );
          nameBasicsMongo.setBirthYear( p.getBirthYear().equals( "\\N" )?"":p.getBirthYear() );
          nameBasicsMongo.setDeathYear( p.getDeathYear().equals( "\\N" )?"":p.getDeathYear() );
          
  
          if(p.getKnownForTitles()!=null && !p.getKnownForTitles().equals( "\\N" ))
            nameBasicsMongo.setKnownForTitles( Arrays.asList(p.getKnownForTitles().split( "," )) );
          else
            nameBasicsMongo.setKnownForTitles( new ArrayList<>() );
          if(p.getPrimaryProfession()!=null && !p.getPrimaryProfession().equals( "\\N" ))
            nameBasicsMongo.setPrimaryProfession(  Arrays.asList(p.getPrimaryProfession().split( "," )));
          else
            nameBasicsMongo.setPrimaryProfession( new ArrayList<>() );
            
            
            
          return nameBasicsMongo;
        } ).forEach( nameBasicsRepository::save );*/
      }
    };
  }
}
