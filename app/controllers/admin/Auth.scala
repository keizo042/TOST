package controllers.admin

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.json._

import java.security.MessageDigest

object Auth extends Controller {
  def login = Action { request =>
    Ok(views.html.admin.login())
  }

  def logout = Action {
    Redirect(controllers.routes.Application.index).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  def loginreq = Action { implicit request =>
    val rts = (code:Boolean,msg:String) => Json.obj(
      "code" -> Json.toJson(code),
      "msg"  -> Json.toJson(msg)
    )
    request.body.asJson.map { json =>
      val md = MessageDigest.getInstance("SHA-384")
      var sb = new StringBuffer()

      Play.application.configuration.getString("tost.adminPassword") match {
        case Some(adminpassword) => md.update(adminpassword.getBytes)
        case None => md.update("tostadmin".getBytes)
      }

      md.digest.foreach(b => sb.append(Integer.toString((b & 0xff) + 0x100,16).substring(1)))

      val hashedPassword = sb.toString

      (json \ "password").asOpt[String] match {
        case Some(p) => {
          if(hashedPassword == p){
            Ok(rts(true,controllers.admin.routes.Application.index.url)).withSession("user" -> "admin")
          }else{
            BadRequest(rts(false,"invalid password"))
          }
        }
        case None => { BadRequest(rts(false,"NOT FOUND `password` contents in your json")) }
      }
    }.getOrElse { BadRequest(rts(false,"invalid json"))}
  }
}
