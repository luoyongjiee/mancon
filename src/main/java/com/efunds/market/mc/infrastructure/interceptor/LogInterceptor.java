package com.efunds.market.mc.infrastructure.interceptor;


import com.efunds.market.mc.common.util.JsonUtil;
import com.efunds.market.mc.infrastructure.context.ContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * created by root 2016/3/27
 * 功能：
 */
public class LogInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        long beginTime = System.currentTimeMillis();
        ContextHolder.ContextInfo info = new ContextHolder.ContextInfo();
        info.setBeginTime(beginTime);
        ContextHolder.setContextInfo(info);

        if (obj instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) obj;
            //类名

            String className = method.getBeanType().getSimpleName();
            //方法名
            String methodName = method.getMethod().getName();

            Enumeration<String> enums = request.getParameterNames();

            StringBuilder params = new StringBuilder();

            while (enums.hasMoreElements()) {
                String name = enums.nextElement();
                params.append(name).append("=").append(request.getParameter(name)).append("&");
            }

            logger.info("请求：{}-{}-{}", className, methodName, params.substring(0, params.length()));
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null)
            logger.info("响应：{}-{}", modelAndView.getViewName(), JsonUtil.toJson(modelAndView.getModel()));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("总耗时：{}",System.currentTimeMillis()- ContextHolder.getContextInfo().getBeginTime());
    }
}
