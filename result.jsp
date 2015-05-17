<html>
<head><title>new_post</title>
    <style>
        body{font-family:"RobotoDraft","Roboto",'sans-serif','Open Sans';color: #404040;background-color: #ebebeb;padding: 0;margin: 0 auto;left: 50%;width: 1000px;}
        .aa{background-color: #364956;color: #ebebeb;padding: 10px;font-size: 60px;margin: 0 auto;}
        .btn{
            background-color: #364956;
            color: #ebebeb;
            height: 10px;
            width: 200px;
            padding: 3px;
            font-size: 12px;
            text-decoration: none;
            line-height: 24px;
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
        String result = request.getAttribute("result").toString();
    %>
        <div class="aa">ADD FEW LINES</div>
        <a style="padding-left:20px;padding-right:20px" class="mnubtn" href="/Blog/" >HOME</a><br>   
        
        
        <div class="tit">Add a new post to blogger :</div><br><br>
            
            <form method="POST" action="newpost">
                
                Post Title: <input type="text" name="title">
                <br><br><br>
                
                Post Content:<br><br>
                <textarea rows="10" cols="123" name="content"></textarea>
                <br><br><br>
                
                Author: <input type="text" name="author">
                <br><br>
                
                <input type="hidden" name="id" value=<%=result%>>
                
                <input type="submit" value="Submit">
            </form> 
</body>
</html>
