<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Liquor Store - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css2?family=Spectral:ital,wght@0,200;0,300;0,400;0,500;0,700;0,800;1,200;1,300;1,400;1,500;1,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/style.css">
    
      <link rel="stylesheet" href="assets/css/style.css">
      
      
  </head>
  <body>

  	<div class="wrap">
			<div class="container">
				<div class="row">
					<div class="col-md-6 d-flex align-items-center">
						<p class="mb-0 phone pl-md-2">
							<a href="#" class="mr-2"><span class="fa fa-phone mr-1"></span> 010-2222-2222</a> 
							<a href="#"><span class="fa fa-paper-plane mr-1"></span> bookloveda@gmail.com</a>
						</p>
					</div>
					<div class="col-md-6 d-flex justify-content-md-end">
						<div class="social-media mr-4">
			    		<p class="mb-0 d-flex">
			    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-facebook"><i class="sr-only">Facebook</i></span></a>
			    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-twitter"><i class="sr-only">Twitter</i></span></a>
			    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-instagram"><i class="sr-only">Instagram</i></span></a>
			    			<a href="#" class="d-flex align-items-center justify-content-center"><span class="fa fa-dribbble"><i class="sr-only">Dribbble</i></span></a>
			    		</p>
		        </div>
		        <div class="reg">
		        	<p class="mb-0"><a href="./signUp.book" class="mr-2">SIGN UP</a> <a href="./loginForm.book">LOG IN</a></p>
		        </div>
					</div>
				</div>
			</div>
		</div>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="index.html">BookLove <span>多</span></a>
