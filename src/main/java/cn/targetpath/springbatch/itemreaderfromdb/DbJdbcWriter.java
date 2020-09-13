package cn.targetpath.springbatch.itemreaderfromdb;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/7 23:53
 * @Version V1.0
 */
@Component("dbJdbcWriter")
public class DbJdbcWriter implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> list) throws Exception {
        for (User user: list) {
            System.out.println(user);
        }
    }
}
