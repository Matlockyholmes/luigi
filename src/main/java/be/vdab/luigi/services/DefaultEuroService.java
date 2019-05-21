package be.vdab.luigi.services;

import be.vdab.luigi.exception.KoersClientException;
import be.vdab.luigi.restclients.KoersClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Service
public class DefaultEuroService implements EuroService {
    private final KoersClient[] koersClients;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultEuroService(KoersClient[] koersClients) {
        this.koersClients = koersClients;
    }

    @Override
    public BigDecimal naarDollar(BigDecimal euro){
        try {
            final BigDecimal[] result = new BigDecimal[1];
            Arrays.stream(koersClients).forEach(koersClient -> result[0] = euro.multiply(koersClient.getDollarKoers().setScale(2, RoundingMode.HALF_UP)));
            return result[0];
        } catch (KoersClientException ex) {
            String fout = "Kan dollar koers van geen enkele bron niet lezen";
            logger.error(fout, ex);
        }
        return null;
    }
}