<!-- 	 
     <div class="order-lg-last btn-group">
          <a href="#" class="btn-cart dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	<span class="flaticon-shopping-bag"></span>
          	<div class="d-flex justify-content-center align-items-center"><small>3</small></div>
          </a>
          <div class="dropdown-menu dropdown-menu-right">
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(images/prod-1.jpg);"></div>
				    	<div class="text pl-3">
				    		<h4>Bacardi 151</h4>
				    		<p class="mb-0"><a href="#" class="price">$25.99</a><span class="quantity ml-3">Quantity: 01</span></p>
				    	</div>
				    </div>
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(images/prod-2.jpg);"></div>
				    	<div class="text pl-3">
				    		<h4>Jim Beam Kentucky Straight</h4>
				    		<p class="mb-0"><a href="#" class="price">$30.89</a><span class="quantity ml-3">Quantity: 02</span></p>
				    	</div>
				    </div>
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(images/prod-3.jpg);"></div>
				    	<div class="text pl-3">
				    		<h4>Citadelle</h4>
				    		<p class="mb-0"><a href="#" class="price">$22.50</a><span class="quantity ml-3">Quantity: 01</span></p>
				    	</div>
				    </div>
				    <a class="dropdown-item text-center btn-link d-block w-100" href="cart.html">
				    	View All
				    	<span class="ion-ios-arrow-round-forward"></span>
				    </a>
				  </div>
        </div> 
        
        -->

	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="index.html" class="nav-link">Home</a></li>
	          
	          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle"id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Resell</a>
              <div class="dropdown-menu" aria-labelledby="dropdown04">

                <a class="dropdown-item" href="./bookDealList.book?category=1">문학</a>
                <a class="dropdown-item" href="./bookDealList.book?category=2">인문</a>
                <a class="dropdown-item" href="./bookDealList.book?category=3">사회/과학/예술</a>
                <a class="dropdown-item" href="./bookDealList.book?category=4">학습/수험서/자격증</a>
                <a class="dropdown-item" href="./bookDealList.book?category=5">만화/외국서적</a>
                <a class="dropdown-item" href="./bookDealList.book?category=6">기타</a>
              </div>
            </li>
	          <li class="nav-item"><a href="./bookReviewList.book" class="nav-link">Review</a></li>
	          <li class="nav-item"><a href="./BookOfflineList.book" class="nav-link">Club-Offline</a></li>
	          <c:if test="${ sessionScope.memID != null }">
	          	<li class="nav-item"><a href="./contact.book" class="nav-link">My Page</a></li>
		        </c:if>
		        <c:if test="${ sessionScope.memID == null }">
		        <li class="nav-item"><a href="./loginForm.book" class="nav-link">My Page</a></li>
		        </c:if>
		        <li class="nav-item"><a href="./search.book" class="nav-link"><img alt="search" src="images/search2.png" width="20"></a></li>
	        </ul>	
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->
    
    <section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_22.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-center">
          <div class="col-md-9 ftco-animate mb-5 text-center">
          	<p class="breadcrumbs mb-0"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>search<i class="fa fa-chevron-right"></i></span></p>
            <h2 class="mb-0 bread">SEARCH</h2>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section bg-light">
			<div class="container">
				<div class="row justify-content-center">
					
					
					
					
    		
    
				
			<div align="center" style="width:1170px;">
    		
    			<h3 class="mb-0 bread">Book Search</h3>
				<br>
           		<div class="form-group"  style="width:1000px; padding-bottom: 50px;">
           
            <input type="text" id="bookName" placeholder="   검색할 책 제목을 입력하세요."style="width:800px; height: 50px; border-color: #808080">
             <button id="search" style="border: 0px;"><img src="./images/search4.png" width="40px" height="40px" id="search"></button>
                  
         <!--    <button id="search">검색</button>   -->    
                  
                  
                  
                </div>
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		
    		<!-- 
    		    <h1>책 제목을 검색해주세요.</h1>
    <input id="bookName" type="text">
    <button id="search">검색</button>
    <p></p>
    <div> -->
    
    <form action="result.jsp" method="get">
		<table cellpadding="0" cellspacing="0" id ="table" >
			
		
			
		</table>
	</form>
	
	
	<a href="./search.book" >
	<br><br><br>
			<p>게시글 검색 하러 가기</p>
   			 </a>
    
    </div>

    		
    		
    		
    		
    		
    		
    			
					</div>
					
					
					 
					
						 
					
					
			
    <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>

    <script>
        $(function () {

            $("#search").click(function () {

                $.ajax({
                    method: "GET",
                    url: "https://dapi.kakao.com/v3/search/book?target=title", // 전송 주소
                    data: { query: $("#bookName").val() }, // 보낼 데이터
                    headers: { Authorization: "KakaoAK b7582e881dbca5d773a2c59631cf4cc5" }
                })
	                .done(function (msg) { // 응답이 오면 처리를 하는 코드
	                	$("#table").empty();

	                	 
	                	 
	                	// $("#table").append('<thead style="display: table-header-group; vertical-align: middle;border-color: inherit;">' ); 
	                 
	                	//$("#table").append("<tr align='center' valign='middle'>" ); 
	                	$("#table").append("<thead align='center'><td></td><td>제목</td><td>작가</td><td>출판사</td><td></td></tr></thead>"  );
	 
	                	//$("#table").append("</tr>");
	                	
	                	//$("#table").append("</thead>");
             	
	                	for (var i = 0; i < 5; i++) {
	                		
	                		$("#table").append('<tbody>');
	                		$("#table").append('<tr class="alert" role="alert" style="padding:10px;"> ');
	                		
	                	//	$("#table").append('<tr>');
	                		$("#table").append('<td style="padding:10px;"><img src="' + msg.documents[i].thumbnail + '"><br></td>');
	                		$("#table").append('<td align="center"><strong>' + msg.documents[i].title + '</strong></td>');
	                		$("#table").append('<td align="center"><strong>' + msg.documents[i].authors + '</strong></td>');
	                		$("#table").append('<td align="center"><strong>' + msg.documents[i].publisher + '</strong></td>');
	                	//	$("#table").append('<td><strong>' + msg.documents[i].isbn + '</strong></td>');
	                	//	$("#table").append('<td><strong>' + msg.documents[i].price + '</strong></td>');
	                	//	$("#table").append('<td><strong>' + msg.documents[i].contents + '</strong></td>');
	                		$("#table").append("</tr>");
	                		
	                	/*
	                		$("#table").append('<input type="hidden" name="title" value='+ msg.documents[i].title+ '>');
	                		$("#table").append('<input type="hidden" name="authors" value='+ msg.documents[i].authors+ '>');
	                		$("#table").append('<input type="hidden" name="publisher" value='+ msg.documents[i].publisher+ '>');
	                		$("#table").append('<input type="hidden" name="isbn" value='+ msg.documents[i].isbn+ '>');
	                		$("#table").append('<input type="hidden" name="price" value='+ msg.documents[i].price+ '>');
	                		$("#table").append('<input type="hidden" name="thumbnail" value='+ msg.documents[i].thumbnail+ '>');
	                	*/ 
	                	// 	$("#table").append(' <input type="submit" value="choice">');
	                	
	                	
	                	
	                	//$("#table").append(' <td align="center"><a href="result.jsp?title='+msg.documents[i].title+'&authors='+msg.documents[i].authors+'&publisher='+msg.documents[i].publisher+'&isbn='+msg.documents[i].isbn+'&price='+msg.documents[i].price+'&thumbnail='+msg.documents[i].thumbnail+'"><img src="../images/check.png" width="20px" height="20px"> </a>');
	                	
	                	
	                	
	                	
	                	//	$("#table").append('<tr><td>__</td></tr>');
	                	
							
						}
	                	
	                
	                });
            })
        });

    </script>		
					
					
					
					
					
					
					
					
					
    		</div>
    		
    	</div>
    </section>
					
					
					
					
					
					
					
				</div>
			</div>
		</section>

    <footer class="ftco-footer">
      <div class="container">
        <div class="row mb-5">
          <div class="col-sm-12 col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2 logo"><a href="#">Liquor <span>Store</span></a></h2>
              <p>Far far away, behind the word mountains, far from the countries.</p>
              <ul class="ftco-footer-social list-unstyled mt-2">
                <li class="ftco-animate"><a href="#"><span class="fa fa-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="fa fa-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="fa fa-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-sm-12 col-md">
            <div class="ftco-footer-widget mb-4 ml-md-4">
              <h2 class="ftco-heading-2">My Accounts</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>My Account</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Register</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Log In</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>My Order</a></li>
              </ul>
            </div>
          </div>
          <div class="col-sm-12 col-md">
            <div class="ftco-footer-widget mb-4 ml-md-4">
              <h2 class="ftco-heading-2">Information</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>About us</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Catalog</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Contact us</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Term &amp; Conditions</a></li>
              </ul>
            </div>
          </div>
          <div class="col-sm-12 col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Quick Link</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>New User</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Help Center</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Report Spam</a></li>
                <li><a href="#"><span class="fa fa-chevron-right mr-2"></span>Faq's</a></li>
              </ul>
            </div>
          </div>
          <div class="col-sm-12 col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Have a Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon fa fa-map marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon fa fa-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon fa fa-paper-plane pr-4"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid px-0 py-5 bg-black">
      	<div class="container">
      		<div class="row">
	          <div class="col-md-12">
		
	            <p class="mb-0" style="color: rgba(255,255,255,.5);"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib.com</a>
	  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
	          </div>
	        </div>
      	</div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
    
  </body>
</html>