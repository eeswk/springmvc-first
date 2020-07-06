package ee.swan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    Cart cart;

    @RequestMapping(method = RequestMethod.GET)
    public String registForm() {
        return "/order/orderList";
    }
}
