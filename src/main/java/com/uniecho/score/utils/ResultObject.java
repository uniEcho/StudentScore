package com.uniecho.score.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by uni_E
 * @classname ResultObject
 * @description TODO
 */
@Getter
@Setter
@Schema(name = "ResultObject", title = "结果对象", description = "结果对象")
public class ResultObject<T> {

    @Schema(title = "代码", description = "代码")
    private String code;

    @Schema(title = "信息", description = "信息")
    private String msg;

    @Schema(title = "数据", description = "数据")
    private T data;

    @Schema(title = "总计", description = "总计")
    private Long count;
}
