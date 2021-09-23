package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.geekbrains.shop.buisness.domain.RoleEntity;
import ru.geekbrains.shop.buisness.domain.dto.UserDto;
import ru.geekbrains.shop.buisness.domain.search.UserSearchCondition;
import ru.geekbrains.shop.buisness.service.RoleService;
import ru.geekbrains.shop.buisness.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String getUserList(UserSearchCondition userSearchCondition, Model model) {
        Page<UserDto> page = userService.findAllPaginated(userSearchCondition);
        List<RoleEntity> roles = roleService.findAll();

        int totalPages = page.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("pageNum", userSearchCondition.getPageNum());
        model.addAttribute("page", page);
        model.addAttribute("pageSize", userSearchCondition.getPageSize());
        model.addAttribute("roles", roles);

        return "user/list";
    }

    @GetMapping("/update/{id}")
    public String getUserUpdatePage(@PathVariable Long id, Model model) {
        UserDto userDto = userService.findUserDto(id);
        List<RoleEntity> roles = roleService.findAll();
        model.addAttribute("user", userDto);
        model.addAttribute("roles", roles);
        return "user/update";
    }

    @PostMapping("/update")
    public RedirectView updateUser(@ModelAttribute UserDto user) {
        userService.updateUser(user);
        return new RedirectView("/user/list");
    }

    @DeleteMapping("/delete")
    public RedirectView deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return new RedirectView("/user/list");
    }

}
