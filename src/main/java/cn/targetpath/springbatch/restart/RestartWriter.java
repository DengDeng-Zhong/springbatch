package cn.targetpath.springbatch.restart;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 输出
 *
 * @author DengBo_Zhong
 * @Date 2020/9/14 16:11
 * @Version V1.0
 */
@Component("restartWriter")
public class RestartWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        for (Customer c :
                list) {
            System.out.println(c);
        }
    }
}
