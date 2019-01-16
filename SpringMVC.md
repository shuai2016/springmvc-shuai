1. 概述

2. HelloWorld
   ```properties
   <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
       <property name="prefix" value="/WEB-INF/views/"></property>
       <property name="suffix" value=".jsp"></property>
   </bean>
   ```

   1. 使用@RequestMapping 注解来映射请求的 URL
   2. 返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器，会做如下解析：
      1. 通过 prefix + returnVal + suffix 这样的方式得到实际的物理视图，然后做转发操作

3. RequestMapping_修饰类

4. RequestMapping_请求方式

   1. @RequestMapping 除了可以使用请求URL映射请求外，还可以使用请求方法，请求参数及请求头映射请求
   2. @RequestMapping的value、method、params及handers分别表示请求URL、请求方法、请求参数及请求头的映射条件，它们之间是与的关系，联合使用多个条件可让请求映射更加精确化。

5. RequestMapping_请求参数&请求头

   ```java
   @RequestMapping(value = "testParamsAndHeaders"
         , params = {"username", "age!=10"}
         , headers = {"Accept-Language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"})
   public String testParamsAndHeaders() {
      return SUCCESS;
   }
   ```

   1. 使用 params 和 headers 来更加精确的映射请求，params 和 headers 支持简单的表达式。

6. RequestMapping_Ant路径

   ```java
   @RequestMapping("/testAntPath/*/abc")
   public String testAntPath(){
      return SUCCESS;
   }
   ```

   1. Ant 风格资源地址支持3种匹配符：
      1. ?：匹配文件名中的一个字符
      2. *：匹配文件名中的任意字符
      3. **：匹配多层路径

7. RequestMapping_PathVariable

   ```java
   @RequestMapping("/testPathVariable/{id}")
   public String testPathVariable(@PathVariable("id") Integer id) {
      System.out.println("testPathVariable : " + id);
      return SUCCESS;
   }
   ```

   1. @PathVariable 可以来映射 URL 中的占位符到目标方法的参数中。

8. RequestMapping_HiddenHttpMethodFilter过滤器

   1. HTTP协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE，它们分别对应四种基本操作：GET用来获取资源，POST用来新建资源，PUT用来更新资源，DELETE用来删除资源。

   2. HiddenHttpMethodFilter：浏览器form表单只支持GET 与 POST 请求，而DELETE、PUT 等 method 并不支持，Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的http方法，使得支持GET、POST、PUT 与 DELETE 请求。

   3. 发送PUT或DELETE请求

      1. 配置HiddenHttpMethodFilter

         ```xml
         <filter>
             <filter-name>hiddenHttpMethodFilter</filter-name>
             <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
         </filter>
         <filter-mapping>
             <filter-name>hiddenHttpMethodFilter</filter-name>
             <url-pattern>/*</url-pattern>
         </filter-mapping>
         ```

      2. 请求时，表单携带一个name="_method" 的隐藏域，值为DELETE 或 PUT

         ```html
         <form action="testRest/1" method="post">
             <input type="hidden" name="_method" value="PUT">
             <input type="submit" value="TestRest PUT">
         </form>
         ```

      3. Controller方法

         ```java
         @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
         public String testRestPut(@PathVariable("id") Integer id) {
            System.out.println("TestRest PUT : " + id);
            return SUCCESS;
         }
         ```

9. RequestParam注解

   ```java
   @RequestMapping(value = "/testRequestParam")
   public String testRequestParam(@RequestParam(value = "username") String un
         , @RequestParam(value = "age",required = false,defaultValue = "0") int age) {
      System.out.println("testRequestParam,username : " + un + " , age : " + age);
      return SUCCESS;
   }
   ```

   1. @RequestParam 来映射请求参数
      1. value：请求参数的参数名
      2. required：该参数是否必须，默认 true
      3. defaultValue：请求参数的默认值

10. RequestHeader注解

  ```java
  @RequestMapping(value = "/testRequestHeader")
  public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
     System.out.println("testRequestHeader,Accept-Language : " + al);
     return SUCCESS;
  }
  ```

  1. 映射请求头信息
  2. 用法同@RequestParam
  3. 了解

11. CookieValue注解

    ```java
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
       System.out.println("testCookieValue,sessionId : " + sessionId);
       return SUCCESS;
    }
    ```

12. 使用POJO作为参数

    1. SpringMVC 会按请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，支持级联属性。

    2. 实现

       1. Java代码

          ```java
          @RequestMapping(value = "/testPojo")
          public String testPojo(User user) {
             System.out.println("testPojo,user : " + user);
             return SUCCESS;
          }
          ```

       2. html代码

          ```html
          <form action="testPojo" method="post">
              <table style="text-align:center ">
                  <tr>
                      <td>username</td>
                      <td><input type="text" name="username"></td>
                  </tr>
                  <tr>
                      <td>password</td>
                      <td><input type="password" name="password"></td>
                  </tr>
                  <tr>
                      <td>age</td>
                      <td><input type="text" name="age"></td>
                  </tr>
                  <tr>
                      <td>email</td>
                      <td><input type="text" name="email"></td>
                  </tr>
                  <tr>
                      <td>province</td>
                      <td><input type="text" name="address.province"></td>
                  </tr>
                  <tr>
                      <td>city</td>
                      <td><input type="text" name="address.city"></td>
                  </tr>
                  <tr>
                      <td colspan="2"><input type="submit" value="submit"></td>
                  </tr>
              </table>
          ```

