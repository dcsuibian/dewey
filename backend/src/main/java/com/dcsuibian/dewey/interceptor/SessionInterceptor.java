package com.dcsuibian.dewey.interceptor;

import com.dcsuibian.dewey.vo.SessionVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器，用于给所有请求添加SessionVo
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        SessionVo sessionVo = (SessionVo) httpSession.getAttribute("session");
        if (null == sessionVo) {
            httpSession.setAttribute("session", new SessionVo());
        }
        return true;
    }
}
