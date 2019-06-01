package com.xinaml.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.xinaml.common.constant.CommonConst;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: [lgq]
 * @Date: [19-6-1 下午2:01]
 * @Description: 处理传入及返回参数日期类型转换
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class GlobalDateConverterHandler {
    /**
     * 返回json的日期处理
     *
     * @return
     */
    @Bean(name = "objectMapper")
    public ObjectMapper getObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConst.DATETIME)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(CommonConst.DATE)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(CommonConst.TIME)));
        om.registerModule(javaTimeModule);
        return om;
    }

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    /**
     * 接收前端日期的转换处理
     *
     * @return
     */

    @Bean
    public Converter<String, LocalDateTime> LocalDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonConst.DATETIME);
                LocalDateTime date = null;
                try {
                    if (StringUtils.isNotBlank(source)) {
                        date = LocalDateTime.parse(source, df);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> LocalDateConvert() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonConst.DATE);
                LocalDate date = null;
                try {
                    if (StringUtils.isNotBlank(source)) {
                        date = LocalDate.parse(source, df);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }

    @Bean
    public Converter<String, LocalTime> LocalTimeConvert() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {

                DateTimeFormatter df = DateTimeFormatter.ofPattern(CommonConst.TIME);
                LocalTime time = null;
                try {
                    if (StringUtils.isNotBlank(source)) {
                        time = LocalTime.parse(source, df);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return time;
            }
        };
    }
}
