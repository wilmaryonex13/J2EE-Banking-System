<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Alliance Bank</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <!-- css -->
  <link href="https://fonts.googleapis.com/css?family=Noto+Serif:400,400italic,700|Open+Sans:300,400,600,700" rel="stylesheet">
  <link href="css/bootstrap.css" rel="stylesheet" />
  <link href="css/bootstrap-responsive.css" rel="stylesheet" />
  <link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
  <link href="css/jcarousel.css" rel="stylesheet" />
  <link href="css/flexslider.css" rel="stylesheet" />
  <link href="css/style.css" rel="stylesheet" />
  <!-- Theme skin -->
  <link href="skins/default.css" rel="stylesheet" />
  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png" />
  <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png" />
  <link rel="shortcut icon" href="ico/favicon.png" />

  <!-- =======================================================
    Theme Name: Flattern
    Theme URL: https://bootstrapmade.com/flattern-multipurpose-bootstrap-template/
    Author: BootstrapMade.com
    Author URL: https://bootstrapmade.com
  ======================================================= -->
</head>

<body ${alert}>
  <div id="wrapper">
    <!-- toggle top area -->
    <div class="hidden-top">
      <div class="hidden-top-inner container">
        <div class="row">
          <div class="span12">
          </div>
        </div>
      </div>
    </div>
    <!-- end toggle top area -->
    <!-- start header -->
    <header>
      <div class="container">
        <div class="row nomargin">
          <div class="span12">
            <div class="headnav">
            </div>
            <!-- Add Modal -->
            <div id="Add" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="mySigninModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="mySigninModalLabel">Add <strong>user account</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" action="userManager" method="POST">
                  <div class="control-group">
                    <label class="control-label" for="inputText">First Name</label>
                    <div class="controls">
					  <input type="text" id="inputText" placeholder="First Name" name="firstName"required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputText1">Last Name</label>
                    <div class="controls">
                      <input type="text" id="inputText1" placeholder="Last Name" name="lastName" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputText2">Age</label>
                    <div class="controls">
                      <input type="number" id="inputText2" placeholder="Age" name="age" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputText3">Email Address</label>
                    <div class="controls">
                      <input type="email" id="inputText3" placeholder="Email Address" name="emailAddress" required>
                    </div>
                  </div>                                        
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn" name="buttonAdd">Confirm</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- end Add modal -->
          </div>
        </div>
        <div class="row">
          <div class="span4">
            <div class="logo">
              <a href="index"><img src="img/asi_logo.png" alt="" class="logo" /></a>
            </div>
          </div>
          <div class="span8">
            <div class="navbar navbar-static-top">
              <div class="navigation">
                <nav>
                  <ul class="nav topnav">
                    <li>
                      <a href="index"><strong>HOME</strong></a>
                    </li>                  
  					<li>
                      <a href="userManager"><strong>USER MANAGER</strong></a>
                    </li>
                    <li>
                      <a href="accountManager"><strong>ACCOUNT MANAGER</strong></a>
                    </li>
                    <li>
                      <a href="transactions"><strong>TRANSACTIONS</strong></a>
                    </li>
                  </ul>
                </nav>
              </div>
              <!-- end navigation -->
            </div>
          </div>
        </div>
      </div>
    </header>
    <!-- end header -->
    <section id="inner-headline">
      <div class="container">
        <div class="row">
          <div class="span4">
            <div class="inner-heading">
              <h2>User Manager</h2>
            </div>
          </div>
          <div class="span8">
          </div>
        </div>
      </div>
    </section>
    <section id="content">
      <div class="container">
        <form class="form-search" action="userManager" method="POST">
          <div class="widget" style = "position:relative; left: 32px; ">
            <input placeholder="First or Last Name" id="inputSearch" type="text" class="input-medium search-query" name="inputSearch">
            <br>
            <br>
            <button type="submit" class="btn btn-square btn-theme" name="buttonSearch">Search</button>
            <br>
            <br>
            <br>            
          </div>
          <div class="span6">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>
                  </th>
                  <th>
                    First Name
                  </th>
                  <th>
                    Last Name
                  </th>
                  <th>
                    Age
                  </th>
                  <th>
                    Email Address
                  </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${userAccountList}" var="item">
                <tr>
                  <td>
                    <input type="checkbox" value="${item.id}" name="checkbox"></input>
                  </td>
                  <td>
                	${item.firstName}
                  </td>
                  <td>
                    ${item.lastName}
                  </td>
                  <td>
                    ${item.age}
                  </td>
                  <td>
                    ${item.emailAddress}
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
            <button class="btn btn-square btn-brown" href="#Add" data-toggle="modal">Add</button>
            <button type="submit" class="btn btn-square btn-brown" name="buttonDelete">Delete</button>
            <br>
            <br>
            <br>
          </div>
        </form>
      </div>
    </section>
    <footer>
      <div class="container">
        <div class="row">
          <div class="span3">
            <div class="widget">
              <h5 class="widgetheading">Alliance Financial Group, Inc.</h5>
              <ul class="link-list">
                <li><a href="#">Privacy Policy</a></li>
                <li><a href="#">Terms and Conditions</a></li>
                <li><a href="#">Disclosure Policy</a></li>
              </ul>
            </div>
          </div>
          <div class="span3">
            <div class="widget">
              <h5 class="widgetheading">Group Companies</h5>
              <ul class="link-list">
                <li><a href="#">Alliance Financial Group</a></li>
                <li><a href="#">Alliance Trust & Banking</a></li>
                <li><a href="#">Alliance Securities</a></li>
              </ul>
            </div>
          </div>
          <div class="span3">
            <div class="widget">
              <h5 class="widgetheading">Photos</h5>
              <div class="flickr_badge">
              	<img alt="photo1" src="img/photo_1.jpg">
              	<img alt="photo2" src="img/photo_2.jpg">
              	<img alt="photo3" src="img/photo_3.jpg">
              	<img alt="photo4" src="img/photo_4.jpg">
              </div>
              <div class="clear">
              </div>
            </div>
          </div>
          <div class="span3">
            <div class="widget">
              <h5 class="widgetheading">Get in touch with us</h5>
              <address>
								<strong>Alliance Bank</strong><br>
								 16th Floor, Buildcomm Center
								 Sumilon Road, Cebu Business Park
								 Cebu City 6000, Philippines
			  </address>
              <p>
                <i class="icon-phone"></i> (032) 234-5678 <br>
                <i class="icon-envelope-alt"></i> alliancebank@gmail.com
              </p>
            </div>
          </div>
        </div>
      </div>
      <div id="sub-footer">
        <div class="container">
          <div class="row">
            <div class="span6">
              <div class="copyright">
                <p>
                  <span>&copy; 2018 - All right reserved.</span>
                </p>
                <div class="credits">
                </div>              
              </div>
            </div>
            <div class="span6">
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
  <a href="#" class="scrollup"><i class="icon-chevron-up icon-square icon-32 active"></i></a>
  <!-- javascript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="js/jquery.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/bootstrap.js"></script>
  <script src="js/jcarousel/jquery.jcarousel.min.js"></script>
  <script src="js/jquery.fancybox.pack.js"></script>
  <script src="js/jquery.fancybox-media.js"></script>
  <script src="js/google-code-prettify/prettify.js"></script>
  <script src="js/portfolio/jquery.quicksand.js"></script>
  <script src="js/portfolio/setting.js"></script>
  <script src="js/jquery.flexslider.js"></script>
  <script src="js/jquery.nivo.slider.js"></script>
  <script src="js/modernizr.custom.js"></script>
  <script src="js/jquery.ba-cond.min.js"></script>
  <script src="js/jquery.slitslider.js"></script>
  <script src="js/animate.js"></script>

  <!-- Template Custom JavaScript File -->
  <script src="js/custom.js"></script>

</body>

</html>
