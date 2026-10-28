package net.wanho.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "学生类")
@TableName("student")
public class Student implements Serializable {

    @Schema(description = "学号")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birth;


}
