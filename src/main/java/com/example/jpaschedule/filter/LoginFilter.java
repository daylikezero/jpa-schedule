package com.example.jpaschedule.filter;

import com.example.jpaschedule.common.Const;
import com.example.jpaschedule.filter.context.MemberContext;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = new String[]{"/api/v1/members/signup", "/api/v1/login", "/api/v1/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // TODO 사용 여부 확인 후 삭제
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("requestURI: {}", requestURI);

        if(!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute(Const.SESSION_KEY) == null) {
                throw new RuntimeException("로그인이 필요한 요청입니다.");
            }
            MemberContext.setMemberId((Long) session.getAttribute(Const.SESSION_KEY));
            log.info("로그인 성공");
        }
        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
