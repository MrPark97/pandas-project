request12 = new ajaxLolRequest();


function compileIt() {
	request12.open('POST', '/', true);
	request12.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	request12.send("code="+encodeURIComponent(editor.getSession().getDocument().getValue()));
}
		
request12.onreadystatechange = function() {
	if (this.readyState == 4) {
		if (this.status == 200) {
			if(this.responseText != null) {
				document.getElementById("compilation-output").innerHTML = this.responseText;
			}
		}
	}
}

function ajaxLolRequest() {
	try {
		var request = new XMLHttpRequest();
	} catch(e1) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e2) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e3) {
				request = false;
			}
		}
	}
	return request;
}