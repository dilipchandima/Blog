package edit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class postedit extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // read the last post id here .......................
        String url = req.getRequestURI();
        String urlprt[]= url.split("/");
        int urlcount = urlprt.length -1;

        JSONParser parserPost = new JSONParser();
        JSONObject post = null;
        String id = urlprt[urlcount];

        // read the post  here .............................

        try {

            if(id != null){
                Object objPost = parserPost.parse(new FileReader("..\\webapps\\Blog\\post\\"+id+".json"));

                post = (JSONObject) objPost;
                String postauthor = post.get("author").toString();
                String posttitle = post.get("title").toString();
                String postcontent = post.get("content").toString();
                String postid = post.get("id").toString();

                req.setAttribute("title",posttitle);
                req.setAttribute("content",postcontent);
                req.setAttribute("author",postauthor);
                req.setAttribute("id",postid);

                req.getRequestDispatcher("/edit.jsp").forward(req,res);

            }


        } catch (Exception e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get POST ......................");
            out.println(e);
            out.println("end of the exception......................");
        }


    }
}