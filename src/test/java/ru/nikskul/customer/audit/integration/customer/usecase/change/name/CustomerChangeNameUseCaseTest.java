package ru.nikskul.customer.audit.integration.customer.usecase.change.name;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.change.name._itf.CustomerChangeNameUseCase;
import ru.nikskul.customer.audit.integration.customer.usecase._itf.BaseIntegrationTest;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;

import java.time.Instant;
import java.time.ZoneOffset;


class CustomerChangeNameUseCaseTest extends BaseIntegrationTest {

    @Autowired
    private CustomerChangeNameUseCase changeNameUseCase;
    @Autowired
    private CustomerRepository repository;

    @Test
    @Sql(
        scripts = "classpath:sql/customer/change-name-test.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    void test() {
        var expected = new CustomerDto(
            1L,
            "Ivan",
            "000",
            0.00
        );

        var result = changeNameUseCase.exec(expected);
        assertEquals(
            OperationResult.Status.OK,
            result.status(),
            result.message()
        );

        var actual = repository.findById(expected.id()).orElseThrow();
        SoftAssertions.assertSoftly(sa -> {
            // Expecting than only name was changed
            sa.assertThat(actual.getId()).isEqualTo(expected.id());
            sa.assertThat(actual.getFullName()).isEqualTo(expected.fullName());
            sa.assertThat(actual.getPhone()).isEqualTo("+79008001020");
            sa.assertThat(actual.getSpent()).isEqualByComparingTo("15.09");

            sa.assertThat(actual.getVersion()).isEqualTo(1L);
            sa.assertThat(actual.getCreatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo("2020-01-01");
            sa.assertThat(actual.getUpdatedAt().atOffset(ZoneOffset.UTC).toLocalDate())
                .isEqualTo(Instant.now().atOffset(ZoneOffset.UTC).toLocalDate());
        });
    }
}