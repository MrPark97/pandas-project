package hello

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler

class Server : io.vertx.core.AbstractVerticle()  {
    override fun start() {

        var router = Router.router(vertx)

        //handles static files in static folder
        router.route("/static/*").handler(StaticHandler.create().setWebRoot("static"))

        router.route("/").handler({ routingContext ->
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!")
        })



        vertx.createHttpServer().requestHandler({ router.accept(it) }).listen(8080)
    }
}