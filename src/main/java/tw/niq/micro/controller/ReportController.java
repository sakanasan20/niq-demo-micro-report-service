package tw.niq.micro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.niq.micro.mapper.CycleAvoidingMappingContext;
import tw.niq.micro.mapper.ReportMapper;
import tw.niq.micro.model.ReportModel;
import tw.niq.micro.service.ReportService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
	
	private final ReportService reportService;
	private final ReportMapper reportMapper;
	private final CycleAvoidingMappingContext context;

	@PreAuthorize("hasRole('ADMIN') and hasAuthority('user.read')")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<ReportModel> getReports(
			@RequestParam(value = "userId", required = false) String userId, 
			@RequestHeader("Authorization") String authorization) {

		return reportService.getReportsByUserId(userId).stream()
				.map((reportDto) -> reportMapper.toReportModel(reportDto, context))
				.collect(Collectors.toList());
	}
	
}
