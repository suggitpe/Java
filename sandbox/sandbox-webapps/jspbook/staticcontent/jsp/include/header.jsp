<%! int pageCount = 0;
   void addCount()
   {
       ++pageCount;
   }
%> <% addCount(); %>
<html>
    <head>
        <title>Header/footer example</title>
        <link rel="stylesheet" type="text/css" href="/format.css"/>
    </head>
    <body>
        <center>
            <h2>Header and footer example</h2>
            <a href="http://sandbox">The local sandbox</a> - <a href="/jspbook/ShowAppSource">Show the local site source code</a><br/>
            This site has been visited <%= pageCount %> times.
        </center>
        <br/><br/>