package com.example.jpaschedule.config.filter;

import com.example.jpaschedule.common.Const;
import com.example.jpaschedule.config.context.MemberContext;
import com.example.jpaschedule.exception.CustomException;
import com.example.jpaschedule.exception.ErrorCode;
import com.example.jpaschedule.exception.ErrorResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("requestURI: {}", requestURI);

        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute(Const.SESSION_KEY) == null) {
                loginFilterExceptionHandler(httpResponse);
                return;
            }
            MemberContext.setMemberId((Long) session.getAttribute(Const.SESSION_KEY));
        }
        chain.doFilter(request, response);
    }

    private static void loginFilterExceptionHandler(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String json = new ObjectMapper()
                .writeValueAsString(ErrorResponseDto.errResponseEntity(new CustomException(ErrorCode.UNAUTHORIZED, "로그인이 필요한 요청입니다.")));
        response.getWriter().write(json);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
