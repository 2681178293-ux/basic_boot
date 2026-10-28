package net.wanho.mapper;

import jakarta.annotation.Resource;
import net.wanho.po.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductMapperTest {

    @Resource
    private ProductMapper productMapper;

    @Test
    public void changePrice(){
        Product product1 = productMapper.selectById(1);
        Product product2 = productMapper.selectById(1);

        product1.setPrice(product1.getPrice()+50);
        productMapper.updateById(product1);

        product2.setPrice(product2.getPrice()-30);
        int res =productMapper.updateById(product2);

        if(res==0){
            product2 = productMapper.selectById(1);
            product2.setPrice(product2.getPrice()-30);
            productMapper.updateById(product2);
        }


    }

}
