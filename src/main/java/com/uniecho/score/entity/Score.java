package com.uniecho.score.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author uni_E
 */
@Getter
@Setter
@ToString
@TableName("t_score")
@Schema(name = "Score", title = "成绩对象", description = "成绩对象")
public class Score implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "成绩id", description = "成绩id")
    @TableId(value = "score_id", type = IdType.AUTO)
    private Integer scoreId;

    @Schema(title = "成绩", description = "成绩")
    @TableField(value = "score_value")
    private Integer scoreValue;

    @Schema(title = "成绩类型", description = "成绩类型")
    @TableField(value = "score_type")
    private String scoreType;

    @Schema(title = "成绩类型名字", description = "成绩类型名字")
    @TableField(value = "score_type_name")
    private String scoreTypeName;

    @Schema(title = "学生id", description = "学生id")
    @TableField(value = "student_id")
    private Integer studentId;

    @Schema(title = "学生姓名", description = "学生姓名")
    @TableField(value = "student_name")
    private String studentName;
}
