package com.simpleboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simpleboard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}
