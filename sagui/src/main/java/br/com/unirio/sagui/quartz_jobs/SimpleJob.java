package br.com.unirio.sagui.pdfjobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.scheduling.quartz.QuartzJobBean;


@Slf4j
public class SimpleJob extends QuartzJobBean {
    
	
	@Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
	
		System.out.println("LOLl");
    	
 
        log.info("SimpleJob End................");
    }
}
