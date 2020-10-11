# SpringBoot Auth demo REST APIs with GCP AppEngine

This project demonstrates how to use SpringBoot JWT token to secure your REST APIs

It also showcase how we can use springfox-swagger2 to document APIs

To use the code:
Refer to google documentation to create a cloud sql instance: https://cloud.google.com/sql/docs/mysql/create-instance

git clone git@github.com:abharku/SpringBootAuthentication.git


Update SpringBootAuthentication/src/main/resources/application.properties from cloud sql instance you have created

spring.cloud.gcp.sql.instance-connection-name
spring.cloud.gcp.sql.database-name
spring.datasource.username
spring.datasource.password

Replace following properties in pom.xml in appengine-maven-plugin section

<projectId>project-id</projectId>
 <cloudSdkPath>GCLOUD_SDK_PATH</cloudSdkPath>

 How to setup GCP CLOUD SDK is explained at https://cloud.google.com/sdk/docs/install

 To deploy to Appengine run below mvn command:

 clean install appengine:deploy

 Assuming that SDK is setup properly. If you want to run locally then make sure following environment variable is setup and pointing to a credential json file:

 GOOGLE_APPLICATION_CREDENTIALS

Use the token received from signin/Signup method and click the Authorize button on Swagger-ui

In the text box add "Bearer <token>"

After this you will be able to access get api/v1/users/{int} method which is the only method secured by auth header

A running version of the code is available below:

http://spring-demo-dot-starfish-k8s.nw.r.appspot.com/swagger-ui.html
