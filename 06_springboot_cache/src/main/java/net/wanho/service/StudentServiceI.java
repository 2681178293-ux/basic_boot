package net.wanho.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;
import net.wanho.po.Student;

import java.util.List;

public interface StudentServiceI extends IService<Student> {

    IPage<Student> findByPage(int pageNum, int pageSize, Student student);

    /** 查询全部列表（带缓存） */
    List<Student> listAll();

    Student queryById(Long id);

    /** 新增：配合 @CachePut，需返回实体以便写入缓存 */
    Student add(Student student);

    /** 修改：配合 @CachePut，需返回实体以便更新缓存 */
    Student edit(Student student);

    void delete(Long id);
}
