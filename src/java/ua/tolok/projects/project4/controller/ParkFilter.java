package ua.tolok.projects.project4.controller;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * Filter set encoding in UTF-8
 * @author Sergiy Tolok
 */
public class ParkFilter implements Filter {

    private String encoding = "UTF-8";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ParkFilter() {
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
            if(request.getParameter("language")!=null){
                Locale locale = new Locale(request.getParameter("language"),request.getParameter("region"));
                request.setAttribute("locale", locale);
                ((HttpServletRequest)request).getSession().setAttribute("locale", locale);
            }
            String reqEncoding = request.getCharacterEncoding();
            //Set character encoding
            if (!encoding.equalsIgnoreCase(reqEncoding)) {
                ((HttpServletRequest)request).setCharacterEncoding(encoding);
                response.setCharacterEncoding(encoding);
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
        }
        
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
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
        String encodingParam = filterConfig.getInitParameter("encoding");
        this.filterConfig = filterConfig;
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ParkFilter()");
        }
        StringBuilder sb = new StringBuilder("ParkFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

}
