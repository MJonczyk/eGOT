package com.jestgit.egot;

import com.jestgit.egot.grupa.Grupa;
import com.jestgit.egot.punkt.Punkt;
import com.jestgit.egot.region.Region;
import com.jestgit.egot.trasa.Trasa;
import com.jestgit.egot.trasa.TrasaRepository;
import com.jestgit.egot.trasa.TrasaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrasaServiceTests {

    private static final Long id = 1L;
    private Punkt p1, p2, p3, p4, p5, p6;

    private Trasa t1, t2, t3;

    private ArrayList<Trasa> t;
    @Mock
    private TrasaRepository trasaRepo;

    @InjectMocks
    private TrasaService trasaService;

    @Before
    public void init(){
        p1 = new Punkt("Łącznik",  (float)15.2925, (float)50.888333, (float)1066.0);
        p2 = new Punkt("Czerniawa Zdrój", (float)0.0, (float)0.0,(float)540.0);
        p3 = new Punkt("Polana Izerska", (float)180.0, (float)90.0, (float)965.0);
        p4 = new Punkt("Suchacz", (float)15.904, (float)50.398, (float)917.0);
        p5 = new Punkt("Czerniawska Kopa",  (float)0.1, (float)0.1, (float)776.0);
        p6 = new Punkt("Ulicko - Pobiedna",  (float)179.9, (float)89.9, (float)1066.0);

        t1 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p2, "1/2", "","S", "001/2004/A", 1);
        t2 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p3, "12/5", "","S", "001/2004/A", 1);
        t3 = new Trasa(new Grupa("Góry Izerskie", new Region("Sudety", ""), "T.01"), p1, p4, "7/7", "","S", "001/2004/A", 1);

        t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        t.add(t3);
    }

    @Test
    public void getOne_IsNotNull(){
        when(trasaRepo.getOne(id)).thenReturn(t1);
        assertNotNull(trasaService.getOne(id));
        verify(trasaRepo, times(1)).getOne(anyLong());
        verifyNoMoreInteractions(trasaRepo);
    }

    @Test
    public void getOne_IsNull(){
        when(trasaRepo.getOne(id)).thenReturn(null);
        assertNull(trasaService.getOne(id));
        verify(trasaRepo, times(1)).getOne(anyLong());
        verifyNoMoreInteractions(trasaRepo);
    }

    @Test
    public void distanceBeetweenPointsTest(){
        assertEquals(0, (double)trasaService.distanceBetweenPoints(p1, p1), 0.001);
        assertEquals(69.512, (double)trasaService.distanceBetweenPoints(p1, p4), 0.001);
        assertEquals(69.512, (double)trasaService.distanceBetweenPoints(p4, p1), 0.001);
        assertEquals(10007.543, (double)trasaService.distanceBetweenPoints(p2, p3), 0.001);
        assertEquals(15.725, (double)trasaService.distanceBetweenPoints(p2, p5), 0.001);
        assertEquals(11.119323, (double)trasaService.distanceBetweenPoints(p3, p6), 0.001);
    }
}
