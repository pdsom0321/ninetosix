package com.ninetosix.apimodule.nts.controller;

import com.ninetosix.apimodule.core.aspect.UserId;
import com.ninetosix.apimodule.nts.dto.attend.*;
import com.ninetosix.apimodule.nts.service.AttendService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AttendController {
    private final AttendService attendService;

    @ApiOperation(value = "출근", notes = "attendCode, locationId -> insert Attend 실행")
    @PostMapping("attend/on")
    public ResponseEntity<Void> onWork(@RequestBody OnWorkReqDTO reqDTO) {
        attendService.onWork(reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "출근", notes = "요청: locationId / 실행: update inTime, locationId")
    @PutMapping("attend/on")
    public ResponseEntity<Void> onWorkDuringDayOff(@RequestBody OnWorkDuringDayOffReqDTO reqDTO) {
        attendService.onWorkDuringDayOff(reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "퇴근", notes = "실행: update outTime")
    @PostMapping("attend/off")
    public ResponseEntity<Void> offWork() {
        attendService.offWork();
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "휴가 및 그외 신청", notes = "attendCode, fromDate, toDate -> insert Attend 실행")
    @PostMapping("attend/{attendCode}")
    public ResponseEntity<Void> dayOff(@PathVariable String attendCode, @RequestBody AttendCodeReqDTO reqDTO) {
        attendService.dayOff(attendCode, reqDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "휴가 신청 철회")
    @PostMapping("attend/cancel-dayoff/{day}")
    public ResponseEntity<Void> cancelDayOff(@PathVariable String day) {
        attendService.cancelDayOff(day);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "금일 출근 정보")
    @UserId
    @GetMapping("attend")
    public ResponseEntity<AttendResDTO> attendInfo(HttpServletRequest request) {
        Long memberId = (Long) request.getAttribute("memberId");
        return ResponseEntity.ok(attendService.attendInfo(memberId));
    }
    /*public ResponseEntity<List<AttendResDTO>> yesterdayAndTodayAttendanceList(HttpServletRequest request){
        Long memberId = (Long) request.getAttribute("memberId");
        return ResponseEntity.ok(attendService.yesterdayAndTodayAttendanceList(memberId));
    }*/

    @ApiOperation(value = "한달 출근 목록")
    @UserId
    @GetMapping("attend/{month}")
    public ResponseEntity<List<MonthlyResDTO>> monthlyAttendanceList(HttpServletRequest request, @PathVariable String month) {
        Long memberId = (Long) request.getAttribute("memberId");
        return ResponseEntity.ok(attendService.monthlyAttendanceList(memberId, month));
    }

    @ApiOperation(value = "엑셀 출력 (팀 단위)")
    @GetMapping("attend/export/{teamId}/{year}/{month}")
    public ModelAndView exportAttendance(@PathVariable long teamId, @PathVariable int year, @PathVariable int month) {
        ModelAndView mv = new ModelAndView("attendance");
        mv.addObject("dates", attendService.getDayOfMonth(year, month));
        mv.addObject("attends", attendService.getAttends(teamId, year, month));
        return mv;
    }

    @ApiOperation(value = "출근부 엑셀 다운로드")
    @GetMapping("attend/excel/{teamId}/{year}/{month}")
    public void downloadExcel(HttpServletResponse response, @PathVariable long teamId, @PathVariable int year, @PathVariable int month) {
        attendService.downloadExcel(response, teamId, year, month);
    }
}
