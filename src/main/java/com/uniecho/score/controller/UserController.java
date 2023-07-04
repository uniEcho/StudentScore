package com.uniecho.score.controller;

import com.uniecho.score.entity.Student;
import com.uniecho.score.entity.User;
import com.uniecho.score.service.IStudentService;
import com.uniecho.score.service.IUserService;
import com.uniecho.score.utils.Constant;
import com.uniecho.score.utils.ResultObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author uni_E
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final IStudentService studentService;

    @Operation(summary = "用户登录" ,
            description = "返回用户对象",
            parameters = {@Parameter(name = "user", description = "用户对象")})
    @ApiResponse(responseCode = "200", description = "登录成功")
    //标识请求地址
    @PostMapping("/login")
    public ResultObject<List<User>> getUsers(User user, HttpServletRequest request) {

        //查询用户列表
        List<User> list = userService.getUser(user); System.out.println(list);
        ResultObject<List<User>> rs = new ResultObject<List<User>>();
        if (list.isEmpty()) {
            //状态码
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            //提示
            rs.setMsg("登录失败");
        } else {
            //状态码
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            request.getSession().setAttribute("user", list.get(0));
            //提示
            rs.setMsg("登录成功");
        }
        //数据
        rs.setData(list);
        return rs;
    }
    @Operation(summary = "用户登出" ,description = "返回用户对象")
    @ApiResponse(responseCode = "200", description = "登出成功")
    @PostMapping("/loginOut")
    public ResultObject<Object> loginOut(HttpServletRequest request) {
        //查询用户列表
        ResultObject<Object> rs = new ResultObject<Object>();
        request.getSession().removeAttribute("user");
        //数据
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("退出成功");
        return rs;
    }

    @Operation(summary = "学生登录" ,
            description = "返回用户对象",
            parameters = {@Parameter(name = "user", description = "用户对象")})
    @ApiResponse(responseCode = "200", description = "登录成功")
    //标识请求地址
    @PostMapping("/studentLogin")
    public ResultObject<List<Student>> studentLogin(User user, HttpServletRequest request) {
        //查询用户列表
        Student student = new Student();
        student.setStudentNo(Integer.parseInt(user.getUserName()));
        student.setStuPass(user.getPassWord());
        List<Student> list = studentService.selectLoginStudent(student);
        ResultObject<List<Student>> rs = new ResultObject<List<Student>>();
        if (list.isEmpty()) {
            //状态码
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            //提示
            rs.setMsg("登录失败");
        } else {
            //状态码
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            request.getSession().setAttribute("student", list.get(0));
            //提示
            rs.setMsg("登录成功");
        }
        //数据
        rs.setData(list);
        return rs;
    }
    @Operation(summary = "学生登出" ,description = "返回用户对象")
    @ApiResponse(responseCode = "200", description = "登出成功")
    @PostMapping("/studentLoginOut")
    public ResultObject<Object> studentLoginOut(HttpServletRequest request) {
        //查询用户列表
        ResultObject<Object> rs = new ResultObject<Object>();
        request.getSession().removeAttribute("student");
        //数据
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("退出成功");
        return rs;
    }

}
