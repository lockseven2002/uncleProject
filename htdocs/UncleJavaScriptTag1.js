var xmlhttp = new XMLHttpRequest();
var phpFileName = "UncleSreverTag1";
Connection(phpFileName);
function Connection(phpFileName){
	var url = "http://127.0.0.1:8080/"+phpFileName+".php";
	xmlhttp.onreadystatechange=function() {
        if (this.readyState == 4 && this.status == 200) {
            MyResponse(this.responseText,phpFileName);
        }
    }
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
function MyResponse(response,phpFileName) {
    var arr = JSON.parse(response);
	//CustomerTable decide by PHP File Name 
    CustomerTable(arr,phpFileName);
}

function CustomerTable(arr,phpFileName){
	var out = "<table>";
	if(phpFileName === "UncleSreverTag1"){
		document.getElementById("id01").style.marginLeft = "35%";
		out += "<tr>"+
	               "<td>銷售日期</td>"+
	               "<td>銷售金額</td>"+
	           "</tr>";
	    for(var i = 0; i < arr.length; i++) {
            out += "<tr>"+
		               "<td>"+arr[i].date+"</td>"+
		               "<td>"+arr[i].sum+"</td>"+
		           "</tr>";
        }
	}else if(phpFileName === "UncelServerNewProject"){
		document.getElementById("id01").style.marginLeft = "0%";
		out += "<tr>"+
	               "<td>大品項</td>"+
	           "</tr>";
        for(i = 0; i < arr.length; i++) {
            out += "<tr>"+
			           "<td>"+arr[i].field_species+"</td>"+
                   "</tr>";
        }
	}else{
		document.getElementById("id01").style.marginLeft = "0%";
		out += "<tr>"+
	               "<td>細項</td>"+
				   "<td>價錢</td>"+
	           "</tr>";
        for(i = 0; i < arr.length; i++) {
            out += "<tr>"+
			           "<td>"+arr[i].detailsName+"</td>"+
					   "<td>"+arr[i].price+"</td>"+
                   "</tr>";
        }
	}
	out += "</table>";
	document.getElementById("id01").innerHTML = out;
}

function NewProjectAndTabchange(UncelServerNewProject,evt){
	var i, tablinks;
    tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
	    tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
	//User event listen
    evt.currentTarget.className += " active";
	//UncelServerNewProject===>PHP File Name
	connection(UncelServerNewProject);
}
// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();