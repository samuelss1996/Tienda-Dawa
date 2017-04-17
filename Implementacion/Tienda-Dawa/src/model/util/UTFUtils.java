package model.util;

import javax.servlet.ServletRequest;
import java.nio.charset.StandardCharsets;

public class UTFUtils {

    public static String getParameter(ServletRequest req, String paramName) {
        String value = req.getParameter(paramName);
        return (value != null)? new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8) : null;
    }
}