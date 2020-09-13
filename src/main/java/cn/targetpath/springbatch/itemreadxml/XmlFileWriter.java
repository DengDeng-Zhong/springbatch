package cn.targetpath.springbatch.itemreadxml;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/13 23:54
 * @Version V1.0
 */
@Component("xmlFileWriter")
public class XmlFileWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        for (Customer customer: list) {
            System.out.println(customer);
        }
    }
}
