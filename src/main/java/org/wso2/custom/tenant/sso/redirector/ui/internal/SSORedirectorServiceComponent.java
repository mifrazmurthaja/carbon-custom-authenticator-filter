package org.wso2.custom.tenant.sso.redirector.ui.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.custom.tenant.sso.redirector.ui.CustomRedirectorJSPFilter;

@Component(
        name = "org.wso2.custom.sso.redirector.ui",
        immediate = true
)
public class SSORedirectorServiceComponent {

    private static Log log = LogFactory.getLog(SSORedirectorServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {
        // register a servlet filter for SSO redirector page
        HttpServlet redirectJSPRedirectorServlet = new HttpServlet() {

            protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                    IOException {

            }
        };
        Filter redirectPageFilter = new CustomRedirectorJSPFilter();
        Dictionary redirectorPageFilterAttrs = new Hashtable(2);
        Dictionary redirectorPageFilterParams = new Hashtable(2);
        redirectorPageFilterParams.put("url-pattern", "/carbon/stratos-auth/redirect_ajaxprocessor.jsp");
        redirectorPageFilterParams.put("associated-filter", redirectPageFilter);
        redirectorPageFilterParams.put("servlet-attributes", redirectorPageFilterAttrs);
        ctxt.getBundleContext().registerService(Servlet.class.getName(), redirectJSPRedirectorServlet,
                redirectorPageFilterParams);
        log.debug("Stratos SSO Redirector bundle is activated..");
    }

    @Deactivate
    protected void deactivate(ComponentContext ctxt) {

        log.debug("Stratos SSO Redirector bundle is deactivated..");
    }
}
