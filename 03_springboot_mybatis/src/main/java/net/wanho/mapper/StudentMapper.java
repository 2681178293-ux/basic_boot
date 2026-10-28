package net.wanho.mapper;

import net.wanho.po.Student;

import java.util.List;

public interface StudentMapper  {
    List<Student> list(Student student);
    Student getById(Long id);
    void add(Student student);
    void update(Student student);
    void delById(Long id);
}
