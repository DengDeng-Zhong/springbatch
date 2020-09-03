package cn.targetpath.springbatch.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/3 22:59
 * @Version V1.0
 */
public class MyDecider implements JobExecutionDecider {
    private int count = 0;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if (count%2 == 0) {
            return new FlowExecutionStatus("even");
        } else {
            return new FlowExecutionStatus("odd");
        }
    }
}
