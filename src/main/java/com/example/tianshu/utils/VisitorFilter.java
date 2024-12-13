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
        String ip = request.getRemoteAddr();
        LocalDate date = LocalDate.now();
        Visitor visitor = visitorRepository.findByIpAndVisitDate(ip, date);

        if (visitor == null) {
            visitor = new Visitor();
            visitor.setIp(ip);
            visitor.setVisitCount(1L);
            visitor.setVisitDate(date);
        } else {
            visitor.setVisitCount(visitor.getVisitCount() + 1);
        }

        // 更新最后访问时间
        visitor.setLastVisitTime(LocalDateTime.now());

        visitor.setVisitDate(LocalDate.now());

        visitorRepository.save(visitor);

        filterChain.doFilter(request, response);
    }

}
