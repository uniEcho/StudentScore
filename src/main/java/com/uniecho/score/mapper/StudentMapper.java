package com.uniecho.score.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uniecho.score.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author uni_E
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Map<String, Object> selectFinalScore(Student student);

    int deleteByStudentId(Integer studentNo);

}
