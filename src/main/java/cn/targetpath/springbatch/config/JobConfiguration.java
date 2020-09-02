package cn.targetpath.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化
 *
 * @author DengBo_Zhong
 * @Date 2020/8/29 0:05
 * @Version V1.0
 */
//@Configuration
//@EnableBatchProcessing
public class JobConfiguration {

    /**
     * 注入创建任务对象的对象
      */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** 创建的任务由step决定
    * 注入创建Step对象的对象
     */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建任务对象
     */
    @Bean
    public Job helloworld(){
        return jobBuilderFactory.get("helloworld")
                .start(step1())
                .build();
    }

    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("helloworld");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
