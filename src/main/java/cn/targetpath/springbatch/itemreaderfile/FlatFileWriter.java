package cn.targetpath.springbatch.itemreaderfile;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取数据
 *
 * @author DengBo_Zhong
 * @Date 2020/9/13 19:39
 * @Version V1.0
 */
@Component("flatFileWriter")
public class FlatFileWriter implements ItemWriter<Customer> {
    int num = 0;
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        for (Customer customer: list) {
            num++;
            System.out.println(num + ":"+ customer);
        }
    }
}
