package net.wanho.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import net.wanho.mapper.StudentMapper;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentServiceI {
    @Override
    public IPage<Student> findByPage(int pageNum, int pageSize, Student student) {
        IPage<Student> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(student.getName()),Student::getName, student.getName());
        wrapper.eq(StrUtil.isNotEmpty(student.getGender()),Student::getGender, student.getGender());
        wrapper.eq(StrUtil.isNotEmpty(student.getAddress()),Student::getAddress, student.getAddress());
        wrapper.eq(ObjectUtil.isNotEmpty(student.getAge()),Student::getAge, student.getAge());
        return this.page(page,wrapper);
    }

    @Override
    public Student findStudentAndScores(Student student) {
        IPage<Student> page = new Page<>(1,10);
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("s.id", student.getId());
        wrapper.eq("s.del_flag","0");
        return this.baseMapper.findStudentAndScores(page,wrapper);
    }
}
