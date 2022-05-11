package com.example.SciPaperService.repository;

import com.example.SciPaperService.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Integer> {
}
