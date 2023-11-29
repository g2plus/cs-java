package top.arhi.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import top.arhi.model.dto.UserDTO;
import top.arhi.model.pojo.User;
import top.arhi.model.vo.AjaxResult;
import top.arhi.util.FastJsonUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping("/rest")
public class RestfulController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${amap.key}")
    private String amapKey = "659f057c4e1b3c72ae58027363b3afb9";

    //440106
    @GetMapping(value = "/get")
    public AjaxResult testGet(@RequestParam("city_code") String cityCode) {
        ResponseEntity<Object> forEntity = restTemplate.getForEntity("https://restapi.amap.com/v3/weather/weatherInfo?extensions=all&key=" + amapKey + "&city=" + cityCode, Object.class);
        System.out.println("状态码:" + forEntity.getStatusCode());
        System.out.println("状态码内容:" + forEntity.getStatusCodeValue());
        HttpHeaders headers = forEntity.getHeaders();
        System.out.println("响应头:" + headers);
        Object body = forEntity.getBody();
        System.out.println("响应内容:" + body);
        String s = FastJsonUtil.parseObjToJson(body);
        Map map = FastJsonUtil.parseJsonToObj(s, Map.class);
        return AjaxResult.success(map.get("forecasts"));
    }

    @GetMapping("/citycode/list")
    public AjaxResult citycodeList() {
        return AjaxResult.success();
    }

    @PostMapping("/post")
    public AjaxResult testPost(UserDTO user) {
        //默认采用x-www-form-urlencoded格式的数据
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity("https://httpbin.org/post", user, Object.class);
        System.out.println("状态码:" + objectResponseEntity.getStatusCode());
        System.out.println("状态码内容:" + objectResponseEntity.getStatusCodeValue());
        HttpHeaders headers = objectResponseEntity.getHeaders();
        System.out.println("响应头:" + headers);
        MediaType contentType = objectResponseEntity.getHeaders().getContentType();
        System.out.println(contentType);
        System.out.println("消息响应内容:" + objectResponseEntity.getBody());
        JSONObject jsonObject = new JSONObject((Map) objectResponseEntity.getBody());
        String s = jsonObject.get("data").toString();
        Map map = FastJsonUtil.parseJsonToObj(s, Map.class);
        return AjaxResult.success(map);
    }

    @PostMapping("/post/form")
    public AjaxResult testPostUri() {
        //请求地址
        String url = "https://httpbin.org/post";

        //设置请求头, x-www-form-urlencoded格式的数据
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, UTF_8);
        httpHeaders.setContentType(mediaType);

        //提交参数设置
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name", "zhangsan");

        //组装请求体
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(map, httpHeaders);


        //发送post请求并打印结果 以String类型接收响应结果JSON字符串
        String s = restTemplate.postForObject(url, request, String.class);
        System.out.println(s);
        return AjaxResult.success();
    }

    @PostMapping("/post/json")
    public AjaxResult testPosJson() {
        String url = "http://localhost:8081/test/post";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        //设置ua
        headers.remove("User-Agent");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject param = new JSONObject();

        param.put("username", "123");

        HttpEntity<String> formEntity = new HttpEntity<String>(param.toString(), headers);
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(url, formEntity, Object.class);

        System.out.println("状态码:" + objectResponseEntity.getStatusCode());
        System.out.println("状态码内容:" + objectResponseEntity.getStatusCodeValue());
        HttpHeaders responseEntityHeaders = objectResponseEntity.getHeaders();
        System.out.println("响应头:" + responseEntityHeaders);
        MediaType contentType = objectResponseEntity.getHeaders().getContentType();
        System.out.println(contentType);
        System.out.println("消息响应内容:" + objectResponseEntity.getBody());


        Object body = objectResponseEntity.getBody();
        String s = FastJsonUtil.parseObjToJson(body);
        Map map = FastJsonUtil.parseJsonToObj(s, Map.class);
        //String result = restTemplate.postForObject(url, formEntity, String.class);
        //System.out.println(result);
        return AjaxResult.success(map);
    }


    @PostMapping("/post/form-data")
    public AjaxResult checkToken(@RequestParam("file") MultipartFile file, User user) {

        //模拟调用文件上传接口
        String url = "http://localhost:8081/test/filewithArgs/v1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        //设置ua
        headers.remove("User-Agent");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        MultiValueMap map = new LinkedMultiValueMap();

        map.add("id", user.getId());
        map.add("age", user.getAge());
        map.add("name", user.getName());
        map.add("gender", user.getGender());

        File tempFile = null;

        try {
            String originalFilename = file.getOriginalFilename();
            int i = originalFilename.lastIndexOf(".");
            String extension = originalFilename.substring(i, originalFilename.length());
            tempFile = File.createTempFile("temp", extension);
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        map.add("file", new FileSystemResource(tempFile));

        //组装请求体
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity(map, headers);

        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(url, entity, Object.class);

        System.out.println("状态码:" + objectResponseEntity.getStatusCode());
        System.out.println("状态码内容:" + objectResponseEntity.getStatusCodeValue());
        HttpHeaders responseEntityHeaders = objectResponseEntity.getHeaders();
        System.out.println("响应头:" + responseEntityHeaders);
        MediaType contentType = objectResponseEntity.getHeaders().getContentType();
        System.out.println(contentType);
        System.out.println("消息响应内容:" + objectResponseEntity.getBody());


        Object body = objectResponseEntity.getBody();
        String s = FastJsonUtil.parseObjToJson(body);
        Map data = FastJsonUtil.parseJsonToObj(s, Map.class);
        //String result = restTemplate.postForObject(url, formEntity, String.class);
        //System.out.println(result);
        return AjaxResult.success(data);
    }
}
