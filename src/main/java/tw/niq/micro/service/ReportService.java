package tw.niq.micro.service;

import java.util.List;

import tw.niq.micro.dto.ReportDto;

public interface ReportService {

	List<ReportDto> getReportsByUserId(String userId);

}
