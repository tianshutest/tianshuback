package com.example.tianshu.Enum;

import lombok.Data;

@Data
public class Answer {
    private final String value;
    private final String type;

    public Answer(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
