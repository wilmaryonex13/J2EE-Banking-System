<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <title>Flattern - Flat and trendy bootstrap site template</title>
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
            <!-- Withdraw Modal -->
            <div id="Withdraw" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="mySigninModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="mySigninModalLabel">Withdraw from this <strong>account</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" action="transactions" method="post"> 
                  <div class="control-group">
                    <label class="control-label" for="accountNumber">Account Number</label>
                    <div class="controls">
					  <select name="option" id="accountNumber" required>
                        <option value="" selected disabled hidden>Choose here</option>
                        <c:forEach items="${bankAccountList}" var="item">
						<option value="${item.accountNumber}"> - ${item.accountNumber}</option>
						</c:forEach>
					  </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputAmount">Amount</label>
                    <div class="controls">
                      <input type="number" id="inputAmoung" placeholder="Amount" name="amount" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn" name="withdraw">Confirm</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- end Withdraw modal -->
            <!-- Deposit Modal -->
            <div id="Deposit" class="modal styled hide fade" tabindex="-1" role="dialog" aria-labelledby="mySigninModalLabel" aria-hidden="true">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 id="mySigninModalLabel">Deposit to this <strong>account</strong></h4>
              </div>
              <div class="modal-body">
                <form class="form-horizontal" action="transactions" method="post"> 
                  <div class="control-group">
                    <label class="control-label" for="accountNumber">Account Number</label>
                    <div class="controls">
					  <select name="option" id="accountNumber" required>
                        <option value="" selected disabled hidden>Choose here</option>
                        <c:forEach items="${bankAccountList}" var="item">
						<option value="${item.accountNumber}">${item.accountNumber}</option>
						</c:forEach>
					  </select>
                    </div>
                  </div>
                  <div class="control-group">
                    <label class="control-label" for="inputAmount">Amount</label>
                    <div class="controls">
                      <input type="number" id="inputAmoung" placeholder="Amount" name="amount" required>
                    </div>
                  </div>
                  <div class="control-group">
                    <div class="controls">
                      <button type="submit" class="btn" name="deposit">Confirm</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <!-- end Deposit modal -->
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
              <h2>Transactions</h2>
            </div>
          </div>
          <div class="span8">
          </div>
        </div>
      </div>
    </section>
    <section id="content">
      <div class="container">
        <form class="form-search" action="transactions" method="POST">
          <div class="widget" style = "position:relative; left: 32px; ">
            <input placeholder="Account number" type="text" class="input-medium search-query" name="accountNumber">
            <br>
            <br>
            <button type="submit" class="btn btn-square btn-theme" name="searchByAccountNumber">Search</button>
            <br>
            <br>
            <br>
          </div>
          <div class="span11">
            <table class="table">
              <thead>
                <tr>
                  <th>
                    Account Number
                  </th>
                  <th>
                    Date/Time
                  </th>
                  <th>
                    Action
                  </th>
                  <th>
                    Amount
                  </th>
                  <th>
                    Previous Balance
                  </th>
                  <th>
                    Current Balance
                  </th>
                </tr>
              </thead>
              <tbody>
				<c:forEach items = "${Transactions}" var="transaction">
					<c:if test = "${transaction.action == 'W'}">
						<tr class = "warning">
							<td>${transaction.accountNumber} </td>
							<td>${transaction.date}</td>
							    <c:if test = "${transaction.action == 'W'}">
									<td>Withdraw</td>	
	      						</c:if>
	     					    <c:if test = "${transaction.action == 'D'}">
									<td>Deposit</td>	
	      						</c:if>
							<td>${transaction.amount}</td>
							<td>${transaction.before_amount}</td>
							<td>${transaction.after_amount}</td>
						</tr>
      				</c:if>
     				<c:if test = "${transaction.action == 'D'}">
						<tr class="success">
						<td>${transaction.accountNumber} </td>
							<td>${transaction.date}</td>
							    <c:if test = "${transaction.action == 'W'}">
									<td>Withdraw</td>	
	      						</c:if>
	     					    <c:if test = "${transaction.action == 'D'}">
									<td>Deposit</td>	
	      						</c:if>
							<td>${transaction.amount}</td>
							<td>${transaction.before_amount}</td>
							<td>${transaction.after_amount}</td>
						</tr>	
      				</c:if>
				</c:forEach>
              </tbody>
            </table>
          	<button class="btn btn-square btn-brown" href="#Withdraw" data-toggle="modal">Withdraw</button>
          	<button class="btn btn-square btn-brown" href="#Deposit" data-toggle="modal">Deposit</button>
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
              <h5 class="widgetheading">Browse pages</h5>
              <ul class="link-list">
                <li><a href="#">About our company</a></li>
                <li><a href="#">Our services</a></li>
                <li><a href="#">Meet our team</a></li>
              </ul>
            </div>
          </div>
          <div class="span3">
            <div class="widget">
              <h5 class="widgetheading">Important stuff</h5>
              <ul class="link-list">
                <li><a href="#">Terms and conditions</a></li>
                <li><a href="#">Privacy policy</a></li>
                <li><a href="#">Career center</a></li>
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
								<strong>Bank</strong><br>
								 Jamestown Subdivision San Roque, Talisay City, Cebu<br>
								 6045 Philippines
			  </address>
              <p>
                <i class="icon-phone"></i> (032) 273-0468 <br>
                <i class="icon-envelope-alt"></i> alviolasurveying@gmail.com
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
