package com.uniecho.score.entity;

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
@TableName("t_student")
@Schema(name = "Student",title = "学生对象", description = "学生对象")
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "学生id", description = "学生id")
    @TableId(value = "student_no")
    private Integer studentNo;

    @Schema(title = "学生姓名", description = "学生姓名")
    @TableField(value = "student_name")
    private String studentName;

    @Schema(title = "学生性别", description = "学生性别")
    @TableField(value = "student_sex")
    private String studentSex;

    @Schema(title = "学生密码", description = "学生密码")
    @TableField(value = "stu_pass")
    private String stuPass;

    @Schema(title = "习题", description = "习题")
    @TableField(value = "score1")
    private double score1;

    @Schema(title = "测验", description = "测验")
    @TableField(value = "score2")
    private double score2;

    @Schema(title = "考试", description = "考试")
    @TableField(value = "score3")
    private double score3;

    @Schema(title = "总成绩", description = "总成绩")
    @TableField(value = "sum_score")
    private double sumScore;
}
