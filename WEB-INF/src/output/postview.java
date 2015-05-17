package output;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


public class postview extends HttpServlet {

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


                JSONArray arr = (JSONArray) post.get("comments");
                List<String> list = new ArrayList<String>();
                Iterator<String> iterator = arr.iterator();

                while (iterator.hasNext()) {
                    list.add(iterator.next());
                }

                int listsz = list.size();
                String[] comments = new String[listsz];
                for(int i=0;i<listsz;i++){
                    comments[i]=list.get(i);
                }

                req.setAttribute("title",posttitle);
                req.setAttribute("content",postcontent);
                req.setAttribute("author",postauthor);
                req.setAttribute("comments",comments);
                req.setAttribute("id",id);

                req.getRequestDispatcher("/view.jsp").forward(req,res);

            }


        } catch (Exception e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get POST ......................");
            out.println(e);
            out.println("......................");
        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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
                JSONArray msg = (JSONArray) post.get("toapprove");
                msg.add(req.getParameter("content"));

                post.remove("toapprove");
                post.put("toapprove", msg);

                File file = new File("..\\webapps\\Blog\\post\\"+id+".json");
                file.createNewFile();
                FileWriter filew = new FileWriter(file);
                filew.write(post.toJSONString());
                filew.flush();
                filew.close();

                doGet(req,res);
            }


        } catch (Exception e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get POST ......................");
            out.println(e);
            out.println("......................");
        }


    }
}