13. 使用Servlet原生API作为参数

    ```java
    @RequestMapping(value = "/testServletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
       System.out.println("testServletAPI");
       System.out.println(request);
       System.out.println(response);
       System.out.println(session);
       return SUCCESS;
    }
    ```

    1. HttpServletRequest
    2. HttpServletResponse
    3. HttpSession
    4. java.security.Principal
    5. Locale
    6. InputStream
    7. OutputStream
    8. Reader
    9. Writer

14. 处理模型数据之ModelAndView

    ```java
    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView() {
       System.out.println("testModelAndView");
       ModelAndView modelAndView = new ModelAndView(SUCCESS);
       modelAndView.addObject("time",new Date());
       return modelAndView;
    }
    ```

    1. 目标方法的返回值可以是ModelAndView类型，其中可以包含视图和模型信息
    2. SpringMVC 会把 ModelAndView 的 model 中的数据放到 request 域对象中。

15. 处理模型数据之Map

    ```java
    @RequestMapping(value = "/testMap")
    public String testMap(Map<String,Object> map) {
       System.out.println("testMap");
       map.put("time",new Date());
       return SUCCESS;
    }
    ```

    1. 目标方法可以添加Map 类型（实际上也可以是Model 类型或 ModelMap 类型）的参数

16. 处理模型数据之SessionAttributes注解

    ```java
    @Controller
    @SessionAttributes(value = {"user"}, types = {Date.class})
    public class SpringMVCTest {
       public static final String SUCCESS = "success";
       
       @RequestMapping(value = "/testSessionAttributes")
       public String testSessionAttributes(Map<String, Object> map) {
          System.out.println("testSessionAttributes");
          map.put("time", new Date());
          map.put("user", new User("A", "123", 18, "a@aa.com"));
          return SUCCESS;
       }
    }
    ```

    1. 该注解只能放到类的上面，而不能修饰方法。
    2. 通过属性名指定需要放到会话中的属性（实际上使用的是 value 属性值）
    3. 通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是 types 属性）

17. ModelAttribute注解值使用场景

18. ModelAttribute注解之实例代码

    ```java
    @ModelAttribute
    public void getUser(@RequestParam("id")Integer id, Map<String,Object> map){
       System.out.println("getUser");
       User bb = null;
       if (id != null){
          bb = new User(2, "BB", "123", 20, "a@aa.com");
          map.put("user",bb);
       }
       System.out.println(bb);
    }
    
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user) {
       System.out.println("testModelAttribute");
       System.out.println(user);
       return SUCCESS;
    }
    ```

    1. @ModelAttribute注解可以使用在方法上，也可以用来修饰目标方法POJO类型的入参

19. ModelAttribute注解之运行原理

20. ModelAttribute注解之源码分析

21. 如何确定目标方法POJO类型参数

    1. 确定一个key：目标方法的POJO属性使用@ModelAttribute修饰，则key值即为@ModelAttribute 的value 属性值，未使用@ModelAttribute修饰，key值为类名首字母小写。
    2. 在implicitModel 中查找 key 对应的对象，若存在（在@ModelAttribute 标记的方法中在Map中保存过，且Map的key和1中 确定的key 一致），则作为入参传入。
    3. 若不存在key 对应的对象，则检查当前的Handler 是否使用 @SessionAttributes 注解修饰，若使用了该注解，且 @SessionAttributes 注解的 value 属性值中包含了 key，则会从 HttpSession 中来获取 key 所对应的 value，若存在则直接传入到目标方法的入参中，若不存在则将抛出异常。
    4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key，则会通过反射来创建 POJO 类型的参数，传入为目标方法的参数。
    5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中，进而会保存到 request 中。

22. ModelAttribute注解修饰POJO类型的入参

23. SessionAttributes注解引发的异常

24. 视图解析流程分析

25. JstlView

26. mvc_view-controller标签

27. 自定义视图

28. 重定向

29. RESTRUL_CRUD_需求

30. RESTRUL_CRUD_显示所有员工信息

31. RESTRUL_CRUD_添加操作&表单标签

32. RESTRUL_CRUD_删除操作&处理静态资源

33. RESTRUL_CRUD_修改操作

34. 数据绑定流程分析

35. 自定义类型转换器

36. annotation-driven配置

37. InitBinder注解

38. 数据的格式化

39. JSR303数据校验

40. 错误消息的显示及国际化

41. 返回JSON

42. HttpMessageConverter原理

43. 使用HttpMessageConverter

44. 国际化_概述

45. 国际化_前两个问题

46. 国际化_通过超链接切换Locale

47. 文件上传

48. 第一个自定义的拦截器

49. 拦截器的配置

50. 多个拦截方法的执行顺序

51. 异常处理_ExceptionHandler注解

52. 异常处理_ResponseStatusExceptionResolver

53. 异常处理_DefaultHandlerExceptionResolver

54. 异常处理_SimpleMappingExceptionResolver

55. 运行流程图解

56. 源码解析

57. Spring整合SpringMVC_提出问题

58. Spring整合SpringMVC_解决方案

59. SpringIOC容器和SpringMVCIOC容器的关系

60. SpringMVC对比Struts2