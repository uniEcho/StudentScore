package com.uniecho.score.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uniecho.score.entity.Student;
import com.uniecho.score.service.IStudentService;
import com.uniecho.score.utils.Constant;
import com.uniecho.score.utils.ResultObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author uni_E
 */
@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService studentService;

    @Operation(summary = "获取所有学生" ,
            description = "返回学生对象",
            parameters = {@Parameter(name = "student", description = "学生对象")})
    @ApiResponse(responseCode = "200", description = "获取成功")
    //标识请求地址
    @GetMapping("/getAllStudent")
    public ResultObject<List<Student>> getUsers(Student student, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        Page<Student> pageInfo = studentService.getAll(student, page, limit);
        ResultObject<List<Student>> rs = new ResultObject<List<Student>>();
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getRecords());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    @Operation(summary = "添加学生信息" ,
            description = "返回学生对象",
            parameters = {@Parameter(name = "student", description = "学生对象")})
    @ApiResponse(responseCode = "200", description = "新增成功")
    //标识请求地址
    @PostMapping("/addStudent")
    public ResultObject<Object> addStudent(Student student) {
        Integer studentNo = student.getStudentNo();
        student.setStuPass(studentNo.toString());
        Student result = studentService.selectByNo(studentNo);
        //统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (null == result) {
            studentService.addStudent(student);
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            rs.setMsg("增加学生信息成功");
        } else {
            rs.setCode(Constant.HASE_RETURN_CODE);
            rs.setMsg("学号已存在");
        }
        return rs;
    }

    @Operation(summary = "修改学生信息" ,
            description = "返回学生对象",
            parameters = {@Parameter(name = "student", description = "学生对象")})
    @ApiResponse(responseCode = "200", description = "修改成功")
    //标识请求地址
    @PutMapping("/updateStudent")
    public ResultObject<Object> updateStudent(Student student) {
        Integer total = studentService.updateStudent(student);
        //统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (null == total || 0 == total) {
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            rs.setMsg("修改学生信息失败");
        } else {
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            rs.setMsg("修改学生信息成功");
        }
        return rs;
    }

    @Operation(summary = "删除学生信息" ,
            description = "返回学生对象",
            parameters = {@Parameter(name = "student", description = "学生对象")})
    @ApiResponse(responseCode = "200", description = "删除成功")
    //标识请求地址
    @DeleteMapping("/deleteStudent")
    public ResultObject<Object> deleteStudent(@RequestParam("studentNo") int studentNo) {
        Integer total = studentService.deleteStudent(studentNo);
        //统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (null == total || 0 == total) {
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            rs.setMsg("修改学生信息失败");
        } else {
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            rs.setMsg("修改学生信息成功");
        }
        return rs;
    }



    /**
     * 获取学生下拉框
     *
     * @return
     */
    @Operation(summary = "获取学生下拉框" ,
            description = "返回学生对象",
            parameters = {@Parameter(name = "student", description = "学生对象")})
    @ApiResponse(responseCode = "200", description = "获取成功")
    @GetMapping("/studentSelect")
    public ResultObject<List<Student>> studentSelect() {
        ResultObject<List<Student>> rs = new ResultObject<List<Student>>();
        List<Student> list = studentService.selectAllStudent();
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        int total = list.size();
        Long a = Long.parseLong(String.valueOf(total));
        rs.setCount(a);
        return rs;
    }

}
