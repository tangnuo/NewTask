

1、为什么没有Retrofit2的集成：

Retrofit2本身就是基于OkHttp3封装的，适合API形式，不适合URL形式。需要单独处理。

再者Retrofit提供了很多注解，不需要代码完成。如果再配合RxJava就更加个性了。



2、参考项目：https://github.com/7449/Retrofit_RxJava_MVP

源码参考：https://github.com/7449/Retrofit_RxJava_MVP/tree/master/app/src/main/java/com/example/y/mvp/network


