package be.vdab.luigi.restclients;

import be.vdab.luigi.exception.KoersClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Scanner;

@Order(1)
public class FixerKoersClient implements KoersClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final URL url;

    FixerKoersClient(URL url) {
        this.url = url;
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
