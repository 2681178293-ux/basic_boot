package net.wanho.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Product {

    @TableId
    private Integer id;
    private String name;
    private Double price;

    @Version
    private Integer version;

}
