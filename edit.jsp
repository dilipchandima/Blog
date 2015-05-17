<html>
<head><title>new_post</title>
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
        String id = request.getAttribute("id").toString();
    %>
        
        <div class="aa">ADD FEW LINES</div>
        <h1 class="tit" style="color: #364956">Edit your post :</h1>
        <p>This is your new post ID <%= id %></p>
            
            <form method="POST" action="newpost">
                
                Post Title: <input type="text" name="title" value=<%=title %> >
                <br><br><br>
                
                Post Content:<br><br>
                <textarea rows="10" cols="123" name="content" ><%=content %></textarea>
                <br><br><br>
                
                Author: <input type="text" name="author" value=<%=author %> >
                <br><br>
                
                <input type="hidden" name="id" value=<%=id%>>
                
                <input type="submit" value="Submit">
            </form> 
</body>
</html>
