package com.mogudiandian.unique.id.generator;

import com.baidu.fsg.uid.UidGenerator;

/**
 * 默认的唯一ID生成器（使用百度的uid-generator）
 * @author sunbo
 */
public class DefaultUniqueIDGenerator implements UniqueIDGenerator {

    private UidGenerator uidGenerator;

    public DefaultUniqueIDGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    @Override
    public long generate() {
        return uidGenerator.getUID();
    }
}
