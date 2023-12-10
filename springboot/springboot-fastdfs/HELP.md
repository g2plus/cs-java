docker pull delron/fastdfs

docker run -d --network=host --name tracker -v /var/fdfs/tracker:/var/fdfs delron/fastdfs tracker

docker run -d --network=host --name storage -e TRACKER_SERVER=ip:22122 -v /var/fdfs/storage:/var/fdfs -e GROUP_NAME=group1 delron/fastdfs storage

https://blog.csdn.net/wlwlwlwl015/article/details/52619851
#docker安装
https://blog.csdn.net/qq_37534947/article/details/106429656

ps: 需要放行23000,22122,8888端口
