package taglib.products;

import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import java.io.IOException;

/**
 * Created by Cristofer Canosa Domínguez on 15/04/2017.
 */
public class ProductDetailsTag extends SimpleTagSupport {

    private EProductType type;

    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        Product item = (Product)pageContext.getRequest().getAttribute("item");
        try {
            switch (type) {
                case CD:
                    out.print("<p>" + ((CD)item).getTitle() + "</p>\n" +
                            "  <p>" + ((CD)item).getAuthor() + "</p>\n" +
                            "  <p>" + ((CD)item).getYear() + "</p>");
                    break;
                case CACTUS:
                    out.print("<p>" + ((Cactus)item).getSpecies() + "</p>\n" +
                              "<p>" + ((Cactus)item).getOrigin() + "</p>\n");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setType(EProductType type) {
        this.type = type;
    }
}
