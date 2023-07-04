package com.uniecho.score.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.uniecho.score.entity.Score;
import com.uniecho.score.entity.Student;
import com.uniecho.score.utils.ResultObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uni_E
 */
public interface IScoreService extends IService<Score> {
    /**
     * 模糊查询学生成绩
     *
     * @param score
     * @return
     */
    public IPage<Score> getAllScore(Score score, int limit, int page);

    /**
     * 增加学生成绩
     *
     * @param score
     * @return
     */
    public ResultObject<Object> insertScore(Score score);

    /**
     * 更改学生成绩
     *
     * @param score
     * @return
     */
    public Integer updateScore(Score score);

    /**
     * 删除学生学生成绩
     *
     * @param scoreId
     * @return
     */
    public Integer deleteScore(int scoreId);

    /**
     * 根据编号查询学生成绩
     *
     * @param scoreId
     * @return
     */
    public Score selectScoreById(int scoreId);

    /**
     * 查询成绩报表
     *
     * @param student
     * @param limit
     * @param page
     * @return
     */
    public IPage<Student> getAllFinalScore(Student student, int limit, int page);
}
