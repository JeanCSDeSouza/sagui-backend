package br.com.unirio.sagui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.unirio.sagui.pdfupload.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class SaguiApplication {
	//@Bean
	//public BCryptPasswordEncoder bCryptPasswordEncoder() {
		  //return new BCryptPasswordEncoder();
	//}
	
	public static void main(String[] args) {
		SpringApplication.run(SaguiApplication.class, args);
	}
}
