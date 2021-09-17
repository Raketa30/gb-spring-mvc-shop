package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
