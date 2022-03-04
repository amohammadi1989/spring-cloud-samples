package com.example.batch;
import com.example.dto.TitlePrincipals;
import com.example.dto.TitlePrincipalsMongo;
import com.example.repository.TitlePrincipalsMongoRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
/**
 * Created By: Ali Mohammadi
 * Date: 01 Dec, 2021
 */


public class TitlePrincipalBatch {
  AtomicLong atomicLong=new AtomicLong(0);

  @Autowired
  StepBuilderFactory stepBuilderFactory;

  public Step stepPrincipal(){
    return  stepBuilderFactory.get( "stepPrincipal" )
    .<TitlePrincipals, TitlePrincipals>chunk( 8000 )
    .reader( reader() )
    .processor( processor() )
    .writer( writer() )
    .faultTolerant()
    .skipPolicy( new FileVerificationSkipper() )
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
  public ItemProcessor<TitlePrincipals,TitlePrincipals> processor(){
    return  new ItemProcessor<TitlePrincipals, TitlePrincipals>() {
      @Override
      public TitlePrincipals process(TitlePrincipals titlePrincipals) throws Exception {
        titlePrincipals.setJob( titlePrincipals.getJob().equals( "\\N" )? "":
                                titlePrincipals.getJob() );
        titlePrincipals.setCharacters( titlePrincipals.getCharacters().equals( "\\N" )?"":
                                       titlePrincipals.getCharacters() );
        titlePrincipals.setCharacters( titlePrincipals.getCharacters().replaceAll( "\\[\"","" ) );
        titlePrincipals.setCharacters( titlePrincipals.getCharacters().replaceAll( "\"\\]","" ) );
  
        return titlePrincipals;
      }
    };
  }
  
  
  
  

  public FlatFileItemReader<TitlePrincipals> reader() {

    FlatFileItemReader<TitlePrincipals> itemReader = new FlatFileItemReader<TitlePrincipals>();
    itemReader.setLineMapper(lineMapperTP());
    itemReader.setLinesToSkip(1);
    itemReader.setResource(new ClassPathResource( "principal.tsv" ) );
    return itemReader;
  }
  
  
  
  public LineMapper<TitlePrincipals> lineMapperTP() {
    DefaultLineMapper<TitlePrincipals> lineMapper = new DefaultLineMapper<TitlePrincipals>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer( DelimitedLineTokenizer.DELIMITER_TAB);
    lineTokenizer.setNames("tconst","ordering","nconst","category","job","characters");
    lineTokenizer.setIncludedFields(0, 1,2,3,4,5);
    BeanWrapperFieldSetMapper<TitlePrincipals> fieldSetMapper = new BeanWrapperFieldSetMapper<TitlePrincipals>();
    fieldSetMapper.setTargetType( TitlePrincipals.class);
    fieldSetMapper.setStrict( true );
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }
  

  public ItemWriter<TitlePrincipals> writer(){
    return new ItemWriter<TitlePrincipals>() {
      @Override
      public void write(List<? extends TitlePrincipals> list) throws Exception {
        System.out.println("writer....."+    atomicLong.addAndGet( list.size() ) +" -------------" +
                           " "+Thread.currentThread().getName());
        
      /*  list.stream().map( p-> {
          TitlePrincipalsMongo personEntity = new TitlePrincipalsMongo();
          personEntity.setJob( p.getJob() );
          personEntity.setCategory( p.getCategory() );
          personEntity.setNconst( p.getNconst() );
          personEntity.setOrdering( Integer.parseInt(p.getOrdering()) );
          personEntity.setTconst( p.getTconst() );
          personEntity.setCharacters( p.getCharacters() );
          return personEntity;
        } ).forEach( titlePrincipalsMongoRepository::save );*/
      }
    };
  }
}
