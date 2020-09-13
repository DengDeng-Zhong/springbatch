package cn.targetpath.springbatch.itemreader;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/7 23:08
 * @Version V1.0
 */
@Configuration
public class ItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderDemoJob(){
        return jobBuilderFactory.get("itemReaderDemoJob")
                .start(itemReaderDemoStep())
                .build();
    }

    @Bean
    public Step itemReaderDemoStep() {
        return stepBuilderFactory.get("itemReaderDemoStep")
                .<String,String>chunk(2)
                .reader(itemReaderDemoRead())
                .writer(list -> {
                    for (String item:list
                         ) {
                        System.out.println("..."+item+"...");
                    }
                })
                .build();
    }

    @Bean
    public MyReaader itemReaderDemoRead() {
        List<String> data = Arrays.asList("1","2","3","4","5");
        return new MyReaader(data);
    }
}
