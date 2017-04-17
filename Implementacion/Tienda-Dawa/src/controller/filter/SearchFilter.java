package controller.filter;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.helper.tax.TaxManagerFactory;
import model.util.UTFUtils;

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
        if(UTFUtils.getParameter(servletRequest, "type") != null) {
            switch(UTFUtils.getParameter(servletRequest, "type")) {
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
        Float minPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "minPrice").replace(",", "."))) : null;
        Float maxPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "maxPrice").replace(",", "."))) : null;

        return new ProductFilter(UTFUtils.getParameter(request, "name"), minPrice, maxPrice);
    }

    /**
     * @param request 
     * @return
     */
    private CDFilter parseCDFilter(ServletRequest request) {
        Float minPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "minPrice").replace(",", "."))) : null;
        Float maxPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "maxPrice").replace(",", "."))) : null;
        Year minYear = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "minCDYear")))? Year.parse(UTFUtils.getParameter(request, "minCDYear")) : null;
        Year maxYear = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "maxCDYear")))? Year.parse(UTFUtils.getParameter(request, "maxCDYear")) : null;

        return new CDFilter(UTFUtils.getParameter(request, "name"), minPrice, maxPrice, UTFUtils.getParameter(request, "cdTitle"), UTFUtils.getParameter(request, "cdAuthor"),
                minYear, maxYear);
    }

    /**
     * @param request 
     * @return
     */
    private CactusFilter parseCactusFilter(ServletRequest request) {
        Float minPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "minPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "minPrice").replace(",", "."))) : null;
        Float maxPrice = (!this.isNullOrEmpty(UTFUtils.getParameter(request, "maxPrice")))?
                TaxManagerFactory.getTaxManager(request).revert(Float.valueOf(UTFUtils.getParameter(request, "maxPrice").replace(",", "."))) : null;

        return new CactusFilter(UTFUtils.getParameter(request, "name"), minPrice, maxPrice, UTFUtils.getParameter(request, "cactusSpecies"),
                UTFUtils.getParameter(request, "cactusOrigin"));
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}