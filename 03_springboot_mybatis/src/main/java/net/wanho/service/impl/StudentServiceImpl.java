package net.wanho.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import net.wanho.mapper.StudentMapper;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentServiceI {

    private final StudentMapper studentMapper;

    @Override
    public PageInfo<Student> findByCondition(int pageNum,int pageSize,Student student) {
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Student> pageInfo= new PageInfo<>(studentMapper.list(student));
        return pageInfo;
    }

    @Override
    public Student getById(Long id) {
        return studentMapper.getById(id);
    }

    @Override
    public void add(Student student) {
        studentMapper.add(student);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delById(Long id) {
        studentMapper.delById(id);
    }
}
