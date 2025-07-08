package com.example.firstproject.repository;

import com.example.firstproject.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> { //JpaRepository는 CRUD, 페이지 처리, 정렬 기능 갖음
    // 네이티브 쿼리 메소드는 SQL쿼리를 리파지터리 메소드로 실행할 수 있게 해줌
    @Query(value = "SELECT * FROM pizza", nativeQuery = true)
    List<Pizza> findPizza();
}
