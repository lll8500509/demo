package com.example.demo.Config.database.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.example.demo.Config.database.DataSourceNames;
import com.example.demo.Config.database.DynamicDataSource;
import com.example.demo.Config.database.annotation.DataSource;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered{

	 protected Logger logger = LoggerFactory.getLogger(getClass());
	 
	@Override
	public int getOrder() {
		return 1;
	}
	
	@Pointcut("@annotation(com.example.demo.Config.database.annotation.DataSource)")
    public void dataSourcePointCut() {

    }
	
	@Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            DynamicDataSource.setDataSource(DataSourceNames.NOVEL);
            logger.debug("set datasource is " + DataSourceNames.NOVEL);
        }else {
            DynamicDataSource.setDataSource(ds.name());
            logger.debug("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            logger.debug("clean datasource");
        }
    }
}
