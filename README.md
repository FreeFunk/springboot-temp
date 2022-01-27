# springboot-model-core
模块释义：
~~~~
此模块作为工具类，在其他模块引入此模块即可用到所有的工具类。
~~~~
项目路径：
~~~~
|_shiro：存放spring切面注解基础公共代码，涉及权限验证切面
   |__annotation：注解抽象类(interface)，自定义注解
   |__aop：注解抽象实现类，处理基础逻辑，指定切面标识
|_code：存放标准公共常用代码，例如基础连接配置、线程、文件操作等等
   |__config：基础连接配置类相关代码
   |_utils：基础逻辑处理相关代码
|_common：公共冗余代码抽取的公共方法代码
|_core：存放核心模块代码，例如多模块使用同一对象类和方法，创建规则：模块名字下定义即可
|_exception：存放异常错误信息处理代码
~~~~
# springboot-model-one
模块释义：
~~~~
此模块作为应用模块，主要程序入口。
~~~~
项目路径：
~~~~
    |_annotation：放置项目自定义注解
    |_aspect：放置切面代码
    |_config：放置配置类
    |_constant：放置常量、枚举等定义
       |__consist：存放常量定义
       |__enums：存放枚举定义
    |_controller：放置控制器代码
    |_filter：放置一些过滤、拦截相关的代码
    |_mapper：放置数据访问层代码接口
    |_model：放置数据模型代码
       |__entity：放置数据库实体对象定义
       |__dto：存放数据传输对象定义
       |__vo：存放显示层对象定义
    |_service：放置具体的业务逻辑代码（接口和实现分离）
       |__intf：存放业务逻辑接口定义
       |__impl：存放业务逻辑实际实现
    |_utils：放置工具类和辅助代码
|_framework：存放程序启动类、数据库、knife...需要程序开启马上加载的配置或者连接等工具

    |_mapper：存放mybatis的XML映射文件（如果是mybatis项目）
    |_static：存放网页静态资源，比如下面的js/css/img
       |__js：前端脚本js
       |__css：前端样式css
       |__img：前端图片
       |__font：前端使用的字体
       |__等等
    |_template：存放网页模板，比如thymeleaf/freemarker模板等
       |__file：文件模板
       |__等等
    |_application.yml：基本配置文件
    |_application-config.yml：除去springboot的基础配置文件
    |_application-database.yml  数据库连接配置


~~~~
