package net.wanho.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Schema(description = "学生类")
@TableName("student")
public class Student {

    @Schema(description = "学号")
    //@TableId(type = IdType.AUTO) // auto none input uuid assignid

    @TableId
    private Long id;

    @Schema(description = "姓名")
    @TableField("name")
    private String name;
    @Schema(description = "年龄")
    private Integer age;
    @Schema(description = "性别")
    private String gender;
    @Schema(description = "地址")
    private String address;
    @Schema(description = "出生日期")
    private Date birth;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //@TableLogic(delval = "2", value = "0")
    private String delFlag;

    @TableField(exist = false)
    private List<Score> scores;

}
