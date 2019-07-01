package org.yyf.zookeeperDemo.curator.service.register;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceInstanceBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.yyf.zookeeperDemo.curator.service.domain.ServiceDetail;

import java.util.concurrent.TimeUnit;


public class ServiceRegisterScene {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
        client.blockUntilConnected();


        ServiceInstance<ServiceDetail> instance = createServiceInstance();
        System.out.println(instance);
        //serviceDiscovery的实质是一个缓存serviceInstance的容器，在其成员变量services中
        ServiceDiscovery<ServiceDetail> serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetail.class)
                .client(client)
                .serializer(new JsonInstanceSerializer<ServiceDetail>(ServiceDetail.class))
                .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                .build();

        //服务注册  
        serviceDiscovery.registerService(instance);
        serviceDiscovery.start();

        TimeUnit.MINUTES.sleep(70);

        serviceDiscovery.close();
        client.close();
    }
    //创建一个ServiceInstance，代表着提供服务的一台jvm实例
    //注意service的id默认值是uuid
    private static ServiceInstance<ServiceDetail> createServiceInstance()  {
        try {
            ServiceInstanceBuilder<ServiceDetail> builder = ServiceInstance.builder();
            return builder.address("127.0.0.1")
                    .port(8080)
                    .name("mockService")
                    .payload(new ServiceDetail("web程序服务", 1))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}  

