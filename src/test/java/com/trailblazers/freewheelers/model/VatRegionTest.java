package com.trailblazers.freewheelers.model;

import com.trailblazers.freewheelers.model.VatRegion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VatRegionTest {

    private static final long USA_ID = 2;
    private static final long FRANCE_ID = 4;
    private final long UK_ID = 1;

    @Test
    public void shouldUkBeConsideredUkRegion() {
        VatRegion vatRegion = VatRegion.findRegion(UK_ID);
        assertEquals(vatRegion, VatRegion.UK);
    }

    @Test
    public void shouldUsaBeConsideredOutOfEuropeRegion() {
        VatRegion vatRegion = VatRegion.findRegion(USA_ID);
        assertEquals(vatRegion, VatRegion.OUTSIDE_EU);
    }

    @Test
    public void shouldFranceBeConsideredEuropeExceptUkRegion() {
        VatRegion vatRegion = VatRegion.findRegion(FRANCE_ID);
        assertEquals(vatRegion, VatRegion.EU_EXCEPT_UK);
    }
}
