# NewTask
android学习、提高




### NewTask1.0

https://github.com/tangnuo/NewTask/tree/newTask1.0

学习过程中的代码，尝试使用各种开源库。

- module化：base,network,imageloader,util...
- Android Architecture Components
- MVVM，MVP封装
- ButterKnife
- greenDao
- livedata
- room
- viewModel
- Rxjava2+Retrofit2

### NewTask2.0

组建化重构项目

> 注意：目前项目在重构之中，只有基本的框架，后续会逐渐补全相应模块的内容。

#### 工程结构

- APP：壳工程
- module_common：通用业务模块（继承自module_lib）
- module_learn：学习模块
- module_lib：通用的基础模块（适合任何项目）
- module_main：入口模块模块
- module_video：视频模块




> 组件开发（ARouter），能独立运行，也可以共同运行。


### MVP中：
view：放视图接口，实现在activity、fragment中；


presenter：这些接口可以根据业务提取共同接口。


model：数据请求。只放接口声明，实现放presenter中。


- 方法一：按照MVP明确划分，内部Impl.（基本结构）

- 方法二：根据模块提取共同接口（接口包含接口、view、presenter都放入），参考OSchina。


### 官方刷新控件+xRecyclerView:

进一步封装，参考：OSChina：RecyclerRefreshLayout。



### 任务：

一个MVP例子（数据请求）；
封装adapter；
研究饿了么代码；
研究字母列表；
权限预申请；

module_lib分离module_common中涉及到业务逻辑的部分，module_lib存放通用的功能。
module_common依赖于module_lib，module_common中存放业务相关的代码。
所有的的module都依赖于module_common
