package tw.niq.micro.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import tw.niq.micro.domain.Report;
import tw.niq.micro.dto.ReportDto;
import tw.niq.micro.model.ReportModel;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReportMapper extends BaseMapper {

	ReportModel toReportModel(ReportDto reportDto, @Context CycleAvoidingMappingContext context);
	
	ReportDto toReportDto(ReportModel reportModel, @Context CycleAvoidingMappingContext context);
	
	ReportDto toReportDto(Report report, @Context CycleAvoidingMappingContext context);
	
	Report toReport(ReportDto reportDto, @Context CycleAvoidingMappingContext context);
	
}
