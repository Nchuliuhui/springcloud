package com.jxtele.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{
	/**
	 * RestTemplate 提供了多种便捷访问远程Http 服务的方法，是一个简单便捷的访问restful服务模板类，是spring 提供的用于访问Rest 服务的客户端模板数据集
	 * @return
	 */
	@Bean
	@LoadBalanced//Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public IRule myRule()
	{	//轮训算法
		//return new RoundRobinRule();
		//达到的目的，用我们重新选择的随机算法替代默认的轮询。
		//return new RandomRule();
		//先按照轮训的算法策略获取服务，如果获取的服务失败则会在指定的时间内进行重试，获取可用的服务
		return new RetryRule();

	}

}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.jxtele.tmall.UserServiceImpl">