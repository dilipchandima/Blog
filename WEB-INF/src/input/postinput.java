package input;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;


public class postinput extends HttpServlet {

    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssms");
        Date date = new Date();
        String id = dateFormat.format(date);

        req.setAttribute("result",id);
        req.getRequestDispatcher("/result.jsp").forward(req,res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String author = req.getParameter("author");
        String id = req.getParameter("id");

        JSONObject post = new JSONObject();
        JSONArray comments = new JSONArray();
        JSONArray toapprove = new JSONArray();

        post.put("title", title);
        post.put("content", content);
        post.put("id",id);
        post.put("author",author);
        post.put("comments",comments);
        post.put("toapprove",toapprove);


        // add post list to a JSON file >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        //////////////////////////////////////////////////
        JSONParser parser = new JSONParser();
        JSONObject list = null;
        try {

            String url = req.getRequestURI();
            String urlprt[]= url.split("/");
            int urlcount = urlprt.length -3;
            String editing = urlprt[urlcount];





            Object obj = parser.parse(new FileReader("..\\webapps\\Blog\\post\\list.json"));

            list = (JSONObject) obj;
            // loop array
            JSONArray msg = (JSONArray) list.get("list");

            if(!editing.equals("editpost")){
                msg.add(id);
            }
            list.remove("list");
            list.put("list",msg);


        } catch (Exception e) {

        }

        //////////////////////////////////////


        try {

            File file = new File("..\\webapps\\Blog\\post\\"+id+".json");
            file.createNewFile();
            FileWriter filew = new FileWriter(file);
            filew.write(post.toJSONString());
            filew.flush();
            filew.close();

            File filelist = new File("..\\webapps\\Blog\\post\\list.json");
            filelist.createNewFile();
            FileWriter fileww = new FileWriter(filelist);
            fileww.write(list.toJSONString());
            fileww.flush();
            fileww.close();



        } catch (IOException e) {

        }



        // Set response content type
        res.setContentType("text/html");

        // New location to be redirected
        String site = new String("http://localhost:8080/Blog/");

        res.setStatus(res.SC_MOVED_TEMPORARILY);
        res.setHeader("Location", site);

    }
}