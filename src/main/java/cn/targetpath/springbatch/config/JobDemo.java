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
 * 三个step的job
 *
 * @author DengBo_Zhong
 * @Date 2020/9/2 20:59
 * @Version V1.0
 */
//@Configuration
//@EnableBatchProcessing
public class JobDemo {

    @Autowired
    private StepBuilderFactory step;

    @Autowired
    private JobBuilderFactory job;

    /**
     * 方法一 顺序执行
     * @return
     */
    public Job jobDemoJobOne(){
        return job.get("jobDemoJob")
                .start(Step1())
                .next(Step2())
                .next(Step3())
                .build();
    }

    /**
     * 方法二 按条件执行
     * @return
     */
    @Bean
    public Job jobDemoJobTwo(){
        return job.get("jobDemoJobTwo")
                .start(Step1())
                .on("COMPLETED").to(Step3())
                .from(Step3()).on("COMPLETED").to(Step2())  // failed() stepAndRestarted()
                .from(Step2()).end()
                .build();
    }

    public Step Step3() {
        return step.get("step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step3执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public Step Step2() {
        return step.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step2执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    public Step Step1() {
        return step.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step1执行");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
