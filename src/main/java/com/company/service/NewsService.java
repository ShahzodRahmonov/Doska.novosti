package com.company.service;

import com.company.dto.NewsCreateDTO;
import com.company.dto.NewsDTO;
import com.company.dto.NewsUpdateDTO;
import com.company.entity.News;
import com.company.entity.Users;
import com.company.enums.NewsStatus;
import com.company.enums.UserRole;
import com.company.enums.UserStatus;
import com.company.exceptions.NewsNotFoundException;
import com.company.exceptions.UserNotFoundException;
import com.company.exceptions.UserRoleException;
import com.company.repository.NewsRepository;
import com.company.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public NewsDTO addNews(Long id, NewsCreateDTO dto){

        Users users = getUser(id);
        News news = new News();
        news.setUsers(users);
        news.setTitle(dto.getTitle());
        news.setText(dto.getText());
        news.setStatus(NewsStatus.NOTACTIVE);
        newsRepository.save(news);
        return toDTO(news);
    }


    public NewsDTO getById(Long id){
        News news = get(id);
        return toDTO(news);
    }


    public Boolean approvedNewsById(UserRole userRole, Long newsId){
        if (!userRole.equals(UserRole.ADMIN)){
            throw new UserRoleException("Sizda yangiliklarni tasdiqlash huquqi mavjud emas!!!");
        }
        News news = get(newsId);
        news.setStatus(NewsStatus.ACTIVE);
        newsRepository.save(news);
        return true;
    }


    public Boolean blockNewsById(UserRole userRole, Long newsId){
        if (!userRole.equals(UserRole.ADMIN)){
            throw new UserRoleException("Sizda yangiliklarni tasdiqlash huquqi mavjud emas!!!");
        }
        News news = get(newsId);
        news.setStatus(NewsStatus.BLOCK);
        newsRepository.save(news);
        return true;
    }


    public Page<NewsDTO> getListApprovedNewsByUserId(Long id, Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<News> paging = newsRepository.getListNewsByUserId(id,NewsStatus.ACTIVE.toString(),pageable);
        List<NewsDTO> list = paging.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(list,pageable,paging.getTotalElements());
    }


    public Page<NewsDTO> getListApprovedNews(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<News> paging = newsRepository.getListApprovedNews(NewsStatus.ACTIVE.toString(),pageable);
        List<NewsDTO> list = paging.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(list,pageable,paging.getTotalElements());
    }


    public Page<NewsDTO> getListNotApprovedNews(UserRole role, Integer page, Integer size){
        if (!role.equals(UserRole.ADMIN)) {
            throw new UserRoleException("Method not allowed!!!");
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<News> paging = newsRepository.getListApprovedNews(NewsStatus.NOTACTIVE.toString(),pageable);
        List<NewsDTO> list = paging.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(list,pageable,paging.getTotalElements());
    }


    public NewsDTO updateNews(Long userId, NewsUpdateDTO dto){
        News news = get(dto.getNews_id());
        if (userId!=news.getUsers().getId()) {
            throw new UserRoleException("Method not allowed!!!");
        }
        news.setTitle(dto.getTitle());
        news.setText(dto.getText());
        news.setStatus(NewsStatus.NOTACTIVE);
        newsRepository.save(news);
        return toDTO(news);
    }






    public NewsDTO toDTO(News news){
        NewsDTO dto = new NewsDTO();
        dto.setId(news.getId());
        dto.setUser_id(news.getUsers().getId());
        dto.setUser(news.getUsers());
        dto.setTitle(news.getTitle());
        dto.setText(news.getText());
        dto.setStatus(news.getStatus());
        dto.setCreatedDate(news.getCreatedDate());
        return dto;
    }


    public News get(Long id){
        Optional<News> optional = newsRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NewsNotFoundException("News not found!!!");
        }
        return optional.get();
    }


    public Users getUser(Long id){
        Optional<Users> optional = usersRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("User not found!!!");
        }
        return optional.get();
    }
}
