package com.re.bt4.controller;

import com.re.bt4.model.Dish;
import com.re.bt4.service.AdminDishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bai4")
public class AdminDishController {
    private final AdminDishService adminDishService;

    public AdminDishController(AdminDishService adminDishService) {
        this.adminDishService = adminDishService;
    }

    @GetMapping({"", "/", "/dishes"})
    public String listDish(Model model) {
        model.addAttribute("dishes", adminDishService.findAll());
        return "dish-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        return adminDishService.findById(id)
                .map(dish -> {
                    model.addAttribute("dish", dish);
                    return "edit-dish";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Khong tim thay mon an yeu cau!");
                    return "redirect:/bai4/dishes";
                });
    }

    @PostMapping("/edit/{id}")
    public String updateDish(@PathVariable int id,
                             @ModelAttribute("dish") Dish dish,
                             RedirectAttributes redirectAttributes) {
        if (adminDishService.findById(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Khong tim thay mon an yeu cau!");
            return "redirect:/bai4/dishes";
        }

        adminDishService.updateDish(id, dish);
        redirectAttributes.addFlashAttribute("successMessage", "Cap nhat mon an thanh cong!");
        return "redirect:/bai4/dishes";
    }
}
