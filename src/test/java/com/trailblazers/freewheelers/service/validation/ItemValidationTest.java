package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.model.Item;
import com.trailblazers.freewheelers.model.ItemType;
import com.trailblazers.freewheelers.service.validation.ItemValidation;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemValidationTest {

    private ItemValidation validator;

    @Before
    public void setUp() throws Exception {
        validator = new ItemValidation();
    }

    private Item someItem() {
        return new Item()
                .setName("Some Item")
                .setDescription("... with a description")
                .setType(ItemType.ACCESSORIES)
                .setPrice(valueOf(0.49))
                .setQuantity((long) 99);
    }

    @Test
    public void shouldHaveZeroValidationErrorsForAValidItem() {
        assertThat(validate(someItem()).size(), is(0));
    }

    @Test
    public void shouldErrorWhenThereIsNoName() {
        assertFieldError(someItem().setName(""), "name", "Please enter Item Name");
    }

    @Test
    public void shouldErrorWhenThereIsNoPrice() {
        assertFieldError(someItem().setPrice(null), "price", "Please enter Item Price");
    }

    @Test
    public void shouldErrorWhenThereIsNoQuantity() {
        assertFieldError(someItem().setQuantity(null), "quantity", "Please enter Item Quantity");
    }

    @Test
    public void shouldErrorWhenThereIsNoType() {
        assertFieldError(someItem().setType(null), "type", "Please enter Item Type");
    }

    @Test
    public void shouldErrorWhenThereIsNoDescription() {
        assertFieldError(someItem().setDescription(""), "description", "Please enter Item Description");
    }

    @Test
    public void shouldHaveAPrice() {
        assertFieldError(someItem().setPrice(null), "price", "Please enter Item Price");
    }

    @Test
    public void shouldErrorForPriceLargerThan99999() throws Exception {
        Item ridiculouslyExpensive = someItem().setPrice(valueOf(100000.00));
        assertFieldError(ridiculouslyExpensive, "price", "must be less than or equal to 99999");
    }

    private Map<String, String> validate(Item item) {
        return validator.validate(item);
    }

    private void assertFieldError(Item item, String field, String expectedMessage) {
        String error = validate(item).get(field);
        assertThat(error, is(expectedMessage));
    }
}
