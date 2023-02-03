package com.wen.base.filter;

import com.wen.base.wrapper.RequestParameterWrapper;
import org.springframework.core.Ordered;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wsw
 * @Date 2023/2/2 17:59
 **/
public class BasisParamFilter extends OncePerRequestFilter implements Ordered {
    public BasisParamFilter() {
    }

    @Override
    public int getOrder() {
        return -2147483643;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> urlAddParams = new HashMap();
        String operatorId = parameterMap.containsKey("operator_id") ? ((String[])parameterMap.get("operator_id"))[0] : null;
        if (!ObjectUtils.isEmpty(operatorId) && !parameterMap.containsKey("operatorId")) {
            urlAddParams.put("operatorId", operatorId);
        }

        String authUserId = parameterMap.containsKey("auth_user_id") ? ((String[])parameterMap.get("auth_user_id"))[0] : null;
        if (!ObjectUtils.isEmpty(authUserId) && !parameterMap.containsKey("authUserId")) {
            urlAddParams.put("authUserId", authUserId);
        }

        String authName = parameterMap.containsKey("auth_name") ? ((String[])parameterMap.get("auth_name"))[0] : null;
        if (!ObjectUtils.isEmpty(authName) && !parameterMap.containsKey("authName")) {
            urlAddParams.put("authName", authName);
        }

        if (urlAddParams.size() > 0) {
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            requestParameterWrapper.addParameters(urlAddParams);
            filterChain.doFilter(requestParameterWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
