otwb
2018-4-17 21:31:26 start coding


#### RESTful APIs
here is [api-docs](http://localhost:8080/otwb/swagger-ui.html)


*在接口中没有body和form的混合参数则全用@ApiImplicitParams，若有混合则混合使用@ApiImplicitParams和@ApiParam*

### Git 全局设置技巧

```
1、提交检出均不转换换行符

git config --global core.autocrlf false

2、拒绝提交包含混合换行符的文件

git config --global core.safecrlf true
```