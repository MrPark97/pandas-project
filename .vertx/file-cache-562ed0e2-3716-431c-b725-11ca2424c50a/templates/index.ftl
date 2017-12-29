<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>PANDAS | {{${context.name}}}</title>
	<link rel="stylesheet" href="/static/css/style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="/static/node_modules/bulma/css/bulma.css" type="text/css" media="all" />
  <link rel="stylesheet" href="/static/css/font-awesome.min.css" type="text/css" media="all" />
  <link rel="import" href="/static/bower_components/paper-input/paper-textarea.html">
  <script src="/static/bower_components/webcomponentsjs/webcomponents-lite.js"></script>
  <script src="/static/bower_components/webcomponentsjs/webcomponents-lite.js"></script>
  <!-- <script src="https://unpkg.com/kotlin-runcode@1/dist/runcode.min.js" data-selector="code"></script> -->
</head>
<body>


<nav class="navbar is-dark is-fixed-top" role="navigation" aria-label="main navigation">
  <div class="navbar-brand">
    <a class="navbar-item" href="https://bulma.io">
      <img src="/static/img/panda-white.svg" alt="Bulma: a modern CSS framework based on Flexbox" width="112" height="28">

      PANDAS
    </a>
  </div>
</nav>

<style type="text/css" media="screen">
    #panda-ide {
        opacity: 0.8;
    }
</style>
<div id="my-ide">

<div id="main-ide" class="columns">
<div id="panda-ide" data-language="kotlin" class="column is-9">

  package hello

  import io.vertx.ext.web.Router

  class Server : io.vertx.core.AbstractVerticle()  {
      override fun start() {

          var router = Router.router(vertx)

          router.route().handler({ routingContext ->
              routingContext.response().putHeader("content-type", "text/html").end("Hello World!")
          })

          vertx.createHttpServer().requestHandler({ router.accept(it) }).listen(8080)
      }
  }

</div>
<div id="input-ide" class="column is-3">
  <paper-textarea label="write the input data here" rows="3">
  </paper-textarea>


  <div class="field is-pulled-right" id="file-input-ide">
    <div class="file is-info">
      <label class="file-label">
        <input class="file-input" type="file" name="resume">
        <span class="file-cta">
          <span class="file-icon">
            <i class="fa fa-upload"></i>
          </span>
          <span class="file-label">
            FILE
          </span>
        </span>
      </label>
    </div>
  </div>
</div>
</div>

<div class="columns">
<div id="output-ide" class="column is-9">
  <article class="message">
    <div class="message-body" id="compilation-output">
      Output will be here...
    </div>
  </article>
</div>
<div id="run-ide" class="column is-3 has-text-centered">
  <a class="button is-success is-outlined" id="run-button-ide" onclick="compileIt()">
    <span>Run</span>
    <span class="icon is-small">
      <i class="fa fa-play-circle-o"></i>
    </span>
  </a>
</div>
</div>

</div>

<footer class="footer">
  <div class="container">
    <div class="content has-text-centered">
      <p>
        <strong class="Chinese">PANDAS</strong> by <a href="https://github.com/MrPark97">Mr. Park</a>. The source code is licensed
        <a href="http://opensource.org/licenses/mit-license.php"><i class="fa fa-balance-scale"></i> MIT</a>.
      </p>
    </div>
  </div>
</footer>

<div id="loading-transparent">
</div>

<img src="/static/img/loading.gif" id="loading" />


<script src="/static/ace-builds-master/src-min-noconflict/ace.js"></script>


<script>
    var theme = "solarized_light";
    var hours = new Date().getHours();
    if(hours >= 18 || hours <= 6) {
      theme = "solarized_dark";
    }

    var editor = ace.edit("panda-ide");
    editor.setTheme("ace/theme/" + theme);



    editor.getSession().setMode("ace/mode/kotlin");

    //alert(editor.getSession().getDocument().getValue());
</script>

<script src="/static/js/script.js"></script>
</body>
</html>