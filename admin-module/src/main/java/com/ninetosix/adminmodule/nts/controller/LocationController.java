package com.ninetosix.adminmodule.nts.controller;

import com.ninetosix.adminmodule.nts.dto.location.LocationReqDTO;
import com.ninetosix.adminmodule.nts.service.LocationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @ApiOperation(value = "위치 등록")
    @PostMapping("location")
    public ResponseEntity<Void> createLocation(@RequestBody LocationReqDTO reqDTO) {
        locationService.createLocation(reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "위치 수정")
    @PutMapping("location/{id}")
    public ResponseEntity<Void> modifyLocation(@PathVariable long id, @RequestBody LocationReqDTO reqDTO) {
        locationService.modifyLocation(id, reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "위치 삭제")
    @DeleteMapping("location/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok().build();
    }

}
