	function openSlideMenu(){
document.getElementById('side-menu').style.width = '240px';
//document.getElementById('main').style.marginLeft = '0px';
// document.body.style.backgroundColor = "#FCF2F2";
}

function closeSideMenu(){
	document.getElementById('side-menu').style.width='0';
//	document.getElementById('main').style.marginLeft = '0';
	 document.body.style.backgroundColor = " #f4f4f4";
}


var dropdown  = document.getElementsByClassName("setting-dd");
var i;

for(i = 0; i < dropdown.length; i++){
	dropdown[i].addEventListener("click", function(){
		this.classList.toggle("active");

		var dropdownContent = this.nextElementSibling;
        if(dropdownContent.style.display === "block"){
        	dropdownContent.style.display = "none";
        } else{
        	 dropdownContent.style.display = "block";
        	 document.getElementById('setting-id').style.paddingBottom = '0';
        }
	})
}









