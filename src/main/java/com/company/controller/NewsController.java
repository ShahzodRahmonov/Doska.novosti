package com.company.controller;

import com.company.dto.NewsCreateDTO;
import com.company.dto.NewsDTO;
import com.company.dto.NewsUpdateDTO;
import com.company.dto.UserDetails;
import com.company.service.NewsService;
import com.company.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@Api(tags = "News")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @ApiOperation("add news")
    @PostMapping("/auth/addnews")
    public ResponseEntity<?> addNews(@Valid @RequestBody NewsCreateDTO dto, HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.addNews(userDetails.getId(),dto));
    }

    @ApiOperation("approved news by id")
    @PutMapping("/auth/approvedNewsById/{id}")
    public ResponseEntity<?> approvedNewsById(@PathVariable("id") Long newsId, HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.approvedNewsById(userDetails.getRole(),newsId));
    }

    @ApiOperation("blocked news by id")
    @PutMapping("/auth/blockedNewsById/{id}")
    public ResponseEntity<?> blockedNewsById(@PathVariable("id") Long newsId, HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.blockNewsById(userDetails.getRole(),newsId));
    }

    @ApiOperation("get news by id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(newsService.getById(id));
    }

    @ApiOperation("get approved news list by user id")
    @GetMapping("/auth/getListApprovedNewsByUserId")
    public ResponseEntity<?> getListApprovedNewsByUserId(@Param("page") Integer page, @Param("size") Integer size, HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.getListApprovedNewsByUserId(userDetails.getId(),page,size));
    }

    @ApiOperation("get approved news list")
    @GetMapping("/getListApprovedNews")
    public ResponseEntity<?> getListApprovedNews(@Param("page") Integer page, @Param("size") Integer size){
        return ResponseEntity.ok(newsService.getListApprovedNews(page,size));
    }

    @ApiOperation("get not approved news list")
    @GetMapping("/auth/getListNotApprovedNews")
    public ResponseEntity<?> getListNotApprovedNews(@Param("page") Integer page, @Param("size") Integer size,HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.getListNotApprovedNews(userDetails.getRole(),page,size));
    }

    @ApiOperation("update news")
    @PutMapping("/auth/update")
    public ResponseEntity update(@RequestBody NewsUpdateDTO dto, HttpServletRequest request){
        UserDetails userDetails = TokenUtil.getCurrentUser(request);
        return ResponseEntity.ok(newsService.updateNews(userDetails.getId(),dto));
    }

}
