package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.MAIN;

@Controller
@RequestMapping(MAIN)
public class MainController {

    @GetMapping
    public String getWelcome(Model model) {
        return "welcome";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin/main";
    }
}
