package ca.gbc.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "${inventory.service.url}") // Make sure this property is set in application.yml or application.properties
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value="/api/inventory")
    boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);
}
