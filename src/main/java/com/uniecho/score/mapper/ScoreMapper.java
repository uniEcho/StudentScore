package com.uniecho.score.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uniecho.score.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author uni_E
 */
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    IPage<Score> selectScorePage(Page<Score> page, @Param("score") Score score);
}
