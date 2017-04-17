package model.helper.tax;

import javax.servlet.ServletRequest;

public class TaxManagerFactory {

    public static TaxManager getTaxManager(ServletRequest request) {
        switch((String) request.getAttribute("country")) {
            case "ES":
                return new SpainTaxManager();
            case "GB":
                return new UKTaxManager();
        }

        return null;
}

}