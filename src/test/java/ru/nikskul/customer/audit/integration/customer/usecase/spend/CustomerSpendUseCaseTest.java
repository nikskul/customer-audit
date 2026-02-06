package ru.nikskul.customer.audit.integration.customer.usecase.spend;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.spent.history.repository.CustomerSpentHistoryRepository;
import ru.nikskul.customer.audit.customer.spent.history.usecase.spend._itf.CustomerSpendUseCase;
import ru.nikskul.customer.audit.integration.customer.usecase._itf.BaseIntegrationTest;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;
import ru.nikskul.customer.audit.spend.request.impl.SpendRequest;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerSpendUseCaseTest extends BaseIntegrationTest {

    @Autowired
    private CustomerSpendUseCase spendUseCase;
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerSpentHistoryRepository historyRepository;

    @Test
    @Sql(
        scripts = "classpath:sql/customer/add-spend.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    void test() {
        var request = new SpendRequest(
            1L,
            150.00,
            LocalDateTime.of(2025, Month.JANUARY, 25, 16, 0)
        );

        var result = spendUseCase.spend(request);
        assertEquals(
            OperationResult.Status.OK,
            result.getStatus(),
            result.getMessage()
        );

        var spendList = historyRepository.findAll();
        assertEquals(2, spendList.size());
        SoftAssertions.assertSoftly(sa -> {
            sa.assertThat(spendList).satisfiesExactlyInAnyOrder(
                item -> {
                    assertThat(item.getId()).isEqualTo(1L);
                    assertThat(item.getAmount()).isEqualByComparingTo("15.09");
                },
                item -> {
                    assertThat(item.getId()).isEqualTo(2L);
                    assertThat(item.getAmount()).isEqualByComparingTo("150.00");
                }
            );
        });

        var actual = repository.findById(request.customerId()).orElseThrow();
        SoftAssertions.assertSoftly(sa -> {
            // Expecting than only spent was changed
            sa.assertThat(actual.getId()).isEqualTo(request.customerId());
            sa.assertThat(actual.getFullName()).isEqualTo("Ivanov Ivan");
            sa.assertThat(actual.getPhone()).isEqualTo("+79008001020");
            sa.assertThat(actual.getSpent()).isEqualByComparingTo("165.09");

            sa.assertThat(actual.getVersion()).isEqualTo(0L);
            sa.assertThat(actual.getCreatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo("2020-01-01");
            sa.assertThat(actual.getUpdatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo("2020-01-01");
        });
    }
}