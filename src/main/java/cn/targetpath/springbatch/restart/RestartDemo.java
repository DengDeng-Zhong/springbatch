package cn.targetpath.springbatch.restart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/14 16:15
 * @Version V1.0
 */
@Configuration
public class RestartDemo {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("restartReader")
    private ItemReader<? extends Customer> restartReader;
    @Autowired
    @Qualifier("restartWriter")
    private ItemWriter<? super Customer> restartWriter;

    @Bean
    public Job restartDemoJob(){
        return jobBuilderFactory.get("restartDemoJob")
                .start(restartDemoStep())
                .build();
    }

    @Bean
    public Step restartDemoStep() {
        return stepBuilderFactory.get("restartDemoStep")
                .<Customer,Customer>chunk(10)
                .reader(restartReader)
                .writer(restartWriter)
                .build();
    }
}
