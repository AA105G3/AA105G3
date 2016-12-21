function doFirst(){
	document.getElementById('adv_image').onchange = fileChange;
}

function fileChange(){
	var file = document.getElementById('adv_image').files[0];
	
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.addEventListener('load', function(){
		var image = document.getElementById('img');
		image.src = readFile.result;
	}, false);
}

window.addEventListener('load', doFirst, false);