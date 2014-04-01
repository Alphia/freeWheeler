package com.trailblazers.freewheelers.service.validation;

import com.trailblazers.freewheelers.model.Item;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class ItemValidation {

    public Map<String, String> validate(Item item) {
        Map<String, String> errors = new HashMap<String, String>();

        validateName(item, errors);
        validateQuantity(item, errors);
        validateType(item, errors);
        validateDescription(item, errors);
        validatePrice(item, errors);

        return errors;
    }

    private void validateName(Item item, Map<String, String> errors) {
        if (item.getName().isEmpty()) {
            errors.put("name", "Please enter Item Name");
        }
    }

    private void validateQuantity(Item item, Map<String, String> errors) {
        if (item.getQuantity() == null) {
            errors.put("quantity", "Please enter Item Quantity");
        }
    }

    private void validateType(Item item, Map<String, String> errors) {
        if (item.getType() == null) {
            errors.put("type", "Please enter Item Type");
        }
    }

    private void validateDescription(Item item, Map<String, String> errors) {
        if (item.getDescription().isEmpty()) {
            errors.put("description", "Please enter Item Description");
        }
    }

    private void validatePrice(Item item, Map<String, String> errors) {
        if (item.getPrice() == null) {
            errors.put("price", "Please enter Item Price");
        }

        if (item.getPrice() == null) {
            errors.put("price", "Please enter Item Price");
        }

        if((item.getPrice() != null) && item.getPrice().compareTo(BigDecimal.valueOf(100000.00)) != -1) {
            errors.put("price", "must be less than or equal to 99999");
        }
    }
}
