/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.projects.project4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.projects.project4.model.commands.OpenMainCommand;

/**
 * Filter which throws user to his main page if session is available 
 * @author Sergiy Tolok
 */
public class SessionListiner implements Filter {

    
    public SessionListiner() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (null != session && (session.getAttribute("owner") != null 
                    || session.getAttribute("forester") != null
                    || session.getAttribute("admin") != null)) {
                OpenMainCommand command = new OpenMainCommand();
                command.setReqRes((HttpServletRequest) request, (HttpServletResponse) response);
                String url = command.perform();
                ((HttpServletResponse)response).sendRedirect(url);
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
        }
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SessionListiner(");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
}
