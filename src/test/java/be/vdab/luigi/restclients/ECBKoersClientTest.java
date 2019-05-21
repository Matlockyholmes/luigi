package be.vdab.luigi.restclients;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ECBKoersClientTest {
    private ECBKoersClient client;

    @Before
    public void before(){
        client = new ECBKoersClient();
    }
    @Test
    public void deKoersMoetPositiefZijn(){
        assertThat(client.getDollarKoers()).isPositive();
    }

}