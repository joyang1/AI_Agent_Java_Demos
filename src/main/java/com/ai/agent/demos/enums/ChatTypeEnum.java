package com.ai.agent.demos.enums;

import lombok.Getter;

/**
 * @author tommy
 * @Time 2025年08月17日 12:26
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName ChatTypeEnum.java
 */
@Getter
public enum ChatTypeEnum {
    SIMPLE("simple", "简单对话"),
    FUNCTION_CALL("functionCall", "使用工具调用对话"),
    ;

    private final String type;

    private final String desc;

    ChatTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据类型字符串获取对应的聊天类型枚举值
     *
     * @param type 聊天类型字符串
     * @return 匹配的聊天类型枚举值，如果未找到则返回null
     */
    public static ChatTypeEnum getByType(String type) {
        for (ChatTypeEnum value : values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return ChatTypeEnum.SIMPLE;
    }
}
