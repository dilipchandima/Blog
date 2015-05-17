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
            String id = request.getAttribute("id").toString();
           
           int count = comments.length;
           
           %>
        <div class="aa">ADD FEW LINES</div>
        <a style="padding-left:20px;padding-right:20px" class="mnubtn" href="/Blog/" >HOME</a><br> 
            
        <br><div style=" background-color: #364956;height:2px"></div>
        <div class="tit"><%=title %></div>
        <p><%=content %></p>
        <h4>by - <%= author%></h4>
            <div style=" background-color: #364956;height:2px"></div><br>          
            <h3 class="tit" style="color: #364956">COMMENTS BY USERS - <%=count%></h3><br>
            <% for(int i=0; i<count ; i++){%>
                <div><%=comments[i]%></div><br>
            <% } %>
            <br>
            
                
                
                
                
            <form method="POST" action="viewpost/post/<%=id%>">
                <div style=" background-color: #364956;height:2px"></div><br>
                Add your comment:<br><br>
                <textarea rows="10" cols="121" name="content"></textarea>
                <br><br>
                <input type="hidden" name="id" value="cc">                
                <input  type="submit" value="Submit">
            </form>
            
                                
        
</body>
</html>
