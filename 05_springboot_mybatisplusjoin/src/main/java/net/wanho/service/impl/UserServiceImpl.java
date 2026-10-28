package net.wanho.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import jakarta.annotation.Resource;
import net.wanho.mapper.AddressMapper;
import net.wanho.mapper.UserMapper;
import net.wanho.po.Address;
import net.wanho.po.User;
import net.wanho.service.UserServiceI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserServiceI {

    @Resource
    private AddressMapper addressMapper;

    @Override
     public User getUserAndAddressById(Long userId) {
        // select * from user u left join address a on u.id=a.user_id where u.id=1
        
        User user = baseMapper.selectJoinOne(User.class,
                new MPJLambdaWrapper<User>()
                        .selectAll(User.class)
                        .selectCollection(Address.class,User::getAddresses)
                        .leftJoin(Address.class, Address::getUserId, User::getId)
                        .eq(User::getId, userId));
        return user;

    }

    @Override
    public IPage<User> getUsers(int pageNum, int pageSize, User user) {
       /* IPage<User> page = new Page<>(pageNum,pageSize);
        MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(User.class)
                .selectCollection(Address.class,User::getAddresses)
                .leftJoin(Address.class, Address::getUserId, User::getId)
                .like(StrUtil.isNotEmpty(user.getName()),User::getName, user.getName())
        ;
       return   baseMapper.selectJoinPage(page,wrapper);*/

        // 1.按条件查用户分页
        IPage<User> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> wrapperUser = new LambdaQueryWrapper<>();
        wrapperUser.like(StrUtil.isNotEmpty(user.getName()),User::getName, user.getName());
        IPage<User> userPage = this.page(page, wrapperUser);


        // 2.查询每个用户下所有地址
        List<Long> userIds = userPage.getRecords().stream().map(User::getId).toList();
        LambdaQueryWrapper<Address> wrapperAddress = new LambdaQueryWrapper<>();
        wrapperAddress.in(Address::getUserId, userIds);
        Map<Long, List<Address>> addressMap = addressMapper.selectList(wrapperAddress).stream().collect(Collectors.groupingBy(Address::getUserId));

        // 3.回填地址
        userPage.getRecords().forEach(u->u.setAddresses(addressMap.get(u.getId())));

        return page;
    }





}
