package net.wanho.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.wanho.po.Student;
import net.wanho.service.StudentServiceI;
import net.wanho.vo.AjaxResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stu")
@RequiredArgsConstructor
@Tag(name = "学生管理")
public class StudentController {

    private final StudentServiceI studentService;

    @Operation(summary = "分页查询学生信息")
    @GetMapping("/findByPage")
    public AjaxResult findByPage(@Parameter(description = "当前页") @RequestParam("pageNum") int pageNum, @Parameter(description = "页大小") @RequestParam("pageSize") int pageSize, Student student){
        IPage<Student> pageInfo = studentService.findByPage(pageNum, pageSize, student);
        return AjaxResult.success(pageInfo);
    }

    @Operation(summary = "查询全部学生列表")
    @GetMapping("/list")
    public AjaxResult listAll() {
        return AjaxResult.success(studentService.listAll());
    }

    @Operation(summary = "根据id查询学生信息")
    @GetMapping("/{id}")
    public AjaxResult getById(@Parameter(description = "学号") @PathVariable("id") Long id){
        Student student = studentService.queryById(id);
        return AjaxResult.success(student);
    }

    @Operation(summary = "添加学生信息")
    @PostMapping
    public AjaxResult add(@RequestBody Student student){
        studentService.add(student);
        return AjaxResult.success();
    }

    @Operation(summary = "修改学生信息")
    @PutMapping
    public AjaxResult update(@RequestBody Student student){
        studentService.edit(student);
        return AjaxResult.success();
    }

    @Operation(summary = "删除学生信息")
    @DeleteMapping("/{id}")
    public AjaxResult delete(@Parameter(description = "学号") @PathVariable("id") Long id){
        studentService.delete(id);
        return AjaxResult.success();
    }
}
