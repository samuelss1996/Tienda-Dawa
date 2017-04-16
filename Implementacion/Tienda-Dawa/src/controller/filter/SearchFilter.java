package controller.filter;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.helper.tax.TaxManagerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.Year;

@WebFilter(filterName = "SearchFilter", urlPatterns = "/stock")
public class SearchFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest.getParameter("type") != null) {
            switch(servletRequest.getParameter("type")) {
                case "ALL":
                    servletRequest.setAttribute("filter", this.parseProductFilter(servletRequest));
                    break;
                case "CD":
                    servletRequest.setAttribute("filter", this.parseCDFilter(servletRequest));
                    break;
                case "CACTUS":
                    servletRequest.setAttribute("filter", this.parseCactusFilter(servletRequest));
                    break;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * @param request 
     * @return
     */
    private ProductFilter parseProductFilter(ServletRequest request) {
        Float minPrice = (!this.isNullOrEmpty(request.getParameter("minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("minPrice"))) : null;
        Float maxPrice = (!this.isNullOrEmpty(request.getParameter("maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("maxPrice"))) : null;

        return new ProductFilter(request.getParameter("name"), minPrice, maxPrice);
    }

    /**
     * @param request 
     * @return
     */
    private CDFilter parseCDFilter(ServletRequest request) {
        Float minPrice = (!this.isNullOrEmpty(request.getParameter("minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("minPrice"))) : null;
        Float maxPrice = (!this.isNullOrEmpty(request.getParameter("maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("maxPrice"))) : null;
        Year minYear = (!this.isNullOrEmpty(request.getParameter("minCDYear")))? Year.parse(request.getParameter("minCDYear")) : null;
        Year maxYear = (!this.isNullOrEmpty(request.getParameter("maxCDYear")))? Year.parse(request.getParameter("maxCDYear")) : null;

        return new CDFilter(request.getParameter("name"), minPrice, maxPrice, request.getParameter("cdTitle"), request.getParameter("cdAuthor"),
                minYear, maxYear);
    }

    /**
     * @param request 
     * @return
     */
    private CactusFilter parseCactusFilter(ServletRequest request) {
        Float minPrice = (!this.isNullOrEmpty(request.getParameter("minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("minPrice"))) : null;
        Float maxPrice = (!this.isNullOrEmpty(request.getParameter("maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(request.getParameter("maxPrice"))) : null;

        return new CactusFilter(request.getParameter("name"), minPrice, maxPrice, request.getParameter("cactusSpecies"),
                request.getParameter("cactusOrigin"));
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}