package cn.targetpath.springbatch.itemreadxml;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/13 23:32
 * @Version V1.0
 */
@Configuration
public class XmlItemReaderDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    @Qualifier("xmlFileWriter")
    private ItemWriter<? super Customer> xmlFileWriter;

    @Bean
    public Job xmlItemReaderDemoJob(){
        return jobBuilderFactory.get("xmlItemReaderDemoJob")
                .start(xmlItemReaderDemoStep())
                .build();
    }

    @Bean
    public Step xmlItemReaderDemoStep() {
        return stepBuilderFactory.get("XmlItemReaderDemoStep")
                .<Customer,Customer>chunk(10)
                .reader(xmlFileReader())
                .writer(xmlFileWriter)
                .build();
}

    @Bean
    @StepScope
    public StaxEventItemReader<Customer> xmlFileReader() {
        StaxEventItemReader<Customer> reader = new StaxEventItemReader<Customer>();
        reader.setResource(new ClassPathResource("customer.xml"));

        // 指定需要处理的标签
        reader.setFragmentRootElementName("customer");
        // 把读取到的xml转成实体对象
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        Map<String,Class> map = new HashMap<>();
        map.put("customer", Customer.class);
        unmarshaller.setAliases(map);

        reader.setUnmarshaller(unmarshaller);
        return reader;
    }
}
