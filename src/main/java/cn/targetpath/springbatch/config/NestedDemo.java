package cn.targetpath.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 父Job
 * 启动须知:需要在application.properties中配置启动 spring.batch.job.names=parentJob
 * @author DengBo_Zhong
 * @Date 2020/9/4 22:48
 * @Version V1.0
 */
//@Configuration
public class NestedDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Job childJobO;
    @Autowired
    private Job childJobT;
    @Autowired
    private JobLauncher launcher;

    @Bean
    public Job parentJob(JobRepository repository, PlatformTransactionManager transactionManager){
        return jobBuilderFactory.get("parentJob")
                .start(childJob1(repository,transactionManager))
                .next(childJob2(repository, transactionManager))
                .build();
    }

    /**
     *
     * @return Job类型的Step,特殊的step
     */
    private Step childJob2(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(childJobT)
                .launcher(launcher)  // 使用父job的启动对象
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }

    private Step childJob1(JobRepository repository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJobO)
                .launcher(launcher)
                .repository(repository)
                .transactionManager(transactionManager)
                .build();
    }
}
