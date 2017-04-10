package controller.filter;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "SearchFilter")
public class SearchFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     * @param request 
     * @return
     */
    private ProductFilter parseProductFilter(ServletRequest request) {
        // TODO implement here
        return null;
    }

    /**
     * @param request 
     * @return
     */
    private CDFilter parseCDFilter(ServletRequest request) {
        // TODO implement here
        return null;
    }

    /**
     * @param request 
     * @return
     */
    private CactusFilter parseCactusFilter(ServletRequest request) {
        // TODO implement here
        return null;
    }
}