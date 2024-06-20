# 进销存管理系统配置说明

## Database 数据库配置
使用Mysql运行`inventory_management_system.sql`即可导入数据库数据


## Backend 项目后端代码
在`backend/src/main/resources/application.yaml`中配置Mysql数据库连接URL与用户名密码

### 项目启动
配置完成数据库之后使用Maven配置所需代码包，然后运行`backend/src/main/java/com/backend/Main.java    ` 主类即可启动服务段
## Frontend 项目前端代码

如果部署服务器使用在
`frontend/src/main/resources/com/frontend/config/application.yaml` 
之中更改服务器链接地址
使用Maven配置所需Jar包之后运行`frontend/src/main/java/com/frontend/AppMain.java` 主类即可运行