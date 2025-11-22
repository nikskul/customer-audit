package ru.nikskul.customer.audit.integration.customer.usecase.create;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.repository._itf.CustomerRepository;
import ru.nikskul.customer.audit.customer.usecase.create._itf.CustomerCreateUseCase;
import ru.nikskul.customer.audit.integration.BaseIntegrationTest;
import ru.nikskul.customer.audit.operation.result.impl.OperationResult;


public class CustomerCreateUseCaseIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CustomerCreateUseCase createUseCase;

    @Autowired
    private CustomerRepository repository;

    @Test
    void testCreate() {
        CustomerDto dto = new CustomerDto(
            null,
            "Иванов Иван Иванович",
            "+79008001020",
            0.00
        );

        var result = createUseCase.exec(dto);
        assertEquals(OperationResult.Status.OK, result.status(), result.message());

        var actual = repository.findById(result.id()).orElseThrow();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actual.getId()).isEqualTo(result.id());
            softAssertions.assertThat(actual.getFullName()).isEqualTo(dto.fullName());
            softAssertions.assertThat(actual.getPhone()).isEqualTo(dto.phone());
            softAssertions.assertThat(actual.getSpent()).isEqualByComparingTo(dto.spent());

            softAssertions.assertThat(actual.getVersion()).isEqualTo(0);
            softAssertions.assertThat(actual.getCreatedAt()).isNotNull();
            softAssertions.assertThat(actual.getUpdatedAt()).isNotNull();
        });
    }
}
