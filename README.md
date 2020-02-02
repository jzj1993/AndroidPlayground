# AndroidPlayground

本项目用于Android学习，主要包括各种自行开发实现的工具类、View组件等。

大部分都是比较简单的组件，简单起见，就不分别建独立的工程和发布JCenter了，有需要可以直接复制代码。

代码结构如下：

- common模块：通用基础组件库

- app模块：各类组件。为了使代码组织更清晰，每个组件放在一个独立的SourceSet中，在app/build.gradle中配置。
    - app/src/main：Demo入口
    - app/src/androidTest：测试代码
    - app/src/xxx：xxx组件源码和Demo，含java代码和资源文件

- xxx模块：复杂的组件会考虑单独建一个Gradle模块
