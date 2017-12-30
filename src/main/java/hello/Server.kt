package hello

import com.google.gson.Gson
import io.vertx.core.MultiMap
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine
import java.net.URLDecoder
import io.vertx.groovy.ext.web.RoutingContext_GroovyExtension.getBodyAsJson
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.kotlin.ext.web.client.WebClientOptions


class Server : io.vertx.core.AbstractVerticle()  {
    override fun start() {

        var router = Router.router(vertx)

        // This body handler will be called for all routes
        router.route().handler(BodyHandler.create())

        //handles static files in static folder
        router.route("/static/*").handler(StaticHandler.create().setWebRoot("static").setCachingEnabled(false).setCacheEntryTimeout(1000).setMaxAgeSeconds(1))

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

        router.post().handler({ routingContext ->

            val code = routingContext.request().getParam("code")
            val input = routingContext.request().getParam("input")
            var codeInstance = Code(code, input)

            //val gson = Gson()
            //val json = gson.toJson(codeInstance)

            var options = WebClientOptions(userAgent = "Pandas/6.6.6")
            var client = WebClient.create(vertx, options)
            client.post(8000, "localhost", "/").sendJson(codeInstance, { ar ->
                if (ar.succeeded()) {
                    // Ok
                    var response = ar.result()
                    println("Got HTTP response with status ${response.bodyAsString()}")
                }
            })

            //codeInstance = gson.fromJson(json, hello.Code::class.java)
            routingContext.response().putHeader("content-type", "text/html").end(codeInstance.code)
        })



        vertx.createHttpServer().requestHandler({ router.accept(it) }).listen(8080)
    }
}