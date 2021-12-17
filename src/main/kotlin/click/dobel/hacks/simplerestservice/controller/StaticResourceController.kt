package click.dobel.hacks.simplerestservice.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class StaticResourceController {

  companion object {
    const val staticPath = "/static"
    const val index = "index.html"
  }

  @RequestMapping(staticPath)
  @ResponseStatus(HttpStatus.FOUND)
  fun home(request: HttpServletRequest, response: HttpServletResponse) {
    response.setHeader(HttpHeaders.LOCATION, "${request.requestURL}/")
  }

  @RequestMapping("${staticPath}/")
  fun homeSlash() = index
}
