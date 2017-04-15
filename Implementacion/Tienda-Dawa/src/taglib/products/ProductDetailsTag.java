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
    private String type;
    private Product product;

    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        try {
            switch (EProductType.valueOf(this.type)) {
                case CD:
                    out.print("<p><strong>Código: </strong>" + ((CD)this.product).getId() + "</p>\n" +
                            "  <p><strong>Título: </strong>" + ((CD)this.product).getTitle() + "</p>\n" +
                            "  <p><strong>Artista: </strong>" + ((CD)this.product).getAuthor() + "</p>\n" +
                            "  <p><strong>Año: </strong>" + ((CD)this.product).getYear() + "</p>");
                    break;
                case CACTUS:
                    out.print("<p><strong>Especie: </strong>" + ((Cactus)this.product).getSpecies() + "</p>\n" +
                              "<p><strong>Origen: </strong>" + ((Cactus)this.product).getOrigin() + "</p>\n");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
