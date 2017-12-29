function compile() {
	request.open("POST", "/", true);
	request.setRequestHeader("Content-type", "application/json");
	request.send(JSON.stringify({code:encodeURIComponent(editor.getSession().getDocument().getValue())}));
}

request = new ajaxRequest();
		
request.onreadystatechange = function() {
	if (this.readyState == 4) {
		if (this.status == 200) {
			if(this.responseText != null) {
				alert(this.responseText);
			}
		}
	}
}

function ajaxRequest() {
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