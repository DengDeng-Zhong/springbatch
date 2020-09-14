package cn.targetpath.springbatch.itemreadermulti;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/14 15:35
 * @Version V1.0
 */
@Component("multiFileWriter")
public class MultiFileWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        for (Customer c :
                list) {
            System.out.println(c);
        }
    }
}
