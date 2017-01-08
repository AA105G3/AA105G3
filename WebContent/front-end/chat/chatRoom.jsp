<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Chat Room</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			#chatroom-wrapper{
				margin: 0px;
				padding:0px;
			}
			#messagesArea{
				/* min-height: 150px;
				min-width: 450px; */
			}
			#chatroom-inputArea{
				padding:0px;
			}
			#inputArea{
				width:450px;
				height: 80px;
				 resize:none;
			}
			#msgInput{
				width:80px;
				height: 80px;
				font-size: 20px;
				font-style: italic;
				margin: 0px;
			}
			#msg-comment{
				font-size: 24px;
				color:gray;
			}
			.frd-id{
				margin-left: 10px;
				font-size: 20px;
				float: left;
				margin-top: 5px;
			}

			.mem-id{
				float: right;
				margin-right: 10px;
				font-size: 20px;
				margin-top: 5px;
				font-weight: bold;
			}
	

			.direct-chat-text {
			    border-radius: 5px;
			    position: relative;
			    padding: 5px 10px;
			    background: #d2d6de;
			    border: 1px solid #d2d6de;
			    margin: 5px 0 0 10px;
			    color: #444;
			    float: left;
			    word-break: break-all;
			    margin-right: 5px;
			}
			.direct-chat-text:after, .direct-chat-text:before {
			    position: absolute;
			    right: 100%;
			    top: 15px;
			    border: solid transparent;
			    border-right-color: #d2d6de;
			    content: ' ';
			    height: 0;
			    width: 0;
			    pointer-events: none;
			}
			.direct-chat-text:after {
			    border-width: 5px;
			    margin-top: -5px;
			}
			.direct-chat-text:before {
			    border-width: 6px;
			    margin-top: -6px;
			}
			.right .direct-chat-text {
			    margin-right: 10px;
			    margin-left: 5px;
			    float:right;
			    background: #3c8dbc;
			    color: #fff;
			}
			.right .direct-chat-text:after, .right .direct-chat-text:before {
			    right: auto;
			    left: 100%;
			    border-right-color: transparent;
			    border-left-color: #3c8dbc;
			}

		</style>
	</head>
	<body>
		
		<div class="col-xs-12 col-sm-12 " id="chatroom-wrapper" >
			<div id = "messagesArea">
				
				
				
			</div>
			<table>
				<tr>
					<td>
						<textarea id = "inputArea"></textarea>		
					</td>
					<td>
						<button id="msgInput" onclick="send();">talk <i class="glyphicon glyphicon-comment" id="msg-comment"></i></button>
					</td>
				</tr>
			</table>
				
				
			
				
			
						
			

		</div>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">


		var ws;
	    var mem_no = "${param.mem_no}";//自己
	    var frd_no = "${param.friend_no}";//朋友
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));

		$().ready(function(){
			$('#inputArea').width($(window).width()-90);
			$('#messagesArea').height($(window).height()-85)


			//chat websocket
			connect();



		})
		$(window).resize(function(){
			$('#inputArea').width($(window).width()-90);
			$('#messagesArea').height($(window).height()-85)
			$('#direct-chat-text').width($(window).width())
		})


	    
	    function connect() {
	    	
		    var endPointURL = "ws://" + window.location.host + webCtx + "/ChatEndpoint/"+mem_no+"/"+frd_no;
	        ws = new WebSocket(endPointURL);


	        ws.onmessage = function(event) {
	            console.log(event.data);
	            var message = JSON.parse(event.data);
	            var html = '<div class="direct-chat-message">'+
							'<div class="frd-id">'+ message.from +'</div>'+
							'<div class="direct-chat-text">'+ message.content+'</div>'+
							'<div style="clear:left;"></div>'+
							'</div>';

	            $('#messagesArea').append(html)
	        };
	    }

	    function send() {

	        var content =$('#inputArea').val();
	        var to = frd_no;//送訊息的目標id
	        var html = '<div class="direct-chat-message right">'+
						'<div class="mem-id">我</div>'+
						'<div class="direct-chat-text">'+content+'</div>'+
						'<div style="clear:right;"></div>'+
						'</div>';

	        var json = JSON.stringify({
	            "to":to,
	            "content":content
	        });

	        ws.send(json);
	        $('#messagesArea').append(html);
	    }

	</script>
</html>