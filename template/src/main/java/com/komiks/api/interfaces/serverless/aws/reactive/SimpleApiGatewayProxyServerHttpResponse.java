package com.khakiout.api.interfaces.serverless.aws.reactive;

import com.khakiout.api.interfaces.serverless.aws.model.ApiGatewayProxyResponse;
import java.util.Collection;
import java.util.Objects;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.AbstractServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SimpleApiGatewayProxyServerHttpResponse extends AbstractServerHttpResponse {

    private final ApiGatewayProxyResponse proxyResponse;
    private final DataBuffer bodyBuffer;
    private final Runnable bodyWriter;

    /**
     * Instantiate the SimpleApiGatewayProxyServerHttpResponse.
     *
     * @param proxyResponse the proxyResponse to be wrapped.
     */
    private SimpleApiGatewayProxyServerHttpResponse(ApiGatewayProxyResponse proxyResponse) {
        super(new DefaultDataBufferFactory());

        this.proxyResponse = proxyResponse;
        this.bodyBuffer = bufferFactory().allocateBuffer();
        this.bodyWriter = () -> {
            byte[] bytes = new byte[bodyBuffer.readableByteCount()];
            bodyBuffer.read(bytes);
            proxyResponse.setBody(bytes);
        };

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getNativeResponse() {
        return (T) this.proxyResponse;
    }

    @Override
    protected Mono<Void> writeWithInternal(Publisher<? extends DataBuffer> body) {
        return Flux.from(body).doOnNext(bodyBuffer::write)
            .doOnError(Throwable::printStackTrace)
            .doOnComplete(bodyWriter).then();
    }

    @Override
    protected Mono<Void> writeAndFlushWithInternal(
        Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return Flux.from(body).concatMap(Flux::from).doOnNext(bodyBuffer::write)
            .doOnComplete(bodyWriter).then();
    }

    @Override
    protected void applyStatusCode() {
        HttpStatus status = getStatusCode();
        if (Objects.nonNull(status)) {
            proxyResponse.setStatusCode(status.value());
        } else {
            proxyResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    protected void applyHeaders() {
        proxyResponse.addHeaders(getHeaders().toSingleValueMap());
    }

    @Override
    protected void applyCookies() {
        getCookies().values()
            .stream()
            .flatMap(Collection::stream)
            .forEach(cookie -> proxyResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString()));
    }


    public static SimpleApiGatewayProxyServerHttpResponse of(
        ApiGatewayProxyResponse proxyResponse) {
        return new SimpleApiGatewayProxyServerHttpResponse(proxyResponse);
    }

}
