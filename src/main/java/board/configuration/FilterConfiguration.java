package board.configuration;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class FilterConfiguration {
	@Bean
	public FilterRegistrationBean<HiddenHttpMethodFilter> filterRegistrationBean() {
		FilterRegistrationBean<HiddenHttpMethodFilter> filterRegBean = new FilterRegistrationBean<HiddenHttpMethodFilter>(new HiddenHttpMethodFilter());
		filterRegBean.setUrlPatterns(Arrays.asList("/*"));
		return filterRegBean;
	}
}
