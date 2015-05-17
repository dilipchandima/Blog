<html>
<head><title>view_post</title>
    <style>
        body{
            font-family:"RobotoDraft","Roboto",'sans-serif','Open Sans';
            color: #404040;
            background-color: #ebebeb;
            padding: 0;
            margin: 0 auto;
            left: 50%;
            width: 1000px;
        }
        .aa{
            background-color: #364956;
            color: #ebebeb;
            padding: 10px;
            font-size: 60px;
            margin: 0 auto;
        }
        .mnubtn{
            background-color: #364956;
            color: #ebebeb;
            height: 50px;
            width: 200px;
            padding: 5px;
            font-size: 16px;
            text-decoration: none;
            line-height: 38px;
        }
        .tit{
            background-color: #16a085;
            color: #ebebeb;
            height: 30px;
            padding: 5px;
            font-size: 20px;
            text-decoration: none;
        }
    </style>
    </head>
<body>
    
        <%  
            String author = request.getAttribute("author").toString();
            String title = request.getAttribute("title").toString();
            String content = request.getAttribute("content").toString();
            String[] comments = (String[])request.getAttribute("comments");
            String[] appcomments = (String[])request.getAttribute("toapprove");
            String id = request.getAttribute("id").toString();
           
           int appcount = appcomments.length;
           int count = comments.length;
           
           %>
        <div class="aa">ADD FEW LINES</div>
        <a style="padding-left:20px;padding-right:20px" class="mnubtn"  href="/Blog/" >back to home</a><br>
        <h2  class="tit"><%=title %></h2>
        <p><%=content %></p>
        <h4>by - <%= author%></h4>
                                
            <h3 class="tit" style="color: #364956">COMMENTS BY USERS - <%=count%></h3><br>
            <% for(int i=0; i<count ; i++){%>
                <div><%=comments[i]%></div><br>
            <% } %>
            <br>
                
                
            
                
                <%if(appcount>0){%>
            <form method="POST" action="viewpost/admin/<%=id%>">
                
                <h3 class="tit" style="color: #364956">COMMENTS TO APPROVE -  <%=appcount%></h3><br>
                <% for(int i=0; i<appcount ; i++){%>
                    
                    <input type="checkbox" name="approvedcomment" value="<%=i%>"><%=appcomments[i]%></div><br>
                <% } %>
                <br>
                             
                <input type="submit" value="Submit">
            </form>
                    <%}%>
            
                                
        
</body>
</html>
