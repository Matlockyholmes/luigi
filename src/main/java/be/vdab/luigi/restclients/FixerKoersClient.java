package be.vdab.luigi.restclients;

import be.vdab.luigi.exception.KoersClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

@Component
@Order(1)
public class FixerKoersClient implements KoersClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final URL url;

    public FixerKoersClient() {
        try{
            url = new URL("http://data.fixer.io/api/latest?access_key=b2b79c3063c0422bf187b752992c0116&symbols=USD");
        } catch (MalformedURLException ex){
            String fout = "Fixer URL is verkeerd";
            throw new KoersClientException(fout);
        }
    }

    @Override
    public BigDecimal getDollarKoers() {
        try (Scanner scanner = new Scanner(url.openStream())){
            String lijn = scanner.nextLine();
            int beginPositieKoers = lijn.indexOf("USD") + 5;
            int accoladePositie = lijn.indexOf('}',beginPositieKoers);
            logger.info("koers gelezen via Fixer");
            return new BigDecimal(lijn.substring(beginPositieKoers, accoladePositie));
        } catch (IOException | NumberFormatException ex){
            String fout = "kan koers niet lezen via Fixer";
            logger.error(fout, ex);
            throw new KoersClientException(fout);
        }
    }
}