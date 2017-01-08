<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.frd_list.model.*"%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/sweetalert2/6.2.9/sweetalert2.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
		
		<button class="chatButton" value = "BBB">button</button>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.2.9/sweetalert2.min.js"></script>
		<script type="text/javascript">

		var ws;
	    var mem_no = "";//自己
	    var frd_no = "";//朋友
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));

	    


	    function connect() {
	    	
	        // mem_no = document.getElementById("username").value;
	        // frd_no = document.getElementById("to").value;
	        mem_no = AAA;
	        frd_no = BBB;
		    var endPointURL = "ws://" + window.location.host + webCtx + "/InviteEndpoint/"+mem_no;
	        ws = new WebSocket(endPointURL);


	        ws.onmessage = function(event) {

	            console.log(event.data);
	            var message = JSON.parse(event.data);

	            //受邀區塊
	            if(message.content=='invite'){
	            	swal({
					  title: '聊天室邀請',
					  text: message.from+"邀請您進入聊天室",
					  type: 'info',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: '同意!',
					  cancelButtonText: '拒絕!',
					  confirmButtonClass: 'btn btn-primary',
					  cancelButtonClass: 'btn btn-danger',
					  buttonsStyling: false
					}).then(function () {
					  	//同意開啟聊天室並傳訊息同意聊天
					  	agree(message.from);
					  	var myWindow = window.open("http://www.google.com", "", "width=400,height=400");

					}, function (dismiss) {
					  // dismiss can be 'cancel', 'overlay',
					  
					  if (dismiss === 'cancel') {
					  	//不同意傳訊息拒絕
					  		refuse(message.from);
					  }
					})
	            }


	            //邀請區塊
	            if(message.content=='true'){
	            	swal({
					  title: '對方同意邀請',
					  text: "You won't be able to revert this!",
					  type: 'info',
					  confirmButtonColor: '#3085d6',
					  confirmButtonText: '開啟聊天室'
					}).then(function () {
					  //打開新分頁
					  	var myWindow = window.open("http://www.google.com", "", "width=400,height=400");
					})
	            }else if(message.content=='false'){
	            	swal({
					 title: '邀請被拒絕',
					 text: "很抱歉，對方不同意您的聊天邀請!",
					 type:'success'
					})
	            }
	            
	        };
	    }
	    //回傳同意
	   function agree(to){
	   		var content = 'true';
	        var to = to;//送訊息的目標id
	        var json = JSON.stringify({
	            "to":to,
	            "content":content
	        });

	        ws.send(json);
	   }

	   function refuse(to){
	   		var content = 'false';
	        var to = to;//送訊息的目標id
	        var json = JSON.stringify({
	            "to":to,
	            "content":content
	        });

	        ws.send(json);
	   }

	    //邀請
	    function invite() {
	        var content = 'invite';
	        var to = $(this).val();//送訊息的目標id
	        var json = JSON.stringify({
	            "to":to,
	            "content":content
	        });

	        ws.send(json);
	    }


		$().ready(function(){
			//when document ready then call connect;
		    connect();
		     $('.chatButton').on('click',invite);
		})
		


	</script>
	</body>
</html>