# AI-Shop-server
This is the Serve, developed in SpringBoot Java, for the AI-SHOP web project.
Run GptHelperApplication to switch on the server.

In addition, you will need to create a src/main/resources/application.properties file, fill out database information and your own API_KEY. An format example is:

spring.jpa.hibernate.ddl-auto=update
spring.datasource.url= <Your database url>
spring.datasource.username= <Your database username>
spring.datasource.password= <Your database password>

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.open-in-view=false
API_KEY= <Your api keys>
