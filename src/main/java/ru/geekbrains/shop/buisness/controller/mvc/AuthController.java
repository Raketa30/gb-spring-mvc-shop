package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.shop.buisness.controller.ControllerUtils;
import ru.geekbrains.shop.buisness.domain.UserEntity;
import ru.geekbrains.shop.buisness.service.UserService;

import javax.validation.Valid;
import java.util.Map;

import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.AUTH;
import static ru.geekbrains.shop.buisness.domain.constant.RequestNameConstant.REGISTRATION;

@Controller
@RequestMapping(AUTH)
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REGISTRATION)
    public String registration() {
        return AUTH + REGISTRATION;
    }

    @PostMapping(REGISTRATION)
    public String addUser(
            @RequestParam("repeatPassword") String passwordConfirm,
            @Valid UserEntity user,
            BindingResult bindingResult,
            Model model) {
        boolean isConfirm = StringUtils.hasText(passwordConfirm);

        if (!isConfirm) {
            model.addAttribute("repeatPassword", "Password confirmation cannot be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (!isConfirm || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrorMap(bindingResult);
            model.mergeAttributes(errors);
            return "auth/registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "auth/registration";
        }

        return "redirect:/";
    }
}
