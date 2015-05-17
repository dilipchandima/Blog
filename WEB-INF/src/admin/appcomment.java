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


public class appcomment extends HttpServlet {

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

            //get approved comments ..................................................
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

            //get for approve comments ..................................................
                JSONArray arr1 = (JSONArray) post.get("toapprove");
                List<String> list1 = new ArrayList<String>();
                Iterator<String> iterator1 = arr1.iterator();

                while (iterator1.hasNext()) {
                    list1.add(iterator1.next());
                }

                int listsz1 = list1.size();
                String[] commentsapp = new String[listsz1];
                for(int i=0;i<listsz1;i++){
                    commentsapp[i]=list1.get(i);
                }
                ///////////////////////////////////////////////////////////

                req.setAttribute("title",posttitle);
                req.setAttribute("content",postcontent);
                req.setAttribute("author",postauthor);
                req.setAttribute("comments",comments);
                req.setAttribute("toapprove",commentsapp);
                req.setAttribute("id",id);

                req.getRequestDispatcher("/viewadmin.jsp").forward(req,res);

            }


        } catch (Exception e) {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("get GET ......................");
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
                JSONArray msg = (JSONArray) post.get("comments");
                JSONArray toapprove = (JSONArray) post.get("toapprove");

                // get approved list of numbers /////////////////////////////////////////////////
                String[] approved = req.getParameterValues("approvedcomment");
                int[] approvedcom = new int[approved.length];

                for(int i=0;i <approved.length; i++){
                    approvedcom[i] = Integer.parseInt(approved[i]);
                }
                res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                out.println(" ......................");

                // add approved comments to comment list //////////////////////
                for(int i=0;i <toapprove.size(); i++){
                    msg.add(toapprove.get(approvedcom[i]));
                    toapprove.remove(approvedcom[i]);
                    out.println(i);
                }


                // remove past arrays and put new ones /////////////////////////////////
                post.remove("comments");
                post.remove("toapprove");
                post.put("comments", msg);
                post.put("toapprove",toapprove);

                // save the JSON //////////////////////////////////////////////////
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