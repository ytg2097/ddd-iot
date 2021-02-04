package io.ac.apigatewayplatform.common.util.log;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xjf
 * @version 1.0
 * Date 2018/11/6 11:19
 */
public class DataBufferUtilFix {
    public static Mono<DataBufferWrapper> join(Flux<DataBuffer> dataBuffers) {
        return dataBuffers.collectList()
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0).factory().join(list))
                .map(buf -> {
                    InputStream source = buf.asInputStream();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    byte[] buff = new byte[4096];

                    try {
                        int n = 0;
                        while ((n = source.read(buff)) != -1) {
                            stream.write(buff, 0, n);
                        }
                    } catch (IOException e) {
                        //
                    }

                    DataBufferWrapper wrapper = new DataBufferWrapper(stream.toByteArray(), buf.factory());
                    DataBufferUtils.release(buf);   //当前版本的 DataBufferUtils::join 没有这一句

                    return wrapper;
                })
                .defaultIfEmpty(new DataBufferWrapper());
    }
}
