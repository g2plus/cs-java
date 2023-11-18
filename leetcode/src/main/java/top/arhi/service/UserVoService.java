package top.arhi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import top.arhi.mapper.UserVoMapper;
import top.arhi.model.vo.Result;
import top.arhi.model.vo.UserVo;

import javax.annotation.Resource;

/**
 * service层
 */
@Service
public class UserVoService {

    @Resource
    private UserVoMapper userVoMapper;

    public Result get(Integer id) {
        LambdaQueryWrapper<UserVo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserVo::getId, id);
        UserVo user = userVoMapper.selectOne(wrapper);
        return Result.success(user);
    }

    public Result insert(UserVo user) {
        int line = userVoMapper.insert(user);
        if (line > 0) {
            return Result.success(line);
        }
        return Result.fail(888, "操作数据库失败");
    }

    public Result delete(Integer id) {
        LambdaQueryWrapper<UserVo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserVo::getId, id);
        int line = userVoMapper.delete(wrapper);
        if (line > 0) {
            return Result.success(line);
        }
        return Result.fail(888, "操作数据库失败");
    }

    public Result update(UserVo user) {
        int i = userVoMapper.updateById(user);
        if (i > 0) {
            return Result.success(i);
        }
        return Result.fail(888, "操作数据库失败");
    }

}
