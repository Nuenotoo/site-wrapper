###### site-wrapper

该组件主要封装返回值response，自动封装成统一的json格式。该特性可以使用boot.site.enable=false来关闭，默认开启。使用@ExceptionHandler对controller层的异常进行拦截

###### how to run
boot-site,直接run Application.main。
在浏览器输入http://localhost:8080/hello
返回 {
       "code": 0,
       "data": "hello world",
       "msg": "成功"
   }