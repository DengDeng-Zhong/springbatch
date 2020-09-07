package cn.targetpath.springbatch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author DengBo_Zhong
 * @Date 2020/9/7 20:16
 * @Version V1.0
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext){
        System.out.println("beforeChunk: "+ chunkContext.getStepContext().getStepName());
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext){
        System.out.println("afterChunk: "+chunkContext.getStepContext().getStepName());
    }
}
