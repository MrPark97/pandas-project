package hello

import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine

class Server : io.vertx.core.AbstractVerticle()  {
    override fun start() {

        var router = Router.router(vertx)

        //handles static files in static folder
        router.route("/static/*").handler(StaticHandler.create().setWebRoot("static"))

        // In order to use a template we first need to create an engine
        var engine = FreeMarkerTemplateEngine.create()

        // Entry point to the application, this will render a custom template.
        router.get().handler({ ctx ->
            // we define a hardcoded title for our application
            ctx.put("name", "TRY KOTLIN")

            // and now delegate to the engine to render it.
            engine.render(ctx, "templates/index.ftl", { res ->
                if (res.succeeded()) {
                    ctx.response().end(res.result())
                } else {
                    ctx.fail(res.cause())
                }
            })
        })

        /*router.route("/").handler({ routingContext ->
            routingContext.response().putHeader("content-type", "text/html").end("Hello World!")
        })*/



        vertx.createHttpServer().requestHandler({ router.accept(it) }).listen(8080)
    }
}