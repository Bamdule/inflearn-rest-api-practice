package com.restapi.events;


import lombok.Builder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Builder
class EventTest {
    @Test
    public void testBuilder(){
        Event event = Event.builder()
                .name("name!")
                .description("desc")
                .build();
        Assertions.assertThat(event).isNotNull();
    }
    @Test
    public void javaBean(){
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

}