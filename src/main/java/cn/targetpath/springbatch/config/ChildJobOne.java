package cn.targetpath.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 子Job1
 *
 * @author DengBo_Zhong
 * @Date 2020/9/4 22:35
 * @Version V1.0
 */
@Configuration
public class ChildJobOne {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job childJobO(){
        return jobBuilderFactory.get("childJobO")
                .start(childJobOneStep1())
                .build();
    }

    @Bean
    public Step childJobOneStep1(){
        return stepBuilderFactory.get("childJobOneStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJobOneStep1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
