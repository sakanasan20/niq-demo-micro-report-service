package tw.niq.micro.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReportDto {
	
	private Long id;
	
	private Long version;
	
	private Timestamp createdDate;
	
	private Timestamp lastModifiedDate;
	
	private String name;

}
