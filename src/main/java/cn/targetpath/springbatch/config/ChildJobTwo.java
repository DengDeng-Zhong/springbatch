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
 * 子Job2
 *
 * @author DengBo_Zhong
 * @Date 2020/9/4 22:44
 * @Version V1.0
 */
@Configuration
public class ChildJobTwo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job childJobT(){
        return jobBuilderFactory.get("childJobT")
                .start(childJobTwoStep2())
                .next(childJobTwoStep1())
                .build();
    }

    @Bean
    public Step childJobTwoStep2(){
        return stepBuilderFactory.get("childJobTwoStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJobTwoStep2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step childJobTwoStep1(){
        return stepBuilderFactory.get("childJobTwoStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("childJobTwoStep1");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
