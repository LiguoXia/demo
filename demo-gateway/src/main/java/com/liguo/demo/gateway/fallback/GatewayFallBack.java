package com.liguo.demo.gateway.fallback;

import com.alibaba.fastjson.JSONObject;
import com.liguo.demo.tool.enums.ResultCodeEnum;
import com.liguo.demo.tool.pojo.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 *
 * @author: 01380232
 * Date: 2021-07-19
 * Time: 20:17
 */
@Component
@Slf4j
public class GatewayFallBack implements FallbackProvider {

    /**
     * 根据服务id指定为哪个微服务提供回退，可以用* 或者 null 代表所有服务
     */
    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            /**
             * 网关向api服务请求是失败了，但是消费者客户端向网关发起的请求是OK的，
             * 不应该把api的404,500等问题抛给客户端
             * 网关和api服务集群对于客户端来说是黑盒子
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {

                return HttpStatus.OK;
            }

            /**
             * 数字类型状态码
             */
            @Override
            public int getRawStatusCode() throws IOException {

                return HttpStatus.OK.value();
            }

            /**
             * 状态文本
             */
            @Override
            public String getStatusText() throws IOException {

                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            /**
             * 当 springms-provider-user 微服务出现宕机后，客户端再请求时候就会返回 fallback 等字样的字符串提示；
             *
             * 但对于复杂一点的微服务，我们这里就得好好琢磨该怎么友好提示给用户了；
             *
             * 如果请求用户服务失败，返回什么信息给消费者客户端
             */
            @Override
            public InputStream getBody() throws IOException {
                HttpResult rs = HttpResult.failure(ResultCodeEnum.SERVER_ERROR);
                log.info(rs.getMessage());
                return new ByteArrayInputStream(JSONObject.toJSONString(rs).getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                // 和body中的内容编码一致，否则容易乱码
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };

    }
}
