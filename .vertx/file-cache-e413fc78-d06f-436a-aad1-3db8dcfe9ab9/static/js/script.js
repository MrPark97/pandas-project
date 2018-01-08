request12 = new ajaxLolRequest();


function compileIt() {
	document.getElementById('loading-transparent').setAttribute('style', 'display: block;');
	document.getElementById('loading').setAttribute('style', 'height:200px; width: 200px;');

	request12.open('POST', '/', true);
	request12.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');


	var input = document.getElementById("input-text").value;
	if(typeof input === "undefined") {
		input = "";
	}
	request12.send("code="+encodeURIComponent(editor.getSession().getDocument().getValue())+"&input="+encodeURIComponent(input));
}
		
request12.onreadystatechange = function() {
	if (this.readyState == 4) {
		if (this.status == 200) {
			if(this.responseText != null) {
				var response = JSON.parse(this.responseText);

				setTimeout(function() {
					
					if(response.error != "") {
						var element = document.createElement("span");
						element.innerHTML = response.error;
						element.setAttribute('class', 'compilation-error');
						document.getElementById("compilation-output").innerHTML = "";
						document.getElementById("compilation-output").append(element);
					} else {
						document.getElementById("compilation-output").innerHTML = response.output;
						for(i in response.warnings) {
							var element = document.createElement("span");
							element.innerHTML = response.warnings[i];
							element.setAttribute('class', 'compilation-warning');
							document.getElementById("compilation-output").innerHTML += "<br />";
							document.getElementById("compilation-output").append(element);
						}
					}

					document.getElementById('loading-transparent').removeAttribute('style');
					document.getElementById('loading').removeAttribute('style');
				}, 1000);
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