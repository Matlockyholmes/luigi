package be.vdab.luigi.services;

import be.vdab.luigi.restclients.KoersClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EuroServiceTest {
    @Mock
    private KoersClient koersClient;
    private EuroService euroService;

    @Before
    public void before(){
        when(koersClient.getDollarKoers()).thenReturn(BigDecimal.valueOf(1.5));
        euroService = new DefaultEuroService(new KoersClient[] {koersClient});
    }
    @Test
    public void naarDollar(){
        assertEquals(euroService.naarDollar(BigDecimal.valueOf(2)),BigDecimal.valueOf(3).setScale(2, RoundingMode.HALF_UP));
        verify(koersClient).getDollarKoers();
    }
}