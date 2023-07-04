package com.uniecho.score.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uniecho.score.entity.Score;
import com.uniecho.score.entity.Student;
import com.uniecho.score.mapper.ScoreMapper;
import com.uniecho.score.mapper.StudentMapper;
import com.uniecho.score.service.IScoreService;
import com.uniecho.score.utils.Constant;
import com.uniecho.score.utils.ResultObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author uni_E
 */
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    private final ScoreMapper scoreMapper;

    private final StudentMapper studentMapper;

    @Override
    public IPage<Score> getAllScore(Score score, int page, int limit) {
        Page<Score> scorePage = new Page<>(page, limit);
        return scoreMapper.selectScorePage(scorePage, score);
    }

    @Override
    public ResultObject<Object> insertScore(Score score) {
        LambdaQueryWrapper<Score> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Score::getScoreType, score.getScoreType()).eq(Score::getStudentId, score.getStudentId());
        Long count = scoreMapper.selectCount(queryWrapper);
        String type = score.getScoreType();
        boolean flag = true;
        String str = "";
        switch (type) {
            case "1" -> {
                if (count >= 16) {
                    flag = false;
                    str = "该学生的习题成绩已经达到16条";
                }
            }
            case "2" -> {
                if (count >= 3) {
                    flag = false;
                    str = "该学生的测验成绩已经达到3条";
                }
            }
            case "3" -> {
                if (count >= 1) {
                    flag = false;
                    str = "该学生的考试成绩已经达到1条";
                }
            }
            default -> flag = true;
        }
        // 统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (flag) {
            int total = scoreMapper.insert(score);
            if (0 == total) {
                rs.setCode(Constant.HASE_RETURN_CODE);
                rs.setMsg("成绩添加失败");
            } else {
                rs.setCode(Constant.SUCCESS_RETURN_CODE);
                rs.setMsg("成绩添加成功");
            }
        } else {
            rs.setCode(Constant.HASE_RETURN_CODE);
            rs.setMsg(str);
        }
        return rs;
    }

    @Override
    public Integer updateScore(Score score) {
        return scoreMapper.updateById(score);
    }

    @Override
    public Integer deleteScore(int scoreId) {
        return scoreMapper.deleteById(scoreId);
    }

    @Override
    public Score selectScoreById(int scoreId) {
        return scoreMapper.selectById(scoreId);
    }

    @Override
    public IPage<Student> getAllFinalScore(Student student, int limit, int page) {
        // 创建分页对象
        Page<Student> pageObj = new Page<>(page, limit);
        List<Student> list = studentMapper.selectList(null);
        for (Student temp : list) {
            double sum = 0;
            Map<String, Object> map = studentMapper.selectFinalScore(temp);
            DecimalFormat df = new DecimalFormat("#.00");
            double score1 = Double.parseDouble(map.get("score1").toString());
            temp.setScore1(Double.parseDouble(df.format(score1)));
            double score2 = Double.parseDouble(map.get("score2").toString());
            temp.setScore2(Double.parseDouble(df.format(score2)));
            double score3 = Double.parseDouble(map.get("score3").toString());
            temp.setScore3(Double.parseDouble(df.format(score3)));
            sum = (score1 * 0.1) + (score2 * 0.2) + (score3 * 0.7);
            temp.setSumScore(Double.parseDouble(df.format(sum)));
        }
        // 封装成IPage对象并返回
        IPage<Student> iPage = studentMapper.selectPage(pageObj,new QueryWrapper<>());
        iPage.setRecords(list);
        return iPage;
    }
}
