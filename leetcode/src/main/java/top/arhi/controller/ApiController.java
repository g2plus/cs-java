package top.arhi.controller;

import lombok.AllArgsConstructor;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.arhi.service.ColorService;
import top.arhi.service.OcrService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {
    @Resource
    private ColorService colorService;

    private final OcrService ocrService;

    @GetMapping("/color")
    public ResponseEntity getRandomColors() {
        Map map = new HashMap(16);
        map.put("code","200");
        map.put("data",colorService.getRandomColors(10));
        return ResponseEntity.ok(map);
    }

    @PostMapping(value = "/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String recognizeImage(@RequestParam("file") MultipartFile file) throws TesseractException, IOException {

        // 调用OcrService中的方法进行文字识别
        return ocrService.recognizeText(file);
    }
}