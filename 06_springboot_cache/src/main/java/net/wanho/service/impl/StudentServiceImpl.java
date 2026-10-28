package net.wanho.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import net.wanho.mapper.StudentMapper;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentServiceI {

    /** 分页不缓存：条件组合多、命中率低，增删改还要整页失效，生产一般直接查库 */
    @Override
    public IPage<Student> findByPage(int pageNum, int pageSize, Student student) {
        IPage<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(student.getName()), Student::getName, student.getName());
        wrapper.eq(StrUtil.isNotEmpty(student.getGender()), Student::getGender, student.getGender());
        wrapper.eq(StrUtil.isNotEmpty(student.getAddress()), Student::getAddress, student.getAddress());
        wrapper.eq(ObjectUtil.isNotEmpty(student.getAge()), Student::getAge, student.getAge());
        return this.page(page, wrapper);
    }

    /** @Cacheable：全量列表缓存。key 固定为 list，形如 student::list */
    @Override
    @Cacheable(value = "student", key = "'list'")
    public List<Student> listAll() {
        System.out.println("查询全部学生列表");
        return this.list();
    }

    /** @Cacheable：有缓存直接返回，没有再查库并写入。key 形如 student::1001 */
    @Override
    @Cacheable(value = "student", key = "#id", unless = "#result == null")
    public Student queryById(Long id) {
        System.out.println("查询数据库");
        return this.getById(id);
    }

    /** @Caching：写入单条缓存 + 清空列表缓存 */
    @Override
    @Caching(
            put = @CachePut(value = "student", key = "#result.id"),
            evict = @CacheEvict(value = "student", key = "'list'")
    )
    public Student add(Student student) {
        this.save(student);
        return student;
    }

    /** @Caching：覆盖单条缓存 + 清空列表缓存 */
    @Override
    @Caching(
            put = @CachePut(value = "student", key = "#student.id"),
            evict = @CacheEvict(value = "student", key = "'list'")
    )
    public Student edit(Student student) {
        this.updateById(student);
        return student;
    }

    /** @Caching：删除单条缓存 + 清空列表缓存 */
    @Override
    @Caching(evict = {
            @CacheEvict(value = "student", key = "#id"),
            @CacheEvict(value = "student", key = "'list'")
    })
    public void delete(Long id) {
        this.removeById(id);
    }
}
