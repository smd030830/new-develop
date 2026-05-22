package com.mjc813.chapter20;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {
    private Integer code;
    private String message;
    private Object resultData;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
