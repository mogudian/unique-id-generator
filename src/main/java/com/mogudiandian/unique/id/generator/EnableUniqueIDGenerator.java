package com.mogudiandian.unique.id.generator;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用唯一ID生成器
 * @author sunbo
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UniqueIDConfiguration.class)
public @interface EnableUniqueIDGenerator {

}