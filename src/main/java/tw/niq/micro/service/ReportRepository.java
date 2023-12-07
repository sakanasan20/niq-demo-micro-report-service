package tw.niq.micro.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.niq.micro.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	List<Report> findAllByUserId(String userId);

}
