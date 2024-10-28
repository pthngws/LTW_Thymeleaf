package com.pthngws.controller;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.pthngws.entity.Category;
import com.pthngws.model.CategoryModel;
import com.pthngws.service.ICategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // Endpoint hiển thị danh sách các danh mục
    @GetMapping("")
    public String listCategories(Model model) {
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory", listCategory);
        return "category-list";
    }

    // Hiển thị form thêm danh mục mới
    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new CategoryModel());
        return "category-add";
    }

    // Lưu danh mục mới hoặc cập nhật danh mục hiện có
    @PostMapping("/add")
    public String saveCategory(@Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category-add";
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryModel, category);
        categoryService.save(category);

        model.addAttribute("message", "Category saved successfully!");
        return "redirect:/category";
    }

    // Hiển thị form chỉnh sửa danh mục
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            CategoryModel categoryModel = new CategoryModel();
            BeanUtils.copyProperties(optionalCategory.get(), categoryModel);
            model.addAttribute("category", categoryModel);
            return "category-edit";
        }

        model.addAttribute("message", "Category not found!");
        return "redirect:/category";
    }

    // Cập nhật danh mục sau khi chỉnh sửa
    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category-edit";
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryModel, category);
        categoryService.save(category);

        model.addAttribute("message", "Category updated successfully!");
        return "redirect:/category";
    }

    // Xóa danh mục theo ID
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        categoryService.deleteById(id);
        model.addAttribute("message", "Category deleted successfully!");
        return "redirect:/category";
    }

    // Tìm kiếm danh mục theo tên
    @GetMapping("/search")
    public String searchCategory(@RequestParam("keyword") String keyword, Model model) {
        List<Category> listCategory;
        if (StringUtils.hasText(keyword)) {
            listCategory = categoryService.findByNameContaining(keyword);
        } else {
            listCategory = categoryService.findAll();
        }
        model.addAttribute("listCategory", listCategory);
        return "category-list";
    }

    // Tìm kiếm danh mục với phân trang
    @GetMapping("/searchpaginated")
    public String searchPaginated(@RequestParam("keyword") Optional<String> keyword,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size, Model model) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
        Page<Category> categoryPage = keyword
                .map(kw -> categoryService.findByNameContaining(kw, pageable))
                .orElseGet(() -> categoryService.findAll(pageable));

        model.addAttribute("categoryPage", categoryPage);

        int totalPages = categoryPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "searchpaging";
    }
}
