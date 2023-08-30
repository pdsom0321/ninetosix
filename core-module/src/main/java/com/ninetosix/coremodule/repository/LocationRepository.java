package com.ninetosix.coremodule.repository;


import com.ninetosix.coremodule.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findById(Long id);
}
