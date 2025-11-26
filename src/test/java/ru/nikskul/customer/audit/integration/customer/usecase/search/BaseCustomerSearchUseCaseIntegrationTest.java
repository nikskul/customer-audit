package ru.nikskul.customer.audit.integration.customer.usecase.search;

import org.junit.jupiter.params.provider.Arguments;
import ru.nikskul.customer.audit.customer.filter.CustomerFilter;
import ru.nikskul.customer.audit.integration.customer.usecase._itf.BaseIntegrationTest;
import ru.nikskul.customer.audit.search.params.impl.SearchParams;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class BaseCustomerSearchUseCaseIntegrationTest
    extends BaseIntegrationTest {

    public static Stream<Arguments> factory() {
        return Stream.of(
            Arguments.of(
                List.of(1L, 2L, 3L, 4L, 5L),
                new SearchParams<>(
                    new CustomerFilter(null, null, null)
                )
            ),

            Arguments.of(
                List.of(1L),
                new SearchParams<>(
                    new CustomerFilter(null, null, null),
                    0,
                    1
                )
            ),

            Arguments.of(
                List.of(3L, 4L),
                new SearchParams<>(
                    new CustomerFilter(null, null, null),
                    2,
                    2
                )
            ),

            Arguments.of(
                List.of(4L, 3L, 5L, 2L, 1L),
                new SearchParams<>(
                    new CustomerFilter(null, null, null),
                    0,
                    10,
                    Map.of("spent", "desc")
                )
            ),

            Arguments.of(
                List.of(3L),
                new SearchParams<>(
                    new CustomerFilter(3L, null, null),
                    0,
                    1
                )
            ),

            Arguments.of(
                List.of(5L),
                new SearchParams<>(
                    new CustomerFilter(null, "Test", null)
                )
            ),

            Arguments.of(
                List.of(1L),
                new SearchParams<>(
                    new CustomerFilter(null, null, "+79008001020")
                )
            )
        );
    }
}
