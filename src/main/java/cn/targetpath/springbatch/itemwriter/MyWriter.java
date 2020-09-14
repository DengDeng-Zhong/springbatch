package cn.targetpath.springbatch.itemwriter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/14 18:04
 * @Version V1.0
 */
@Component("myWriter")
public class MyWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println(list.size());
        for (String str: list
             ) {
            System.out.println(str);
        }
    }
}
