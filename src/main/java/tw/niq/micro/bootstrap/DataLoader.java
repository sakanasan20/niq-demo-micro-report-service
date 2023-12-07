package tw.niq.micro.bootstrap;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tw.niq.micro.domain.Report;
import tw.niq.micro.service.ReportRepository;

@Profile("dev")
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

	private final ReportRepository reportRepository;
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		reloadTestReports();
	}
	
	@Transactional
	private void reloadTestReports() {
		
		if (reportRepository.count() == 0) {
			reportRepository.saveAll(
					Arrays.asList(
							Report.builder().name("Test Report 1").userId("d0f40e37-192d-4ddd-ad26-ad466d5becc7").build(), 
							Report.builder().name("Test Report 2").userId("d0f40e37-192d-4ddd-ad26-ad466d5becc7").build(), 
							Report.builder().name("Test Report 3").userId("070a13a9-fbfd-47c3-b155-5dae5626e7ca").build()
			));
		}
		
	}

}
