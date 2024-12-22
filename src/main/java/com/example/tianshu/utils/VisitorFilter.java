package com.example.tianshu.utils;

import com.example.tianshu.dao.model.Visitor;
import com.example.tianshu.service.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class VisitorFilter extends OncePerRequestFilter {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取真实的客户端IP地址
        String ip = getClientIp(request);

        LocalDate date = LocalDate.now();
        try{
        // 查找当天访问记录
        Visitor visitor = visitorRepository.findByIpAndVisitDate(ip, date);

        if ("127.0.0.1".equals(ip)) {
            filterChain.doFilter(request, response);
            return; // 跳过记录操作，直接执行后续过滤器链
        }

        synchronized (this) {
            if (visitor == null) {
                // 如果当天没有记录，创建新的记录
                visitor = new Visitor();
                visitor.setIp(ip);
                visitor.setVisitCount(1L);
                visitor.setVisitDate(date);
            } else {
                // 如果已有记录，则更新访问计数
                visitor.setVisitCount(visitor.getVisitCount() + 1);
            }
        }

        // 更新最后访问时间
        visitor.setLastVisitTime(LocalDateTime.now());

        // 尝试保存，捕获异常并更新记录
            visitorRepository.saveAndFlush(visitor);  // 使用saveAndFlush确保数据立即提交

            // 发生唯一约束冲突，表示记录已存在，执行更新操作
            // 继续执行后续操作，确保即使发生冲突也不阻止访问
            visitor = visitorRepository.findByIpAndVisitDate(ip, date);
            if (visitor != null) {
                visitor.setVisitCount(visitor.getVisitCount() + 1);
                visitorRepository.saveAndFlush(visitor);  // 强制提交更新
            }
        } catch (IOException e) {
            filterChain.doFilter(request, response);
            return; // 跳过记录操作，直接执行后续过滤器链
        }

        // 继续执行后续过滤器链，允许正常访问页面
        filterChain.doFilter(request, response);
    }

    // 提取真实的客户端 IP 地址
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
