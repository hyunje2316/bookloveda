<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="book.model.*" %>
    
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
    
    
<c:set value="${ vo }" var="vo" />
<!DOCTYPE html>
<html lang="en">
  <head>
  
	<script src="http://code.jquery.com/jquery-latest.js"></script> 
	
	
	
    <title>BookLove多 - offline List View</title>
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
      
      
      
      <link rel="stylesheet" href="assets/css/style.css">
      
      
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
window.onload = function(){
	var no = $('#o_board_no').val();
	$.ajax({
		type: "post",
		async: false,
		url: "./offcomm",
		data: {num : no},
		success: function(data, textStatus){
			var jsonInfo = JSON.parse(data);
			var i;
			var max = ${ maxcom };
			var memberInfo = '<h3 class="mb-5">Comments</h3>';
			memberInfo += '<form action="./OfflineCommentSubmit.book" method="post">';
			memberInfo += '<input type="hidden" name="c_board_no" value="${ vo.o_no }">';
			memberInfo += '<textarea name="off_comment"cols="30" rows="5" class="form-control"></textarea><br>';
			memberInfo += '<input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary">';
			memberInfo += "<input type='hidden' name='c_no' value='" + max + "'>";
			memberInfo += '</form>';
			memberInfo += '<ul class="comment-list">';
			memberInfo += '<br><br>';
			
			for(i in jsonInfo.members){
				memberInfo += '<li class="comment">';
				memberInfo += '<div class="comment-body">';
				memberInfo += '<h3>' + jsonInfo.members[i].id + '</h3>';
				memberInfo += '<div class="meta">' + jsonInfo.members[i].date + '</div>';
				memberInfo += '<p>' + jsonInfo.members[i].comment + '</p>';
				memberInfo += '<p><a style="color: #BBAA55" href="./OfflineCommDelAction.book?id=' + jsonInfo.members[i].id + '&num=${ vo.o_no }&no=' + jsonInfo.members[i].no + '">삭제</a></p>';
				memberInfo += '</div>';
				memberInfo += '</li>';
			}
			memberInfo += "</ul>";
			i++;
			if(i > 0) memberInfo += '<h3 class="mb-5">Total : ' + i + '</h3>';
			else i = 0;
			$('#offline_comm').html(memberInfo);
		}
	});
};

</script>
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
		        <c:if test="${ sessionScope.memID != null }">
		        <p class="mb-0"><font color="#33FF33">${memID }</font> &nbsp;&nbsp;&nbsp;<a href="./logout.book">Log Out</a></p>
		        </c:if>
		        <c:if test="${ sessionScope.memID == null }">
		        	<p class="mb-0"><a href="./signUp.book" class="mr-2">Sign Up</a> <a href="./loginForm.book">Log In</a></p>
		        </c:if>
		        </div>
					</div>
				</div>
			</div>
		</div>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="home.book">BookLove <span>多</span></a>
