package com.example.tianshu.utils;

import com.example.tianshu.dao.model.InterfaceCountsDTO;
import com.example.tianshu.service.InterfaceCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class VisitorFilter extends OncePerRequestFilter {

    private static final long VISIT_INTERVAL = 3;  // 3 seconds
    private final Map<String, Lock> ipLocks = new ConcurrentHashMap<>();


    @Autowired
    private InterfaceCountService interfaceCountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取真实的客户端IP地址
        String clientIp = getClientIp(request);
        InterfaceCountsDTO interfaceCountsDTO = new InterfaceCountsDTO();
        interfaceCountsDTO.setIp(clientIp);
        interfaceCountsDTO.setVisitDate(LocalDate.now());
        LocalDateTime currentTime = LocalDateTime.now();
        Lock lock = ipLocks.computeIfAbsent(clientIp, k -> new ReentrantLock());
        lock.lock();  // 获取锁

        // 查找当天访问记录
        InterfaceCountsDTO interfaceCountsDTOS = interfaceCountService.findByIpAndVisitDate(interfaceCountsDTO);

        try {
            // 获取当天访问记录
            InterfaceCountsDTO interfaceCounts = new InterfaceCountsDTO();
            interfaceCounts.setIp(clientIp);
            interfaceCounts.setVisitDate(LocalDate.now());

            LocalDateTime lastVisitTime = interfaceCountsDTOS != null ? interfaceCountsDTOS.getLastVisitTime() : null;

            if (interfaceCountsDTOS == null) {
                // 如果当天没有记录，创建新的记录
                interfaceCounts.setVisitCount(1);
                interfaceCounts.setLastVisitTime(currentTime);
                interfaceCountService.insertVisit(interfaceCounts);
            } else if (Duration.between(lastVisitTime, currentTime).getSeconds() > VISIT_INTERVAL) {
                // 如果有记录并且超过时间间隔，更新访问数据
                interfaceCountsDTO.setId(interfaceCountsDTOS.getId());
                interfaceCountsDTO.setVisitCount(interfaceCountsDTOS.getVisitCount() + 1);
                interfaceCountsDTO.setLastVisitTime(currentTime);
                interfaceCountService.updateVisit(interfaceCountsDTO);
            }
        } finally {
            lock.unlock();  // 释放锁
        }
//        interfaceCounts.setVisitCount(interfaceCountsDTOS.getVisitCount() + 1);
//        // 更新最后访问时间
//        interfaceCountsDTO.setLastVisitTime(LocalDateTime.now());

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
