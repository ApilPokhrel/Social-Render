

function uadd(a){
var user = document.getElementsByClassName("uadd")[a].value;
console.log(user);

axios({
	method: 'post',
	url: '/sumUser/Add',
	data:{
		userId: user
	}
}).then(function(res){
	console.log('iam in form '+res.data);

	alert("user add succesfully");
    var btn = document.getElementsByClassName("user-div");
    btn[a].style.display = 'none';
	
	// document.getElementById("popcontent").innerHTML = "user add succesfully";
	// document.getElementById("popups").click();
	
}).catch(function(err){
	console.log(err);

    alert("Error adding user");
	// document.getElementById("popcontent").innerHTML = "Error adding user";
	// document.getElementById("popups").click();
})
};



document.getElementById("popclose").addEventListener("click", function(){
	document.getElementById("sugcont").style.zIndex = "1";

});
