package com.ninetosix.apimodule.nts.dto.attend;

import java.util.List;

public record ExportDTO(String memberName, List<AttendDTO> attends) {}
