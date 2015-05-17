
<html>
<head>
    
    <title>blogger</title>
    
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
            String[] title = (String[])request.getAttribute("titlelist");
            String[] id = (String[])request.getAttribute("idlist");
            String[] author = (String[])request.getAttribute("authorlist");
            String[] content = (String[])request.getAttribute("contentlist");
        %>
    
            <div class="aa">ADD FEW LINES</div>
             <a style="padding-left:20px;padding-right:20px" class="mnubtn" href="/Blog/statics" >ADMIN ZONE</a><br>     
        
        <% for(int i=0; i<10 ; i++){%>
            <div style=" background-color:  #ea141f;height:px"></div>
            <h2 class="tit">
            <%=title[i] %></h2>
            <p><%=content[i] %></p>
            <p>by- <%=author[i] %></p>
            <a class="btn" href="/Blog/viewpost/post/<%=id[i]%>" >readmore</a><br>
        <% } %>
        
        <br><br><div>created by Jayalath J.D.C vs Weerarathne I.L</div>
                        
</body>
</html>
