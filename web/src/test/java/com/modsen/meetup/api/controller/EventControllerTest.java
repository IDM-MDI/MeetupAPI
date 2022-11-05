package com.modsen.meetup.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.modsen.meetup.api.config.MeetupApiApplication;
import com.modsen.meetup.api.dto.EventDto;
import com.modsen.meetup.api.dto.PaginationInfo;
import com.modsen.meetup.api.dto.ResponsePage;
import com.modsen.meetup.api.entity.Event;
import com.modsen.meetup.api.service.PageEventService;
import com.modsen.meetup.api.util.ResponseStatusUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static com.modsen.meetup.api.entity.EntityName.EVENT;
import static com.modsen.meetup.api.entity.EntityStatus.ACTIVE;
import static com.modsen.meetup.api.util.ResponseStatusUtil.pageFoundResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MeetupApiApplication.class)
@AutoConfigureMockMvc
class EventControllerTest {
    private final MediaType halJson = MediaType.valueOf("application/hal+json");
    private final MediaType halJsonUTF = MediaType.valueOf("application/hal+json;charset=UTF-8");
    private static final String URL = "/api/v1/event";
    private static final long ID = 1;
    private static final String TEST = "TEST";
    private static final Event ENTITY =
            Event.builder()
                    .id(1)
                    .topic(TEST)
                    .description(TEST + TEST + TEST)
                    .venue(null)
                    .manager(null)
                    .date(LocalDateTime.of(2012,12,12,12,12))
                    .status(ACTIVE.name())
                    .build();
    private static final EventDto DTO =
            EventDto.builder()
                    .id(1)
                    .topic(TEST)
                    .description(TEST + TEST + TEST)
                    .venue(null)
                    .manager(null)
                    .date(LocalDateTime.of(2012,12,12,12,12))
                    .status(ACTIVE.name())
                    .build();
    private static final List<Event> ENTITY_LIST = List.of(ENTITY);

    private static final List<EventDto> DTO_LIST = List.of(DTO);
    private static final PaginationInfo PAGINATION =
            PaginationInfo.builder()
                    .page(0)
                    .direction("ASC")
                    .sort("id")
                    .filter("")
                    .size(5)
                    .build();
    @Autowired
    private EventController controller;
    @MockBean
    private PageEventService service;
    @Autowired
    private MockMvc mockMvc;
    @SneakyThrows
    @Test
    void getActiveEvents() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .paginationInfo(PAGINATION)
                .status(pageFoundResponse(EVENT.toString()))
                .build();
        when(service.findByActivePage(PAGINATION))
                .thenReturn(expected);
        mockMvc.perform(get(URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is((int) DTO.getId())))
                .andExpect(jsonPath("$.data[0].topic", is(DTO.getTopic())));
    }

    @SneakyThrows
    @Test
    void addEvent() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.saveResponse(EVENT.toString()))
                .build();
        when(service.save(any()))
                .thenReturn(expected);
        mockMvc.perform(post(URL)
                        .contentType(halJsonUTF)
                        .content(objectMapper.writeValueAsString(DTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is((int) DTO.getId())))
                .andExpect(jsonPath("$.data[0].topic", is(DTO.getTopic())));
    }

    @SneakyThrows
    @Test
    void getEventByID() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .paginationInfo(PAGINATION)
                .status(pageFoundResponse(EVENT.toString()))
                .build();
        when(service.findByID(ID))
                .thenReturn(expected);
        mockMvc.perform(get(URL + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is((int) DTO.getId())))
                .andExpect(jsonPath("$.data[0].topic", is(DTO.getTopic())));
    }

    @SneakyThrows
    @Test
    void updateEvent() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.updateResponse(EVENT.toString()))
                .build();

        when(service.update(any(),eq(ID)))
                .thenReturn(expected);
        mockMvc.perform(patch(URL + "/" + ID)
                        .contentType(halJsonUTF)
                        .content(objectMapper.writeValueAsString(DTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is((int) DTO.getId())))
                .andExpect(jsonPath("$.data[0].topic", is(DTO.getTopic())));
    }

    @SneakyThrows
    @Test
    void deleteEvent() {
        ResponsePage<EventDto> expected = ResponsePage.<EventDto>builder()
                .data(DTO_LIST)
                .status(ResponseStatusUtil.deleteResponse(EVENT.toString()))
                .build();
        when(service.delete(ID))
                .thenReturn(expected);
        mockMvc.perform(delete(URL + "/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", is((int) DTO.getId())))
                .andExpect(jsonPath("$.data[0].topic", is(DTO.getTopic())));
    }
}