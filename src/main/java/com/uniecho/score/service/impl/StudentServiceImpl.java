package com.uniecho.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniecho.score.entity.Student;
import com.uniecho.score.mapper.StudentMapper;
import com.uniecho.score.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author uni_E
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    private final StudentMapper studentMapper;

    @Override
    public Page<Student> getAll(Student student, int page, int size) {
        Page<Student> studentPage = new Page<>(page, size);
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        if (student.getStudentName() != null && !student.getStudentName().equals("")) {
            wrapper.eq(Student::getStudentName, student.getStudentName());
            return studentMapper.selectPage(studentPage, wrapper);
        }
        return studentMapper.selectPage(studentPage, null);
    }

    /**
     * 查询是否存在
     *
     * @param studentNo 学生id
     * @return
     */
    @Override
    public Student selectByNo(int studentNo) {
        return studentMapper.selectById(studentNo);
    }

    /**
     * 增加学生信息
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public Integer addStudent(Student student) {
        return studentMapper.insert(student);
    }

    /**
     * 修改学生信息
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public Integer updateStudent(Student student) {
        return studentMapper.updateById(student);
    }

    /**
     * 删除学生信息
     *
     * @param studentNo 学生id
     * @return
     */
    @Override
    public Integer deleteStudent(int studentNo) {
        return studentMapper.deleteByStudentId(studentNo);
    }

    /**
     * 全量查询所有学生为下拉框
     *
     * @return
     */
    @Override
    public List<Student> selectAllStudent() {
        return studentMapper.selectList(null);
    }

    /**
     * 查询学生账号信息
     *
     * @param student 学生信息
     * @return
     */
    @Override
    public List<Student> selectLoginStudent(Student student) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStudentNo, student.getStudentNo()).eq(Student::getStuPass, student.getStuPass());
        return studentMapper.selectList(queryWrapper);
    }
}
