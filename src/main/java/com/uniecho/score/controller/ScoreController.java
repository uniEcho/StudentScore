package com.uniecho.score.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uniecho.score.entity.Score;
import com.uniecho.score.entity.Student;
import com.uniecho.score.service.IScoreService;
import com.uniecho.score.utils.Constant;
import com.uniecho.score.utils.ResultObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author uni_E
 */
@Tag(name = "ScoreController",description = "成绩管理API")
@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final IScoreService scoreService;


    @Operation(summary = "新增成绩" ,
            description = "返回成绩对象",
            parameters = {@Parameter(name = "score", description = "成绩对象")})
    @ApiResponse(responseCode = "200", description = "新增成功")
    //标识请求地址
    @PostMapping("/addScore")
    public ResultObject<Object> addScore(Score score) {
        return scoreService.insertScore(score);
    }

    @Operation(summary = "获取所有成绩" ,
            description = "返回成绩对象、页数、条数",
            parameters = {@Parameter(name = "score", description = "成绩对象"),
                    @Parameter(name = "limit", description = "条数"),
                    @Parameter(name = "page", description = "页数")})
    @ApiResponse(responseCode = "200", description = "获取成功")
    //标识请求地址
    @GetMapping("/getAllScore")
    public ResultObject<List<Score>> getAllScore(Score score, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        IPage<Score> pageInfo = scoreService.getAllScore(score, page, limit);
        ResultObject<List<Score>> rs = new ResultObject<List<Score>>();
        List<Score> list = pageInfo.getRecords();
        for (Score temp : list) {
            String type = temp.getScoreType();
            if ("1".equals(type)) {
                temp.setScoreTypeName("习题");
            }
            if ("2".equals(type)) {
                temp.setScoreTypeName("测验");
            }
            if ("3".equals(type)) {
                temp.setScoreTypeName("考试成绩");
            }
        }
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    @Operation(summary = "修改成绩" ,
            description = "返回成绩对象",
            parameters = {@Parameter(name = "score", description = "成绩对象")})
    @ApiResponse(responseCode = "200", description = "修改成绩成功")
    //标识请求地址
    @PutMapping("/updateScore")
    public ResultObject<Object> updateScore(Score score) {
        Integer total = scoreService.updateScore(score);
        //统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (null == total || 0 == total) {
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            rs.setMsg("修改学生成绩信息失败");
        } else {
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            rs.setMsg("修改学生成绩信息成功");
        }
        return rs;
    }

    @Operation(summary = "删除成绩" ,
            description = "返回成绩对象",
            parameters = {@Parameter(name = "score", description = "成绩对象")})
    @ApiResponse(responseCode = "200", description = "删除成绩成功")
    //标识请求地址
    @DeleteMapping("/deleteScore")
    public ResultObject<Object> deleteScore(Score score) {
        Integer total = scoreService.deleteScore(score.getScoreId());
        //统一返回
        ResultObject<Object> rs = new ResultObject<Object>();
        if (null == total || 0 == total) {
            rs.setCode(Constant.FAILURE_RETURN_CODE);
            rs.setMsg("删除学生成绩信息失败");
        } else {
            rs.setCode(Constant.SUCCESS_RETURN_CODE);
            rs.setMsg("删除学生成绩信息成功");
        }
        return rs;
    }

    @Operation(summary = "获取所有总成绩" ,
            description = "返回成绩对象、页数、条数",
            parameters = {@Parameter(name = "score", description = "成绩对象"),
                    @Parameter(name = "limit", description = "条数"),
                    @Parameter(name = "page", description = "页数")})
    @ApiResponse(responseCode = "200",description = "获取成功")
    @GetMapping("/getAllSumScore")
    public ResultObject<List<Student>> getAllSumScore(Student student, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        IPage<Student> pageInfo = scoreService.getAllFinalScore(student, page, limit);
        ResultObject<List<Student>> rs = new ResultObject<List<Student>>();
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getRecords());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    @Operation(summary = "获取我的成绩" ,
            description = "返回成绩对象",
            parameters = {@Parameter(name = "score", description = "成绩对象")})
    @ApiResponse(responseCode = "200", description = "获取成功")
    //获取我的成绩
    @GetMapping("/getMyScore")
    public ResultObject<List<Student>> getAllSumScore(HttpServletRequest request, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        Student student = (Student) request.getSession().getAttribute("student");
        IPage<Student> pageInfo = scoreService.getAllFinalScore(student, limit, page);
        ResultObject<List<Student>> rs = new ResultObject<List<Student>>();
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getRecords());
        rs.setCount(pageInfo.getTotal());
        return rs;

    }
    @Operation(summary = "获取学生自己的成绩信息" ,
            description = "返回成绩对象",
            parameters = {@Parameter(name = "score", description = "成绩对象")})
    @ApiResponse(responseCode = "200", description = "获取成功")
    @GetMapping("/getMyScoreInfo")
    public ResultObject<List<Score>> getMyScoreInfo(HttpServletRequest request, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        Student student = (Student) request.getSession().getAttribute("student");
        Score score = new Score();
        score.setStudentId(student.getStudentNo());
        IPage<Score> pageInfo = scoreService.getAllScore(score, page, limit);

        Map<String, String> scoreTypeNames = new HashMap<>();
        scoreTypeNames.put("1", "习题");
        scoreTypeNames.put("2", "测验");
        scoreTypeNames.put("3", "考试成绩");

        List<Score> list = pageInfo.getRecords();
        for (Score temp : list) {
            String type = temp.getScoreType();
            if (scoreTypeNames.containsKey(type)) {
                temp.setScoreTypeName(scoreTypeNames.get(type));
            }
        }

        ResultObject<List<Score>> rs = new ResultObject<List<Score>>();
        rs.setCode(Constant.SUCCESS_RETURN_CODE);
        rs.setMsg("查询成功");
        rs.setData(list);
        rs.setCount(pageInfo.getTotal());
        return rs;
    }
}
