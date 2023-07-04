package com.uniecho.score.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uniecho.score.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author uni_E
 */
public interface IUserService extends IService<User> {
    /**
     * 全量查询用户
     *
     * @return
     */
    List<User> getUser(User user);


}
