package io.ac.apigatewayplatform.common.util.log;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
public class RecorderServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private final List<DataBuffer> dataBuffers = new LinkedList<>();
    private boolean bufferCached = false;
    private Mono<Void> progress = null;

    public RecorderServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        synchronized (dataBuffers) {
            if (bufferCached) {
                return copy();
            }

            if (progress == null) {
                progress = cache();
            }

            return progress.thenMany(Flux.defer(this::copy));
        }
    }

    private Flux<DataBuffer> copy() {
        return Flux.fromIterable(dataBuffers)
                .map(buf -> buf.factory().wrap(buf.asByteBuffer()));
    }

    private Mono<Void> cache() {
        return super.getBody()
                .map(dataBuffers::add)
                .then(Mono.defer(()-> {
                    bufferCached = true;
                    progress = null;

                    return Mono.empty();
                }));
    }
}
