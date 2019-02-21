<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
    <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="">
            <meta name="author" content="">
        
            <!-- Bootstrap Core CSS -->
            <link href="css/bootstrap.min.css" rel="stylesheet">
            <!-- Favicon -->
            <link rel="icon" href="img/favicon-java.png" />
             <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
             <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
             <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

            <script>
                $(document).ready(function(){
                    $('#btn-unfollow').click(function(){
                    console.log('Oi to aqui');
                        $('#card-unfollow').hide('fast');
                    });
                });
            </script>
    </head>
    <body>
    <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">Followme Bitch!</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Services</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Contact</a>
                    </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <!-- Jumbotron Header -->
             <header class="jumbotron my-4">
             </header>

            <div class="row">
                <c:forEach var="user" items="${list}">
                <div id="${user.pk}" class="col-lg-3 col-md-2 mb-3">
                    <div class="card" style="width:270px">
                        <img class="card-img-top" src=${user.profile_pic_url} alt="Card image">
                        <div class="card-body">
                        <h4 class="card-title">${user.username}</h4>
                        <p class="card-text">${user.full_name}</p>
                        <a href="#" id="btn-unfollow${user.pk}" class="btn btn-primary">UnFollow</a>
                        </div>
                    </div>
                     <script>
                        $(document).ready(function(){
                            $('#btn-unfollow${user.pk}').click(function(){
                                $('#${user.pk}').fadeOut('slow');
                             });
                        });
                     </script>
                    </div>
                </c:forEach>
            </div>
        </div>

        <footer class="py-5 bg-dark">
            <div class="container">
              <p class="m-0 text-center text-white">Copyright &copy; <a href="https://github.com/JohnGomez/follow">John Gomes 2019</a></p>
            </div>
            <!-- /.container -->
          </footer>

      </body>
</html>