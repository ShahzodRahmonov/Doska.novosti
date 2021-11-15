package com.company.repository;

import com.company.entity.News;
import com.company.enums.NewsStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News,Long> {
    @Query(value = "select * from news n where n.user_id=:id and n.status=:status", nativeQuery = true)
Page<News> getListNewsByUserId(@Param("id") Long id, @Param("status") String status, Pageable pageable);

    @Query(value = "select * from news n where n.status=:status", nativeQuery = true)
    Page<News> getListApprovedNews(@Param("status") String status, Pageable pageable);
}
