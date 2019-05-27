
 function imgPreview(url, x){
    return `<img src="${url}" class="imgmanu" >`;
   } 
  

   function vidPreview(url){
  return `<video id="vid1" style="height: 240px; width:100%; " controls>
     <source src="${url}" id="video_here">
    Your browser does not support HTML5 video.
    </video`;
    }


  var imgfile = document.getElementById("img_file");
  var vidfile = document.getElementById("vid_file");
  var dialog = document.getElementById("sd");
  var preview = document.getElementById("preview");
  var imgBtn = document.getElementById("imgBtn");
  var vidBtn = document.getElementById("vidBtn");
  var close = document.getElementById("closepreview");

preview.style.display = 'none';
close.style.display = 'none';

close.addEventListener("click", function(){
   hide();
});

function hide(){
  preview.style.display = 'none';
  close.style.display = 'none';
  preview.innerHTML = '';
  imgfile.value = null;
  vidfile.value = null;
}

  if(imgfile){
  imgfile.addEventListener("change", function(){
        onChangeImg(this);
  });
    };

  
  imgBtn.addEventListener("click", function(){
  preview.innerHTML = '';
  imgfile.value = null;
    imgfile.click();

  });


  vidBtn.addEventListener("click", function(){
 preview.innerHTML = '';
  vidfile.value = null;
    vidfile.click();

  });


    function onChangeImg(input){
      if(input.files[0]){
          
         var length;

         if(input.files.length < 4){
          
           length = input.files.length;
         }

         else{
          length = 4;
         }

      console.log("length is "+length);
      for(var i= 0; i < length; i++){
        if(input.files[i].size < 1024*1024*50){
        var context = input.files[i].type;
        var str = context.split("/");

        if(str[0] === "image"){

             reader = new FileReader();
        reader.onload = function(e){
         preview.style.display = 'block';
         close.style.display = 'block';
          html = imgPreview(e.target.result, i);
           
          preview.innerHTML += html;
        }

        reader.readAsDataURL(input.files[i]);
        }

        }
      }
      }
    }



    if(vidfile){
      vidfile.addEventListener("change", function(){
           onChangeVid(this);
      });
    };



    function onChangeVid(input){
      if(input.file || input.files[0]){
         if(input.files[0].size < 1024*1024*450){

        var context = input.files[0].type;
        var str = context.split("/");

        if(str[0] === "video"){

           preview.style.display = 'block';
           close.style.display = 'block';
         var html = vidPreview(URL.createObjectURL(input.files[0]));

          preview.innerHTML = html;

         }
        }
      }
     }
    
    
    
    
    var option = document.getElementById("options");
    var opns = document.getElementsByClassName("inp");
//    opns[0].style.display = 'none';
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
//    			opns[0].style.display = 'none';
    		opns[1].style.display = 'none';
    		opns[2].style.display = 'none';
    		opns[3].style.display = 'none';
    		}
    });
