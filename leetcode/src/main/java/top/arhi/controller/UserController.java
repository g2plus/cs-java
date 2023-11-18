package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arhi.cache.Cache;
import top.arhi.cache.ClearAndReloadCache;
import top.arhi.model.vo.Result;
import top.arhi.model.vo.UserVo;
import top.arhi.service.UserVoService;


/**
 * 用户控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserVoService userService;

    @GetMapping("/get/{id}")
    @Cache(name = "get method")
    //@Cacheable(cacheNames = {"get"})
    public Result get(@PathVariable("id") Integer id){
        return userService.get(id);
    }

    @PostMapping("/updateData")
    @ClearAndReloadCache(name = "get method")
    public Result updateData(@RequestBody UserVo user){
        return userService.update(user);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody UserVo user){
        return userService.insert(user);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return userService.delete(id);
    }

}
