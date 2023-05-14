package com.autodiler.controller;

import com.autodiler.controller.main.Attributes;
import com.autodiler.model.enums.BodyType;
import com.autodiler.model.enums.Fuel;
import com.autodiler.model.enums.Transmission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/map")
    public String map(Model model) {
        AddAttributes(model);
        return "map";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam BodyType bodyType, @RequestParam Fuel fuel, @RequestParam Transmission transmission) {
        AddAttributesIndexSearch(model, bodyType, fuel, transmission);
        return "index";
    }
}
