#luck

IDE: idea

JDK: 1.8.0_171

FRAME: 
        
        spring-cloud: Greenwich.SR1
        Spring-boot: 2.1.4.RELEASE
        Maven: 3.5.3
        
DOC:
    https://cloud.spring.io/spring-cloud-static/Greenwich.RELEASE/single/spring-cloud.html
    
    
    
启动顺序：

        1.eureka
        
        2.gateway
        
        3.config  
           
        4.其他服务
        
        （服务注册到eureka默认需要30秒）
        
模块及端口说明：   

           eureka（服务治理）:8761
           
           config（远程配置中心）：9000 
           
           geteway（网关）8088
           
           order （订单模块）8082
           
           storage （存储模块）8081
           
           jpa （持久化模块依赖）
           
           common （通用模块依赖）
           
           parent (功能模块父依赖)
           
 手动刷新远程配置：
    localhost:8081/actuator/refresh    :post          