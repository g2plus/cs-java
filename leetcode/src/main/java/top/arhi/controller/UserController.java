package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.arhi.annotation.Cache;
import top.arhi.annotation.ClearCache;
import top.arhi.model.vo.AjaxResult;
import top.arhi.model.vo.UserVo;
import top.arhi.service.UserVoService;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserVoService userService;

    @GetMapping("/get/{id}")
    @Cache(name = "#id")
    //@Cacheable(cacheNames = {"get"})
    public AjaxResult get(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @PostMapping("/updateData")
    @ClearCache(name = "#user.id")
    public AjaxResult updateData(@RequestBody UserVo user) {
        return userService.update(user);
    }

    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody UserVo user) {
        return userService.insert(user);
    }

    @DeleteMapping("/delete/{id}")
    @ClearCache(name = "#id")
    public AjaxResult delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }

}
