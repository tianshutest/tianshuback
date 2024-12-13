package com.example.tianshu.client;

import org.springframework.web.bind.annotation.GetMapping;


import java.util.Map;

public interface FAQRobotClient {
    @GetMapping("/QuestionAnswer")
    Map<String, Object> getAnswer(String question_id);

//    @PostMapping("/send-message")
//    String sendMessage(String message);
}
