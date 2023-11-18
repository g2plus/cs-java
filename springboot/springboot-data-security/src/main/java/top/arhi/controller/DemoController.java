package top.arhi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.arhi.dto.DemoRespDTO;
import top.arhi.dto.Response;

/**
 * @Author
 * @Description
 * @Date 2021/3/1 14:01
 */
@RestController
public class DemoController {

    /**
     * @return
     */
    @GetMapping(value = "/test")
    public Response<DemoRespDTO> test() {
        DemoRespDTO respDTO = new DemoRespDTO();
        respDTO.setUserName("李世民");
        respDTO.setPhone("15575909531");
        respDTO.setIdCard("32010319520807064X");
        respDTO.setPassword("asdf12345678");
        respDTO.setCustomValue("sfwegewgrergergwefwefwef");
        return Response.success(respDTO);
    }

}
