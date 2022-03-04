package com.example.batch;
import com.example.dto.TitleBasicsDto;
import com.example.dto.TitleBasicsMongo;
import com.example.dto.TitlePrincipals;
import com.example.repository.TitleBasicsMongoRepository;
import com.example.repository.TitleBasicsRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
/**
 * Created By: Ali Mohammadi
 * Date: 01 Dec, 2021
 */

@Service
public class TitleBasicsBatch {
  AtomicLong atomicLong=new AtomicLong(0);

  @Autowired
  StepBuilderFactory stepBuilderFactory;
  @Autowired
  TitleBasicsRepository titleBasicsRepository;

  public Step stepTitleBasics(){
    return  stepBuilderFactory.get( "stepTitleBasic" )
    .<TitleBasicsDto, TitleBasicsMongo>chunk( 800 )
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
  public ItemProcessor<TitleBasicsDto, TitleBasicsMongo> processor(){
    return  new ItemProcessor<TitleBasicsDto, TitleBasicsMongo>() {
      @Override
      public TitleBasicsMongo process(TitleBasicsDto dto) throws Exception {
        TitleBasicsMongo mo=new TitleBasicsMongo();
        
        mo.setTitleType( dto.getTitleType() );
        mo.setEndYear( dto.getEndYear().replaceAll( "\\\\N","" ) );
        mo.setGenres( Arrays.asList(dto.getGenres().split( "," )) );
        mo.setOriginalTitle( dto.getOriginalTitle() );
        mo.setPrimaryTitle( dto.getPrimaryTitle() );
        mo.setIsAdult( dto.getIsAdult() );
        mo.setStartYear( dto.getStartYear().replaceAll( "\\\\N","" ) );
        mo.setTconst( dto.getTconst() );
  
       // System.out.println(mo.toString());
        
        return mo;
      }
    };
  }
  
  
  
  

  public FlatFileItemReader<TitleBasicsDto> reader() {

    FlatFileItemReader<TitleBasicsDto> itemReader = new FlatFileItemReader<TitleBasicsDto>();
    itemReader.setLineMapper(lineMapperTP());
    itemReader.setLinesToSkip(1);
    itemReader.setResource(new ClassPathResource( "titleBasic.tsv" ) );
    return itemReader;
  }
  
  public LineMapper<TitleBasicsDto> lineMapperTP() {
    DefaultLineMapper<TitleBasicsDto> lineMapper = new DefaultLineMapper<TitleBasicsDto>();
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer( DelimitedLineTokenizer.DELIMITER_TAB);
    lineTokenizer.setNames("tconst","titleType","primaryTitle","originalTitle","isAdult",
                           "startYear","endYear","genres");
    lineTokenizer.setIncludedFields(0, 1,2,3,4,5,6,7);
    BeanWrapperFieldSetMapper<TitleBasicsDto> fieldSetMapper =
    new BeanWrapperFieldSetMapper<TitleBasicsDto>();
    fieldSetMapper.setTargetType( TitleBasicsDto.class);
    fieldSetMapper.setStrict( true );
    lineMapper.setLineTokenizer(lineTokenizer);
    lineMapper.setFieldSetMapper(fieldSetMapper);
    return lineMapper;
  }
  

  public ItemWriter<TitleBasicsMongo> writer(){
    return new ItemWriter<TitleBasicsMongo>() {
      @Override
      public void write(List<? extends TitleBasicsMongo> list) throws Exception {
        System.out.println("writer....."+    atomicLong.addAndGet( list.size() ) +" -------------" +
                           " "+Thread.currentThread().getName());
        
         titleBasicsRepository.saveAll(list);
      }
    };
  }
}
