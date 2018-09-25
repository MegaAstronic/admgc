package net.moonj.admgc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan({"net.moonj.admgc.genecode.user.mapper","net.moonj.admgc.mapper"})
public class MybatisPlusConfig {
  /**
   * 分页插件
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
      return new PaginationInterceptor();
  }
}