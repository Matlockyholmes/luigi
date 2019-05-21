package be.vdab.luigi.services;

import be.vdab.luigi.exception.KoersClientException;
import be.vdab.luigi.restclients.KoersClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        for (KoersClient koersClient : koersClients) {
            try {
                return euro.multiply(koersClient.getDollarKoers()).setScale(2, RoundingMode.HALF_UP);
            } catch (KoersClientException ex) {
                logger.error("kan dollar koers niet lezen", ex);
            }
        }
        return null;
    }
}
