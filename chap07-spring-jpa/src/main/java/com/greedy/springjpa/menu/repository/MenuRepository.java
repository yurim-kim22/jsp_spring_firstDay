package com.greedy.springjpa.menu.repository;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.greedy.springjpa.menu.entity.Category;
import com.greedy.springjpa.menu.entity.Menu;

@Repository
public class MenuRepository {
	
	//7번 메뉴 보기
	public Menu findMenuByCode(EntityManager entityManager, int menuCode) {
		
		return entityManager.find(Menu.class, menuCode);
	}
	
	//메뉴 전체 목록 조회
	public List<Menu> findAllMenu(EntityManager entityManager) {
		
		String jpql = "SELECT m FROM Menu as m ORDER BY m.menuCode ASC";
		
		return entityManager.createQuery(jpql, Menu.class).getResultList();
	}
	
	//카테고리목록조회
	public List<Category> findAllCategory(EntityManager entityManager) {
		
		String jpql = "SELECT c FROM Category as c ORDER BY c.categoryCode ASC";
		
		return entityManager.createQuery(jpql, Category.class).getResultList();
	}
	
	//메뉴 인서트
	public void registNewMenu(EntityManager entityManager, Menu menu) {
		
		entityManager.persist(menu);
	}
	
	//메뉴 코드를 기준으로 메뉴 수정하기
	public void modifyMenu(EntityManager entityManager, Menu menu) {
		
		/* 전달 받은 메뉴 정보를 통해 해당 엔티티를 먼저 조회한다. */
		Menu selectedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		/* 조회 된 메뉴 객체를 수정한다. */
		selectedMenu.setMenuName(menu.getMenuName());
		
	}

	public void stateMenu(EntityManager entityManager, Menu menu) {
		
		/* 전달 받은 메뉴 정보를 통해 해당 엔티티를 먼저 조회한다. */
		Menu selectedMenu = entityManager.find(Menu.class, menu.getMenuCode());
		/* 조회 된 메뉴 객체를 판매 상태를 수정한다. */
		selectedMenu.setOrderableStatus(menu.getOrderableStatus());
	}

	public void deleteMenu(EntityManager entityManager, Menu menu) {
		
		/* 전달 받은 메뉴 정보를 통해 해당 엔티티를 먼저 조회한다. */
		Menu deleteMenu = entityManager.find(Menu.class, menu.getMenuCode());
		
		/* 조회 된 메뉴 객체를 삭제한다. */
		entityManager.remove(deleteMenu);
	}

	public List<Menu> searchMenu(EntityManager entityManager,String keyword) {
	      return entityManager.createQuery("SELECT m FROM Menu m WHERE m.menuName LIKE :keyword", Menu.class)
	            .setParameter("keyword", "%" + keyword + "%")
	            .getResultList();
	 }
}












