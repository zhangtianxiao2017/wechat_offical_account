
mvn clean package

dockerName="wechatpublicaccess"
dockerPort="9000"
dockerJarVolumn="/home/ubuntu/.jenkins/workspace/wechat_public_access/target/public_access-1.0.jar"
# 停止并删除现有容器

echo "${dockerName}:容器开始停止"
sudo docker stop ${dockerName}
sudo docker rm ${dockerName}
echo "${dockerName}:容器已停止"

echo "${dockerName}:重新启动容器"
# sudo docker run --name test -d -p 9000:8080 -v /home/ubuntu/.jenkins/workspace/wechat_public_access/target/public_access-1.0.jar:/jar/app.jar ztx/openjdk:1.0.0
sudo docker run --name ${dockerName} -d -p ${dockerPort}:8080 -v ${dockerJarVolumn}:/jar/app.jar ztx/openjdk:1.0.0
