package com.wen.base.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author wsw
 * @Date 2023/2/2 17:28
 **/
public class RequestParameterWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params;

    public RequestParameterWrapper(HttpServletRequest request) {
        super(request);
        this.params = new HashMap();
        this.params.putAll(request.getParameterMap());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return this.params;
    }

    public RequestParameterWrapper(HttpServletRequest request, Map<String, Object> extraParams) {
        this(request);
        this.addParameters(extraParams);
    }

    public void addParameters(Map<String, Object> extraParams) {
        Iterator var2 = extraParams.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var2.next();
            this.addParameter((String) entry.getKey(), entry.getValue());
        }

    }

    @Override
    public String getParameter(String name) {
        String[] values = (String[]) this.params.get(name);
        return values != null && values.length != 0 ? values[0] : null;
    }

    @Override
    public String[] getParameterValues(String name) {
        return (String[]) this.params.get(name);
    }

    public void addParameter(String name, Object value) {
        if (!this.params.containsKey(name) && value != null) {
            if (value instanceof String[]) {
                this.params.put(name, (String[]) ((String[]) value));
            } else if (value instanceof String) {
                this.params.put(name, new String[]{(String) value});
            } else {
                this.params.put(name, new String[]{String.valueOf(value)});
            }
        }

    }
}
