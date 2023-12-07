package tw.niq.micro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tw.niq.micro.dto.ReportDto;
import tw.niq.micro.mapper.CycleAvoidingMappingContext;
import tw.niq.micro.mapper.ReportMapper;

@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {
	
	private final ReportRepository reportRepository;
	private final ReportMapper reportMapper;
	private final CycleAvoidingMappingContext context;

	@Override
	public List<ReportDto> getReportsByUserId(String userId) {
		return reportRepository.findAllByUserId(userId).stream()
				.map(report -> reportMapper.toReportDto(report, context))
				.collect(Collectors.toList());
	}

}
