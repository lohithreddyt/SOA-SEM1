
package com.tourism.board.repository;

import com.tourism.board.entity.TourismEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourismRepository
        extends JpaRepository<TourismEntry, Long> {
}
