package admin;

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


public class statics extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // read the last post id here .......................

        JSONParser parser = new JSONParser();
        JSONParser parserPost = new JSONParser();
        JSONObject list = null;
        JSONObject post = null;

        String[] idlist= new String[10];

        try {
            Object obj = parser.parse(new FileReader("..\\webapps\\Blog\\post\\list.json"));

            list = (JSONObject) obj;
            JSONArray msg = (JSONArray) list.get("list");

            int count = msg.size()-1;

            for(int i=0 ; i<10 ; i++){
                idlist[i] = msg.get(count--).toString();
            }


        } catch (Exception e) {

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get ID ......................");
            out.println(e);
            out.println("......................");
        }

        // read the post  here .............................

        try {

            String[] authorlist= new String[10];
            String[] titlelist= new String[10];
            String[] pidlist= new String[10];
            String[] contentlist= new String[10];

            for(int i=0; i<10; i++){
                Object objPost = parserPost.parse(new FileReader("..\\webapps\\Blog\\post\\"+idlist[i]+".json"));

                post = (JSONObject) objPost;
                titlelist[i]=post.get("title").toString();
                pidlist[i]=post.get("id").toString();
                contentlist[i]=post.get("content").toString();
                authorlist[i] = post.get("author").toString();

                if(contentlist[i].length()>300){
                    contentlist[i]= contentlist[i].substring(0,299)+"....";
                }
            }


            req.setAttribute("titlelist",titlelist);
            req.setAttribute("idlist",pidlist);
            req.setAttribute("contentlist",contentlist);
            req.setAttribute("authorlist",authorlist);

            req.getRequestDispatcher("/stat.jsp").forward(req,res);




        } catch (Exception e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get POST ......................");
            out.println(e);
            out.println("......................");
        }




    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


    }
}