package ee.swan.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    Cart cart;

    @RequestMapping(method = RequestMethod.GET)
    public String registForm(Model model) {
        Item item = new Item();
        model.addAttribute(item);
        return "/cart/cartRegisteForm";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@Validated Item item, BindingResult result) {
        List<Item> items = Arrays.asList(item);
        cart.setId("1");
        cart.setCartItems(items);
        return "/cart/cartList";
    }
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list() {
        return "/cart/cartList";
    }

}