<!-- 	      
	      <div class="order-lg-last btn-group">
          <a href="#" class="btn-cart dropdown-toggle dropdown-toggle-split" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	<span class="flaticon-shopping-bag"></span>
          	<div class="d-flex justify-content-center align-items-center"><small>3</small></div>
          </a>
          <div class="dropdown-menu dropdown-menu-right">
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(booklove/images/prod-1.jpg);"></div>
				    	<div class="text pl-3">
				    		<h4>Bacardi 151</h4>
				    		<p class="mb-0"><a href="#" class="price">$25.99</a><span class="quantity ml-3">Quantity: 01</span></p>
				    	</div>
				    </div>
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(booklove/images/prod-2.jpg);"></div>
				    	<div class="text pl-3">
				    		<h4>Jim Beam Kentucky Straight</h4>
				    		<p class="mb-0"><a href="#" class="price">$30.89</a><span class="quantity ml-3">Quantity: 02</span></p>
				    	</div>
				    </div>
				    <div class="dropdown-item d-flex align-items-start" href="#">
				    	<div class="img" style="background-image: url(booklove/images/prod-3.jpg);"></div>
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
        </div> -->

	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="home.book" class="nav-link">Home</a></li>
	         
	          <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Resell</a>
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
	          <li class="nav-item active"><a href="./BookOfflineList.book" class="nav-link">Club-Offline</a></li>
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
          	<p class="breadcrumbs mb-0"><span class="mr-2"><a href="home.book">Home <i class="fa fa-chevron-right"></i></a></span> <span><a href="product.html">Club - Offline <i class="fa fa-chevron-right"></i></a></span> <span>Club - Offline detail <i class="fa fa-chevron-right"></i></span></p>
            <h2 class="mb-0 bread">Club - Offline detail</h2>
          </div>
        </div>
      </div>
    </section>

   <section class="ftco-section ftco-degree-bg">
      <div class="container">
        <div class="row">
          <div style="width:1170px;">
            	subject<h2 class="mb-3">${ vo.o_subject }</h2>
            <div align="right">
            <p>DATE : ${ vo.o_date }</p>
            <p>작성자 : ${ vo.m_id }</p>
            <p>조회수 : ${ vo.o_ref }</p>
            </div>
          	<div align="center">
	          	<p>
	              <img src="./offlineUpload/${ vo.o_address }" alt="" class="img-fluid" >
	            </p>
	         </div>
	         
	         <p>CONTENT
	         <p> ${ vo.o_content }
	         
            <div class="tag-widget post-tag-container mb-5 mt-5">
              <div class="tagcloud">
             			  
			 <div align="center" >	
			  <c:if test="${ sessionScope.memID != vo.m_id }">
			  <a href="BookOfflineList.book" class="btn btn-primary py-2 px-4" style="padding: 1.5rem !important;">List</a> 
  			 
			  </c:if>
			  
			   <c:if test="${ sessionScope.memID == vo.m_id }">
			 <a href="BookOfflineList.book" class="btn btn-primary py-2 px-4" style="padding: 1.5rem !important;">List</a> 
  			 <a href="OfflineUpdate.book?num=${ vo.o_no }" class="btn btn-primary py-2 px-4" style="padding: 1.5rem !important;">Update</a> 
 			  <a href="OfflineDelete.book?num=${ vo.o_no }" class="btn btn-primary py-2 px-4" style="padding: 1.5rem !important;">Delete</a>
 			 
			   </c:if>
   </div>
                
              </div>
              
              
            </div>
            
			   
   
   
            
            

			



            <input type="hidden" name="o_board_no" id="o_board_no" value="${ vo.o_no }"> <!-- 글번호 -->
            <div class="pt-5 mt-5" id="offline_comm">
              <h3 class="mb-5">6 Comments</h3>
              <ul class="comment-list">
                <li class="comment">
                  <div class="vcard bio">
                    <img src="images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>John Doe</h3>
                    <div class="meta">April 12, 2020 at 1:21am</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>
                </li>

                <li class="comment">
                  <div class="vcard bio">
                    <img src="images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>John Doe</h3>
                    <div class="meta">April 12, 2020 at 1:21am</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>

                  <ul class="children">
                    <li class="comment">
                      <div class="vcard bio">
                        <img src="images/person_1.jpg" alt="Image placeholder">
                      </div>
                      <div class="comment-body">
                        <h3>John Doe</h3>
                        <div class="meta">April 12, 2020 at 1:21am</div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                        <p><a href="#" class="reply">Reply</a></p>
                      </div>


                      <ul class="children">
                        <li class="comment">
                          <div class="vcard bio">
                            <img src="images/person_1.jpg" alt="Image placeholder">
                          </div>
                          <div class="comment-body">
                            <h3>John Doe</h3>
                            <div class="meta">April 12, 2020 at 1:21am</div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                            <p><a href="#" class="reply">Reply</a></p>
                          </div>

                            <ul class="children">
                              <li class="comment">
                                <div class="vcard bio">
                                  <img src="images/person_1.jpg" alt="Image placeholder">
                                </div>
                                <div class="comment-body">
                                  <h3>John Doe</h3>
                                  <div class="meta">April 12, 2020 at 1:21am</div>
                                  <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                                  <p><a href="#" class="reply">Reply</a></p>
                                </div>
                              </li>
                            </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>

                <li class="comment">
                  <div class="vcard bio">
                    <img src="images/person_1.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>John Doe</h3>
                    <div class="meta">April 12, 2020 at 1:21am</div>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
                    <p><a href="#" class="reply">Reply</a></p>
                  </div>
                </li>
              </ul>
              <!-- END comment-list -->
              
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">Leave a comment</h3>
                <form action="#" class="p-5 bg-light">
                  <div class="form-group">
                    <label for="name">Name *</label>
                    <input type="text" class="form-control" id="name">
                  </div>
                  <div class="form-group">
                    <label for="email">Email *</label>
                    <input type="email" class="form-control" id="email">
                  </div>
                  <div class="form-group">
                    <label for="website">Website</label>
                    <input type="url" class="form-control" id="website">
                  </div>

                  <div class="form-group">
                    <label for="message">Message</label>
                    <textarea name="" id="message" cols="30" rows="10" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
            </div>

          </div> <!-- .col-md-8 -->
          

        </div>
      </div>
    </section> <!-- .section -->

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
    

  <script>
		$(document).ready(function(){

		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		            
		            $('#quantity').val(quantity + 1);

		          
		            // Increment
		        
		    });

		     $('.quantity-left-minus').click(function(e){
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		      
		            // Increment
		            if(quantity>0){
		            $('#quantity').val(quantity - 1);
		            }
		    });
		    
		});
	</script>
    
  </body>
</html>