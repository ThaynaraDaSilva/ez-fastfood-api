package br.com.fiap.ez.fastfood.frameworks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
    }
	
	 @Override
	public void addCorsMappings(CorsRegistry registry) {
        // Allow all origins and methods for testing purposes
        registry.addMapping("/**")
        		.allowedOriginPatterns("http://9175-177-190-77-68.ngrok-free.app") // Adicione todas as URLs específicas aqui
        		//.allowedOrigins("http://localhost:8080", "https://9175-177-190-77-68.ngrok-free.app", "http://9175-177-190-77-68.ngrok-free.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                .allowedHeaders("Authorization", "Content-Type", "*")
                .allowCredentials(true);
    }
}
