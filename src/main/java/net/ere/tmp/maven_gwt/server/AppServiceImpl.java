package net.ere.tmp.maven_gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import net.ere.tmp.maven_gwt.shared.AppService;
import net.ere.tmp.maven_gwt.shared.BlogItem;
import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.model.Author;
import net.ere.tmp.maven_gwt.spring.model.Blog;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

// @WebServlet(name = "appServlet", urlPatterns = { "/App/app_service" })
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    private static final long serialVersionUID = 1L;

    private Logger log = Logger.getLogger(AppServiceImpl.class.getName());

    private TimeService timeService;

    private <T> T getBean(java.lang.Class<T> aClass) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        return ctx.getBean(aClass);
    }

    @Override
    public Date getTime() {
        TimeService timeService = getBean(TimeService.class);

        Date time = timeService.getTime();

        log.info("Call getTime(): " + timeService.getTimeAsString());
        return time;
    }

    @Override
    public String getPropertyValue(String propertyKey) {
        log.info("Call getPropertyValue(" + propertyKey + ")");
        return System.getProperty(propertyKey);
    }

    @Override
    public void createAuthor(String name, String blogTitle, String blogText) {
        try {
            DataService dataService = getBean(DataService.class);

            Author author = new Author(name);
            Blog blog = new Blog(blogTitle, blogText);

            author.getBlogs().add(blog);
            blog.setAuthor(author);

            dataService.persistAuthor(author);

        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public List<BlogItem> getAllBlogItem() {
        List<BlogItem> list = new ArrayList<>();
        DataService dataService = getBean(DataService.class);

        List<Author> allAuthors = dataService.getAllAuthors();

        for (Author a : allAuthors) {
            for (Blog blog : a.getBlogs()) {
                list.add(new BlogItem(a.getName(), blog.getTitle(), blog.getContent()));
            }
        }

        return list;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String simpleParam = getServletConfig().getInitParameter("simpleInitParam");
        String param1 = req.getParameter("param1");

        if (param1 != null && !param1.isEmpty()) {
            out.println("Hello World '" + simpleParam + "' : " + param1);
        } else {
            out.println("{");
            out.println("{\"id\":12934563,\"name\":\"Bill Smith\",\"email\":\"bill.smith@web.com\",\"phoneNumber\":\"32125551212\"}\",");
            out.println("{\"id\":12934564,\"name\":\"Bill Smith 2\",\"email\":\"bill.smith2@web.com\",\"phoneNumber\":\"321255512142\"}\"");
            out.println("{\"id\":12934564,\"name\":\"Bill Smith 2\",\"email\":\"bill.smith2@web.com\",\"phoneNumber\":\"321255512142\"}\"");
            out.println("{\"id\":12934564,\"name\":\"Bill Smith 2\",\"email\":\"bill.smith2@web.com\",\"phoneNumber\":\"321255512142\"}\"");
            out.println("{\"id\":12934564,\"name\":\"Bill Smith 2\",\"email\":\"bill.smith2@web.com\",\"phoneNumber\":\"321255512142\"}\"");
            out.println("}");
        }

        out.close();
    }
}
