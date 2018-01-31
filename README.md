# File uploader
 
## Spring boot application for uploading files with a REST API
usage :
* set up root.path in application.properties (ex : /opt/uploads)
* launch app : `mvn spring-boot:run`
* curl command : `curl -X POST -F 'file=@/home/scapillier/testUpload.txt' http://localhost:8080/api/clients/123/upload/file1.txt`
