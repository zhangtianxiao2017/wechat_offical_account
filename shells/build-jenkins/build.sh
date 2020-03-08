# 打包
mvn clean package

# 常量
projectLocation="/home/ubuntu/projects"
dockerName="wechatofficalaccount"
dockerPort="80"
# dockerJarVolumn="/home/ubuntu/.jenkins/workspace/wechat_public_access/target/public_access-1.0.jar"
dockerJarVolumn=${projectLocation}/${dockerName}/jar

mkdir ${projectLocation}/${dockerName}
mkdir ${projectLocation}/${dockerName}/jar

# 复制打包后的jar包
cp ./target/*.jar ${dockerJarVolumn}/app.jar

# 停止并删除现有容器
echo "${dockerName}:容器开始停止"
sudo docker stop ${dockerName}
sudo docker rm ${dockerName}
echo "${dockerName}:容器已停止"

echo "${dockerName}:重新启动容器"
sudo docker run --name ${dockerName} -d -p ${dockerPort}:8080 -v ${dockerJarVolumn}:/jar ztx/openjdk:1.0.0
echo "${dockerName}:容器启动成功"