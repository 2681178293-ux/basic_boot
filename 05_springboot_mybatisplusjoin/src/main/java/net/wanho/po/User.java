package net.wanho.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class User {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableField(exist = false)
    private List<Address> addresses;

}
