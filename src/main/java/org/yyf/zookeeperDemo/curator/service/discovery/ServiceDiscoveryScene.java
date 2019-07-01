package org.yyf.zookeeperDemo.curator.service.discovery;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.*;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.curator.x.discovery.strategies.RoundRobinStrategy;
import org.yyf.zookeeperDemo.curator.ClientUtil;
import org.yyf.zookeeperDemo.curator.service.domain.InstanceDetails;
import org.yyf.zookeeperDemo.curator.service.domain.ServiceDetail;

/**
 * Created by @author yyf on 2019-03-26.
 */
public class ServiceDiscoveryScene {
    public static void main(String[] args) throws Exception {

        ServiceDiscovery<InstanceDetails> serviceDiscovery = null;
        try {
            CuratorFramework client = ClientUtil.client();
            serviceDiscovery = ServiceDiscoveryBuilder.builder(InstanceDetails.class)
                    .client(client)
                    .basePath(ServiceDetail.REGISTER_ROOT_PATH)
                    .serializer(new JsonInstanceSerializer<>(InstanceDetails.class))
                    .build();
            serviceDiscovery.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //

        ServiceProvider<InstanceDetails> serviceProvider = serviceDiscovery.serviceProviderBuilder()
                .serviceName("mockService")//这就是我们在注册端提供的name，在spring cloud中就是spring.application.name
                .providerStrategy(new RoundRobinStrategy<>())//从多个实例中选择一个实例的策略，RoundRobin,Random,Sticky
                .downInstancePolicy(new DownInstancePolicy())//决定一个isntance是否已经死亡的策略对象
//                .threadFactory("serviceProviderOfMockService")
//                .additionalFilter()
                .build();

        serviceProvider.start();
        ServiceInstance<InstanceDetails> instance = serviceProvider.getInstance();
        System.out.println(instance);
    }
}
