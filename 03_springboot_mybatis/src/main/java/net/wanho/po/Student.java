package net.wanho.po;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "学生类")
public class Student {

    @Schema(description = "学号")
    private Long id;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "年龄")
    private Integer age;
    @Schema(description = "性别")
    private String gender;
    @Schema(description = "地址")
    private String address;
    @Schema(description = "出生日期")
    private Date birth;

}
