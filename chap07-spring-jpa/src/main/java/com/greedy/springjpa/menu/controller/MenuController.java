package com.greedy.springjpa.menu.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.springjpa.menu.dto.CategoryDTO;
import com.greedy.springjpa.menu.dto.MenuDTO;
import com.greedy.springjpa.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private MenuService menuService;
	
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping("/{menuCode}")
	public String findMenuByCode(@PathVariable int menuCode, Model model) {
		
		MenuDTO menu = menuService.findMenuByCode(menuCode);
//		System.out.println("menu = " + menu) ;
		
		model.addAttribute("menu", menu);
		
		return "menu/one";
	}
	
	@GetMapping("/list")
	public String findAllMenu(Model model) {
		
		List<MenuDTO> menuList = menuService.findAllMenu();
		
		model.addAttribute("menuList", menuList);
		
		return "menu/list";
	}
	
	//메뉴 등록
	@GetMapping("/regist")
	public void registPage() {}
	//카테고리 목록 조회 비동기처리
	@GetMapping(value="category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findCategoryList() {
		
		return menuService.findAllCategory();
	}
	//메뉴 등록
	@PostMapping("/regist")
	public String registMenu(@ModelAttribute MenuDTO newMenu) {
		
		menuService.registNewMenu(newMenu);
		
		return "redirect:/menu/list";
	}
	
	//메뉴 수정
	@GetMapping("/modify")
	public void modifyPage() {}
	
	@PostMapping("/modify")
	public String menuModify(@ModelAttribute MenuDTO menu) {
		
		menuService.modifyMenu(menu);
		
		return "redirect:/menu/" + menu.getMenuCode();
	}
	
	//판매 상태 수정하기
	@GetMapping("/state")
	public void statePage() {}
	
	@PostMapping("/state")
	public String menuState(@ModelAttribute MenuDTO menu) {
		
		menuService.stateMenu(menu);
		
		return "redirect:/menu/" + menu.getMenuCode();
	}
	
	//메뉴 삭제
	@GetMapping("/delete")
	public void deletePage() {}
	
	@PostMapping("/delete")
	public String deleteMenu(@ModelAttribute MenuDTO menu) {
		
		menuService.deleteMenu(menu);
		
		return "redirect:/menu/list";
	}
	
	//메뉴 검색
	@GetMapping("/search")
	public String searchPage() {
		
		return "menu/search";
	}
	
	@PostMapping("/search")
	public String searchMenu(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
		
		List<MenuDTO> menuList = menuService.searchMenu(keyword);
		model.addAttribute("menuList", menuList);

		
		return "menu/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
