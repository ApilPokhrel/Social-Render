var dialog1 = document.getElementById("cd1");
	var dialog2 = document.getElementById("cd2");
	var finput1 = document.getElementById("cf1");
	var finput2 = document.getElementById("cf2");
	var preview1 = document.getElementById("preview1");
	var preview2 = document.getElementById("preview2");
 
 
     preview1.style.display = 'none';
     preview2.style.display = 'none';

 	dialog1.addEventListener("click", function(){
 	    if(preview1.style.display === 'none' || finput1.value === null){
 			finput1.click();
 	  }
 	  else{
 	      finput1.value = null;
 	       var vid1 = document.getElementById("vid1");
 	       if(vid1){
 	       vid1.autoplay = false;
 	    vid1.load();
 	  }
 	    preview1.style.display = 'none';  
 	  }
 		});

 		dialog2.addEventListener("click", function(){
 	     if(preview2.style.display === 'none' || finput2.value === null){
 	    finput2.click();
 	  }
 	  else{
 	      finput2.value = null;
 	       var vid2 = document.getElementById("vid2");
 	       if(vid2){
 	       vid2.autoplay = false;
 	    vid2.load();
 	  }
 	    preview2.style.display = 'none';  
 	  }
 		
 		});

   

   function imgPreviewComp(url){
   	return `<button class="exitfile">&#10008;</button> <img src="${url}" style="height: 210px; width:202px; margin-top: 5px;
    margin-left: -10px;">`;
   } 


   function vidPreview1(url){
	return `<video id="vid1"  style="height: 210px; width:202px; margin-top: 5px;
    margin-left: -10px;"controls>
     <source src="${url}" id="video_here">
    Your browser does not support HTML5 video.
    </video>`;
    }

     function vidPreview2(url){
  return `<video id="vid2" style="height: 210px; width:202px; margin-top: 5px;
    margin-left: -10px;" " controls>
     <source src="${url}" id="video_here">
    Your browser does not support HTML5 video.
    </video>`;
    }

    var source = document.getElementById("video_here");

    if(finput1){
	finput1.addEventListener("change", function(){
        onChange1(this);
	});
    }

if(finput2){
	finput2.addEventListener("change", function(){
		onChange2(this);
	});
}

   function onChange1(input1){
    
    var reader;
    var html1;
    if(input1.files && input1.files[0]){
    	var context = input1.files[0].type;
    	var str = context.split("/");
    	if(str[0] === "image"){
        reader = new FileReader();
        reader.onload = function(e){
           preview1.style.display = 'block';

           html1 = imgPreviewComp(e.target.result);
           preview1.innerHTML = html1;
        }

        reader.readAsDataURL(input1.files[0]);
    	}

    	else if(str[0] === "video"){
            preview1.style.display = 'block';
            html1 = vidPreview1(URL.createObjectURL(input1.files[0]));
          preview1.innerHTML = html1;
           
    	}

    	else{
    		return;
    	}
    }
    }


   function onChange2(input2){
   	var reader;
   	var html2;
     if(input2.files && input2.files[0]){

     	var context = input2.files[0].type;
     	var str = context.split("/");
     	if(str[0] === "image"){
        reader = new FileReader();
        reader.onload = function(e){
        	preview2.style.display = 'block';
          html2 = imgPreviewComp(e.target.result);

          preview2.innerHTML = html2;
        }

        reader.readAsDataURL(input2.files[0]);
    }

    else if(str[0] === "video"){
          preview2.style.display = 'block';
          html2 = vidPreview2(URL.createObjectURL(input2.files[0]));

          preview2.innerHTML = html2;
         }

         else{
         	return;
         }
     }
    }


    function closePreview1(){
      finput1.value = null;
       var vid1 = document.getElementById("vid1");
       vid1.autoplay = false;
    vid1.load();
    preview1.style.display = 'none';
    }


    function closePreview2(){
      finput2.value = null;
       var vid2 = document.getElementById("vid2");
       vid2.autoplay = false;
       vid2.load();
       preview2.style.display = 'none';
    }
    
    
	var tag = document.getElementById("tags");
	tag.addEventListener("change", function(){
		console.log(tag.value);
	});

	
	
	
	
	
	
	
	
var option = document.getElementById("options");
var opns = document.getElementsByClassName("inp");
//opns[0].style.display = 'none';
opns[1].style.display = 'none';
opns[2].style.display = 'none';
opns[3].style.display = 'none';
option.addEventListener("click", function(){
	if(opns[1].style.display === 'none'){
		   opns[0].style.display = 'block';
		opns[1].style.display = 'block';
		opns[2].style.display = 'block';
		opns[3].style.display = 'block';
		}

		else{
//			opns[0].style.display = 'none';
		opns[1].style.display = 'none';
		opns[2].style.display = 'none';
		opns[3].style.display = 'none';
		}
});




    