package com.dahuaboke.hhh.bean;

import com.dahuaboke.hhh.annotation.HhhClient;
import com.dahuaboke.hhh.bean.HhhFactoryBean;
import com.dahuaboke.hhh.consts.HhhConst;
import com.dahuaboke.hhh.model.HhhClientModel;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * author: dahua
 * date: 2023/11/20 11:30
 */
@Component
public class HhhClientBeanProcessor implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<BeanDefinition> beanDefinitionSet = new LinkedHashSet();
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(
                    AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
        scanner.addIncludeFilter(new AnnotationTypeFilter(HhhClient.class));
        String basePackages = environment.getProperty(HhhConst.SCAN_BASE_PACKAGE);
        for (String basePackage : basePackages.split(",")) {
            beanDefinitionSet.addAll(scanner.findCandidateComponents(basePackage));
        }

        for (BeanDefinition candidateComponent : beanDefinitionSet) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                Assert.isTrue(annotationMetadata.isInterface(), "@HhhClient can only be specified on an interface");
                Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(HhhClient.class.getCanonicalName());
                registerFeignClient(registry, annotationMetadata, attributes);
            }
        }
    }

    private void registerFeignClient(BeanDefinitionRegistry registry,
                                     AnnotationMetadata annotationMetadata, Map<String, Object> attributes) {
        String className = annotationMetadata.getClassName();
        Class clazz = ClassUtils.resolveClassName(className, null);
        String name = (String) attributes.get("name");
        String url = (String) attributes.get("url");
        HhhClientModel hhhClientModel = new HhhClientModel(name, url, clazz);
        HhhFactoryBean hhhFactoryBean = new HhhFactoryBean(hhhClientModel);
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(clazz, (Supplier) () -> hhhFactoryBean.getObject());
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        definition.setLazyInit(true);
        BeanDefinitionHolder holder = new BeanDefinitionHolder(definition.getBeanDefinition(), className);
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
