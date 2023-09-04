package com.ninetosix.coremodule.repository;


import com.ninetosix.coremodule.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LocationRepository extends JpaRepository<Location, Long> {
}
