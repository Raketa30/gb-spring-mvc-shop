package ru.geekbrains.shop.buisness.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.shop.buisness.domain.dto.UserDto;
import ru.geekbrains.shop.buisness.domain.search.UserSearchCondition;
import ru.geekbrains.shop.buisness.service.UserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String getUserList(UserSearchCondition userSearchCondition, Model model) {
        Page<UserDto> page = userService.findAllPaginated(userSearchCondition);

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

        return "user/list";
    }

}
