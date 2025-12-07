package ru.nikskul.customer.audit.integration.customer.usecase.spend;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.integration.customer.usecase._itf.BaseIntegrationTest;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerSpendUseCaseTest extends BaseIntegrationTest {

    @Autowired
    private CustomerSpendUseCase spendUseCase;
    @Autowired
    private CustomerRepository repository;

    @Test
    @Sql(
        scripts = "classpath:sql/customer/add-spend.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    void test() {
        var request = new SpendRequest(
            1L,
            150.00
        );

        var result = spendUseCase.spend(request);
        assertEquals(
            OperationResult.Status.OK,
            result.getStatus(),
            result.getMessage()
        );

        var actual = repository.findById(request.customerId()).orElseThrow();
        SoftAssertions.assertSoftly(sa -> {
            // Expecting than only name was changed
            sa.assertThat(actual.getId()).isEqualTo(request.customerId());
            sa.assertThat(actual.getFullName()).isEqualTo("Ivanov Ivan");
            sa.assertThat(actual.getPhone()).isEqualTo("+79008001020");
            sa.assertThat(actual.getSpent()).isEqualByComparingTo("165.09");

            sa.assertThat(actual.getVersion()).isEqualTo(1L);
            sa.assertThat(actual.getCreatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo("2020-01-01");
            sa.assertThat(actual.getUpdatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo(Instant.now().atOffset(ZoneOffset.UTC).toLocalDate());
        });
    }
}