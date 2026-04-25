package com.ramilastanli.job_search_api.config;

import com.ramilastanli.job_search_api.service.RateLimitService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimitService rateLimitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteAddr(); // İstifadəçinin IP-sini götürürük
        Bucket bucket = rateLimitService.resolveBucket(ip);

        if (bucket.tryConsume(1)) { // 1 jeton götürməyə çalışırıq
            return true; // Jeton varsa, davam et
        } else {
            // Jeton yoxdursa, 429 xətası qaytarırıq
            response.setStatus(429);
            response.getWriter().write("Həddindən artıq sorğu göndərildi. Zəhmət olmasa 1 dəqiqə gözləyin.");
            return false;
        }
    }
}