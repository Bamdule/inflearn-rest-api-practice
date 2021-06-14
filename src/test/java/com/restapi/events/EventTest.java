package com.restapi.events;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

@RunWith(JUnitParamsRunner.class)
class EventTest {
    @Test
    public void testBuilder() {
        Event event = Event.builder()
                .name("name!")
                .description("desc")
                .build();
        Assertions.assertThat(event).isNotNull();
    }

    @Test
    public void javaBean() {
        //given
        String name = "Event";
        String description = "Event";

        //when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //then
        Assertions.assertThat(event.getName()).isEqualTo(name);
        Assertions.assertThat(event.getDescription()).isEqualTo(description);

    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 100, false",
            "100, 0, false",
    })
    public void testFree(int basePrice, int maxPrice, boolean isFree) {
        //Given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        //When
        event.update();

        // Then
        Assertions.assertThat(event.isFree()).isEqualTo(isFree);
    }

    private static Stream<Arguments> isFreeParam() {
        return Stream.of(
                Arguments.of(0,0, true),
                Arguments.of(0,100, false),
                Arguments.of(100,0, true)
        );
    }

    @ParameterizedTest
    @MethodSource("isOfflineParam")
    public void testOffline(String location, boolean offline) {
        //Given
        Event event = Event.builder()
                .location(location)
                .build();
        //When
        event.update();

        // Then
        Assertions.assertThat(event.isOffline()).isEqualTo(offline);
    }

    private static Stream<Arguments> isOfflineParam() {
        return Stream.of(
                Arguments.of("강남", true),
                Arguments.of("", false),
                Arguments.of(null, false)
        );
    }


}