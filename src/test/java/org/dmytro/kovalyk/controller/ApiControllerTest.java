package org.dmytro.kovalyk.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dmytro.kovalyk.OnStartUpRunner;
import org.dmytro.kovalyk.service.BusRouteService;
import org.dmytro.kovalyk.util.UrlConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {

    private static final String DEPARTURE_SID = "153";
    private static final String ARRIVAL_SID = "150";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusRouteService _busRouteService;

    @MockBean
    private OnStartUpRunner _onStartUpRunner;

    @Before
    public void setUp() throws Exception {
        given(_busRouteService.isDirectBusRoute(DEPARTURE_SID, ARRIVAL_SID)).willReturn(true);
        given(_busRouteService.isDirectBusRoute("-1", "-1")).willReturn(false);

        willDoNothing().given(_onStartUpRunner).run(anyVararg());
    }

    @Test
    public void testDirectNoParam() throws Exception {
        this.mockMvc.perform(get(UrlConstants.API_DIRECT_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.direct_bus_route").value(false)).andExpect(jsonPath("$.dep_sid").value(-1))
                .andExpect(jsonPath("$.arr_sid").value(-1));
    }

    @Test
    public void testDirectNonEmptyParams() throws Exception {
        this.mockMvc
                .perform(get(UrlConstants.API_DIRECT_URL).param(UrlConstants.DEPARTURE_SID, DEPARTURE_SID)
                        .param(UrlConstants.ARRIVAL_SID, ARRIVAL_SID))
                .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.direct_bus_route").value(true))
                .andExpect(jsonPath("$.dep_sid").value(153)).andExpect(jsonPath("$.arr_sid").value(150));
    }
}
