package com.xinaml.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinaml.gateway.result.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午2:26]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class AuthFilter  implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isNotBlank(token)) {
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        Result data = new Result();
        data.setCode(HttpStatus.UNAUTHORIZED.value());
        data.setMsg("非法请求！");
        byte[] datas = JSON.toJSONString(data,SerializerFeature.WriteMapNullValue).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
