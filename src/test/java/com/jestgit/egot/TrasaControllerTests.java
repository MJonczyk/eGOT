package com.jestgit.egot;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.punkt.Punkt;
import com.jestgit.egot.region.Region;
import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.trasa.TrasaController;
import com.jestgit.egot.trasa.TrasaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TrasaController.class)
public class TrasaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrasaService trasaService;

    @Test
    public void getIndex_ShouldReturnIndexView() throws Exception{
        mockMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/index"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAll_ShouldReturnWyswietlViewAndModelWithCurrentRoutes() throws Exception{
        Punkt p1 = new Punkt("Łącznik",  (float)15.2925, (float)50.888333, (float)1066.0);
        Punkt p2 = new Punkt("Czerniawa Zdrój", (float)15.302, (float)50.915361,(float)540.0);
        Punkt p3 = new Punkt("Polana Izerska", (float)15.343256, (float)50.877719, (float)965.0);
        Punkt p4 = new Punkt("Suchacz", (float)15.904, (float)50.398, (float)917.0);

        Trasa t1 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p2, "1/2", "","S", "001/2004/A", 1);
        Trasa t2 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p3, "12/5", "","S", "001/2004/A", 1);
        Trasa t3 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p4, "7/7", "","S", "001/2004/A", 1);

        ArrayList<Trasa> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        t.add(t3);

        when(trasaService.getAll()).thenReturn(t);
        mockMvc.perform(get("/wyswietl"))
                .andExpect(view().name("wyswietl"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("trasy", hasSize(3)))
                .andExpect(model().attribute("trasy", hasItem(
                        allOf(
                                hasProperty("punktPoczatkowy", is(p1)),
                                hasProperty("punktKoncowy", is(p2)),
                                hasProperty("punktyZaTrase", is("1/2"))
                        )
                )))
                .andExpect(model().attribute("trasy", hasItem(
                        allOf(
                                hasProperty("punktPoczatkowy", is(p1)),
                                hasProperty("punktKoncowy", is(p3)),
                                hasProperty("punktyZaTrase", is("12/5"))
                        )
                )))
                .andExpect(model().attribute("trasy", hasItem(
                        allOf(
                                hasProperty("punktPoczatkowy", is(p1)),
                                hasProperty("punktKoncowy", is(p4)),
                                hasProperty("punktyZaTrase", is("7/7"))
                        )
                )));

        when(trasaService.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/wyswietl"))
                .andExpect(view().name("wyswietl"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("trasy", hasSize(0)));

        t.remove(2);
        t.remove(1);
        when(trasaService.getAll()).thenReturn(t);
        mockMvc.perform(get("/wyswietl"))
                .andExpect(view().name("wyswietl"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("trasy", hasSize(1)))
                .andExpect(model().attribute("trasy", hasItem(
                        allOf(
                                hasProperty("punktPoczatkowy", is(p1)),
                                hasProperty("punktKoncowy", is(p2)),
                                hasProperty("punktyZaTrase", is("1/2"))
                        )
                )));
    }
}
