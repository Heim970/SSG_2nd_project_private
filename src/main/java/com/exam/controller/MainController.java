package com.exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.dto.GoodsDTO;
import com.exam.service.GoodsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private final GoodsService goodsService;

    public MainController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "All") String gCategory,
                       HttpServletRequest request) {
    	
    	List<GoodsDTO> goodsList;
    	
    	if (gCategory.equals("All")) {
    		// RequestParam이 없는 경우 = 전체 카테고리 조회하기
    		goodsList = goodsService.getAllStock();
    	} else {
    		// RequestParam이 있는 경우 = 해당 카테고리 조회
    		goodsList = goodsService.goodsList(gCategory);
    	}
    	
        // request에 allStock 속성 설정
        request.setAttribute("goodsList", goodsList);

        return "main"; // main.jsp로 반환
    }
}
