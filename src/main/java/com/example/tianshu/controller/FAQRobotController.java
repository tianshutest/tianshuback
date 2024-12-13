package com.example.tianshu.controller;

import com.example.tianshu.client.FAQRobotClient;
import com.example.tianshu.service.FAQRobotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
@Slf4j
@RestController
@CrossOrigin(origins = {"http://www.tsjiaoyi.asia:10","http://wl.tsjiaoyi.asia:10","http://101.35.54.61:7531", "http://101.35.54.61:5500","http://127.0.0.1:5500","http://127.0.0.1:7531","http://wl.tsjiaoyi.asia:51385"})
@RequestMapping("/tianshu/FAQRobot")
public class FAQRobotController implements FAQRobotClient {
    @Resource
    private FAQRobotService faqRobotService;
//
//    @Autowired
//    private MessageProducer messageProducer;
//
//
//    public String sendMessage(String message) {
//        messageProducer.sendMessage(message);
//        return "Message sent successfully!";
//    }

    @Override
    public Map<String, Object> getAnswer(String question_id) {
        return faqRobotService.getAnswerById(question_id);
    }
}
