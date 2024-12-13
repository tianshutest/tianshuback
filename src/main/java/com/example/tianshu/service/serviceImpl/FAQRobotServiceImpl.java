package com.example.tianshu.service.serviceImpl;

import com.example.tianshu.Enum.QuestionEnum;
import com.example.tianshu.service.FAQRobotService;
import com.example.tianshu.service.InterfaceCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class FAQRobotServiceImpl implements FAQRobotService {

    @Resource
    private InterfaceCountService interfaceCountService;

    @Override
    public Map<String, Object> getAnswerById(String question_id) {
        Map<String, Object> result = new HashMap<>();
        try {
            int id = Integer.parseInt(question_id.trim());
            for (QuestionEnum question : QuestionEnum.values()) {
                if (Integer.parseInt(question.getQuestion()) == id) {
                    result.put("status", "success");
                    result.put("answer", question.getAnswer().getValue());
                    result.put("type", question.getAnswer().getType());
                    interfaceCountService.updateCount();
                    return result;
                }
            }
            result.put("status", "error");
            result.put("message", "无效的问题编号");
        } catch (NumberFormatException e) {
            result.put("status", "error");
            result.put("message", "问题编号必须是有效的整数");
        }
        return result;
    }
}
