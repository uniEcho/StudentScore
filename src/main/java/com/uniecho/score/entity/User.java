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
@TableName("t_user")
@Schema(name = "User", title = "用户对象", description = "用户对象")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "用户id", description = "用户id")
    @TableId(value = "user_id")
    private Integer userId;

    @Schema(title = "用户名", description = "用户名")
    @TableField(value = "user_name")
    private String userName;

    @Schema(title = "密码", description = "密码")
    @TableField(value = "pass_word")
    private String passWord;
}
