
	function showImage(idx){
		var file = document.getElementById("upLoadFile"+idx).files[0];				
		var read = new FileReader();
		read.onload=function(){
				var image = document.getElementById('image'+idx);
					image.src=read.result;
					
				};
		read.readAsDataURL(file);	
	} 


// 	(function readURL(input) {

//     if (input.files && input.files[0]) {
//         var reader = new FileReader();

//         reader.onload = function (e) {
//             $('#image').attr('src', e.target.result);
//         }

//         reader.readAsDataURL(input.files[0]);
//     }
// }

// $("#upLoadFile").change(function(){
//     readURL(this);
// });

// );
