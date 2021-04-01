package org.wso2.custom.tenant.sso.redirector.ui;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This class acts as a servlet filter which forwads the requests coming for stratos-auth/redirect_ajaxprocessor.jsp
 * to stratos-custom-auth/redirect_ajaxprocessor.jsp
 */
public class CustomRedirectorJSPFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }
        String url = ((HttpServletRequest) servletRequest).getServletPath();
        if (url.contains("//")) {
            url = url.replace("//", "/");
        }
        url = url.replace("stratos-auth/redirect_ajaxprocessor.jsp", "stratos-custom-auth/redirect_ajaxprocessor.jsp");
        RequestDispatcher requestDispatcher =
                servletRequest.getRequestDispatcher(url);
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // not required to implement
    }

    public void destroy() {
        // not required to implement
    }
}
