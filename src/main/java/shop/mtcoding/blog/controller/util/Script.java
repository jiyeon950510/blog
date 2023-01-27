package shop.mtcoding.blog.controller.util;

import java.nio.file.Path;

public class Script {
    public static String back(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    public static String href(String msg, String path) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.href='" + path + "';");
        sb.append("</script>");
        return sb.toString();
    }

    public static String href(String path) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("history.hrep='" + path + "';");
        sb.append("</script>");
        return sb.toString();
    }
}
