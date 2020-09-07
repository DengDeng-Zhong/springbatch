package cn.targetpath.springbatch.itemReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

/**
 * 读取数据
 * @author DengBo_Zhong
 * @Date 2020/9/7 23:15
 * @Version V1.0
 */
public class MyReaader implements ItemReader<String> {
    private final Iterator<String> iterator;

    public MyReaader(List<String> list) {
        this.iterator = list.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // 一个一个读取数据
        if (iterator.hasNext()) {
            return this.iterator.next();
        }else {
            return  null;
        }
    }
}
