package pierrot.tacos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class TacoSecurityApplication  implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoSecurityApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}
	
//	@Bean
//	public CommandLineRunner showBeans(ApplicationContext appCtx) {
//		return (args) -> {
//			log.info("following the Beans provided by Spring...");
//			String[] beanNames = appCtx.getBeanDefinitionNames();
//			Arrays.asList(beanNames).forEach(bn ->  log.info("{}",bn));
//			log.info("finally the total of Beans: {}", appCtx.getBeanDefinitionCount());
//		};
//	}

}
