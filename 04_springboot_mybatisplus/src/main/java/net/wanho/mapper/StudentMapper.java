package net.wanho.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import net.wanho.po.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper extends BaseMapper<Student> {
    Student findStudentAndScores(IPage<Student> page, @Param(Constants.WRAPPER) Wrapper<Student> queryWrapper);
}
