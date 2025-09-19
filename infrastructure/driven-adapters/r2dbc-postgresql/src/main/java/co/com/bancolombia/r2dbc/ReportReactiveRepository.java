package co.com.bancolombia.r2dbc;
import co.com.bancolombia.r2dbc.entity.ApprovedReportEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportReactiveRepository extends ReactiveCrudRepository<ApprovedReportEntity, Long>, ReactiveQueryByExampleExecutor<ApprovedReportEntity> {

}