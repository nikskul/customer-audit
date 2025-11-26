package ru.nikskul.customer.audit.integration.customer.usecase.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ru.nikskul.customer.audit.customer.dto.impl.CustomerDto;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.customer.usecase.search._itf.CustomerSearchUseCase;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.List;


class CustomerSearchUseCaseTest
    extends BaseCustomerSearchUseCaseIntegrationTest {

    @Autowired
    private CustomerSearchUseCase searchUseCase;

    @ParameterizedTest
    @MethodSource("factory")
    @Sql(
        scripts = "classpath:sql/customer/search-test.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(
        scripts = "classpath:sql/clear-all.sql",
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    void search(
        List<Long> expectedIdList,
        SearchParams<CustomerFilter> params
    ) {
        List<CustomerDto> actual = searchUseCase.search(params);

        var actualIdsList = actual.stream().map(CustomerDto::id).toList();

        if (params.sorting().isEmpty()) {
            Assertions.assertThat(actualIdsList)
                .containsExactlyInAnyOrderElementsOf(expectedIdList);
        } else {
            Assertions.assertThat(actualIdsList)
                .containsExactlyElementsOf(expectedIdList);
        }
    }
}