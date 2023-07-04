package com.uniecho.score.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniecho.score.entity.Student;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uni_E
 */
public interface IStudentService extends IService<Student> {
    /**
     * 全量查询用户
     *
     * @return
     */
    Page<Student> getAll(Student student, int page, int size);

    /**
     * 查询是否存在
     *
     * @return
     */
    Student selectByNo(int studentNo);

    /**
     * 增加学生信息
     *
     * @param student
     * @return
     */
    Integer addStudent(Student student);


    /**
     * 修改学生信息
     *
     * @param student
     * @return
     */
    Integer updateStudent(Student student);


    /**
     * 删除学生信息
     *
     * @param studentNo
     * @return
     */
    Integer deleteStudent(int studentNo);

    /**
     * 全量查询用户为下拉框
     *
     * @return
     */
    List<Student> selectAllStudent();

    /**
     * 查询学生账号信息
     *
     * @param student
     * @return
     */
    List<Student> selectLoginStudent(Student student);
}
