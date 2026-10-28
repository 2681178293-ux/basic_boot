package net.wanho.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Address {
    @TableId
    private Long id;
    private Long userId;
    private String city;
    private String address;

}
