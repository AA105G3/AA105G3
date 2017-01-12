function checkMem(){
	
	var rightImage = "<img src='../../images/right.png' />";
	var errorImage = "<img src='../../images/error.png' />";
	
	//驗證會員信箱
	document.getElementById('spanEmail').innerHTML = "例：foodtime@gmail.com"
	
	var memAc = document.getElementById('memEmail');
	
	memAc.addEventListener('blur', checkEmail, false);
	
	function checkEmail(){
		var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
	
		if(!emailRule.test(memAc.value)){
			document.getElementById('spanEmail').innerHTML = errorImage + "電子信箱格式錯誤";
		}
		else{
			document.getElementById('spanEmail').innerHTML = rightImage + "正確！";
		}
	}
	
	//驗證密碼
	document.getElementById('spanPw1').innerHTML = "至少使用一個英文跟一個數字設定密碼，密碼長度6~12字數，區分大小寫";
	
	var memPw1 = document.getElementById('memPw1');
	
	memPw1.addEventListener('blur', checkPassword, false);
	
	function checkPassword(){
		var passwordRule = /[a-zA-Z0-9]{6,12}/g;
		
		var numRule = /[a-zA-Z]{6,12}/g;
	
		var result = passwordRule.exec(memPw1.value);
		
		if(memPw1.value.length >= 6){
			if(memPw1.value == result){
				if(memPw1.value.search(/[0-9]/g) == -1 || memPw1.value.search(/[a-zA-Z]/g) == -1){
					if(!numRule.test(memPw1.value)){
						document.getElementById('spanPw1').innerHTML = errorImage + "請加入英文";
					}
					else{
						document.getElementById('spanPw1').innerHTML = errorImage + "請加入數字";
					}
				}
				else{
					document.getElementById('spanPw1').innerHTML = rightImage + "正確！";
				}
			}
			else{
				document.getElementById('spanPw1').innerHTML = errorImage + "不可以使用特殊字元如：!@#$%^&*()-_=+空白等";
			}
		}
		else{
			document.getElementById('spanPw1').innerHTML = errorImage + "密碼至少6碼";
		}
	}
	
	//再次輸入密碼
	document.getElementById('spanPw2').innerHTML = "請再次輸入相同的密碼";
	
	var memPw2 = document.getElementById('memPw2');
	
	memPw2.addEventListener('blur', checkAgain, false);
	memPw1.addEventListener('keyup', checkAgain, false);
	
	function checkAgain(){
		var passwordRule = /[a-zA-Z0-9]{6,12}/g;
		
		if(memPw2.value.length != 0){
			if(memPw2.value.length >= 6){
				if(memPw2.value == memPw1.value){
					if(memPw2.value.search(/[0-9]/g) == -1 || memPw2.value.search(/[a-zA-Z]/g) == -1){
						document.getElementById('spanPw2').innerHTML = errorImage + "密碼格式錯誤";
					}
					else{
						document.getElementById('spanPw2').innerHTML = rightImage + "正確！";
					}
				}
				else{
					document.getElementById('spanPw2').innerHTML = errorImage + "兩次密碼不相同";
				}
			}
			else{
				document.getElementById('spanPw2').innerHTML = errorImage + "密碼至少6碼";
			}
		}
		else{
			document.getElementById('spanPw2').innerHTML = "請再次輸入相同的密碼";
		}
		
	}
	
	//驗證手機
	document.getElementById('spanPhone').innerHTML = "請輸入手機號碼，不需使用(-)或空格隔開，例：0987654321";
	
	var memPhone = document.getElementById('memPhone');
	
	memPhone.addEventListener('blur', checkPhone, false);
	
	function checkPhone(){
		var phoneRule = /^09[0-9]{8}$/;
		
		var result = phoneRule.exec(memPhone.value);
		
		if(memPhone.value.length == 10){
			if(result == memPhone.value){
				document.getElementById('spanPhone').innerHTML = rightImage + "正確！";
			}
			else{
				document.getElementById('spanPhone').innerHTML = errorImage + "請輸入有效的手機號碼";
			}
		}
		else{
			document.getElementById('spanPhone').innerHTML = errorImage + "請輸入有效的手機號碼"
		}
	}
	
	//顯示私廚資料
	/*document.getElementById('spanOwn').innerHTML = "〈申請私廚資格詳細內容請參閱...〉";*/
	
	//顯示上傳圖片的資料
	document.getElementById('uploadImage').onchange = fileChange;
	
	document.getElementById('spanImage').innerHTML = "請上傳.png/.jpg/.gif格式的圖片，大小1MB以內";
	
	function fileChange(){
		var file = document.getElementById('uploadImage').files[0];
		var message = 'File Type : ' + file.type + '<br>';
		message += 'File Size : ' + file.size + ' bytes<br>';
		document.getElementById('fileInfo').innerHTML = message;

		uploadImage.addEventListener('mouseout', checkImage, false);
		
		//檢查圖片大小及格式
		
		function checkImage(){
			if(file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/gif'){
				if(file.size > 1024*1024){
					document.getElementById('spanImage').innerHTML = errorImage + "檔案大小超過限制，請選擇1MB以內的圖片";
					document.getElementById('img').src = "images/wrongImage.png";
				}
				else{
					document.getElementById('spanImage').innerHTML = rightImage + "正確！";
					
					//顯示上傳圖片
					var readFile = new FileReader();
					readFile.readAsDataURL(file);
					readFile.addEventListener('load', function(){
						var image = document.getElementById('img');
						image.src = readFile.result;
						/* image.width = 300; */
						image.height = 200;
					}, false);
				}
			}
			else{
				document.getElementById('spanImage').innerHTML = errorImage + "圖片檔案格式不符，請上傳.png/.jpg/.gif格式的圖片";
				/* var image_x = document.getElementById('img');
				image_x.parentNode.removeChild(image_x); */
				
				/* document.getElementById('img').src = "#"; */
				
				/* document.getElementById('img').removeAttribute('src'); */
				
				/* document.getElementById('img').src = "images/wrongImage.png"; */

				/* var readFile = new FileReader();
				readFile.readAsDataURL(file);
				readFile.addEventListener('load', function(){
					document.getElementById('img').src = "images/wrongImage.png";
				}, false); */
			}
		}
	}
}

window.addEventListener('load', checkMem, false);

