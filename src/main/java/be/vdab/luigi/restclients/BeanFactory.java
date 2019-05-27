package be.vdab.luigi.restclients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.net.URL;

//@Configuration
public class BeanFactory {
    private final URL ecbKoersURL;
    private final URL fixerKoersURL;

    public BeanFactory(@Value("${ecbKoersURL}")URL ecbKoersURL, @Value("${fixerKoersURL}")URL fixerKoersURL) {
        this.ecbKoersURL = ecbKoersURL;
        this.fixerKoersURL = fixerKoersURL;
    }
    @Bean
    ECBKoersClient ecbKoersClient(){
        return new ECBKoersClient(ecbKoersURL);
    }
    @Bean
    FixerKoersClient fixerKoersClient(){
        return new FixerKoersClient(fixerKoersURL);
    }
}
