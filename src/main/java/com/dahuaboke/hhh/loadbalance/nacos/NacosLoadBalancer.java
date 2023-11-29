package com.dahuaboke.hhh.loadbalance.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.dahuaboke.hhh.consts.HhhConst;
import com.dahuaboke.hhh.exception.NoInstanceException;
import com.dahuaboke.hhh.loadbalance.LoadBalancer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * author: dahua
 * date: 2023/11/29 9:43
 */
public class NacosLoadBalancer implements LoadBalancer, EnvironmentAware {

    private Environment environment;

    @Override
    public String getInstanceAddress(String name) {
        try {
            String address = environment.getProperty(HhhConst.NACOS_ADDR);
            NamingService namingService = NamingFactory.createNamingService(address);
            Instance instance = namingService.selectOneHealthyInstance(name);
            return instance.getIp() + instance.getPort();
        } catch (NacosException e) {
            throw new NoInstanceException();
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
