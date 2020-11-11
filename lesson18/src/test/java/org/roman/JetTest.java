package org.roman;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.roman.controller.JetController;
import org.roman.model.Jet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JetTest {

    @Autowired
    public JetController controller;

    @Autowired
    public DataSource dataSource;

    @AfterEach
    public void clear() {
        JdbcTestUtils.deleteFromTables(new JdbcTemplate(dataSource), "jet");
    }

    @Test
    void testCreate() {
        Jet ssj100 = Jet.builder()
                .name("SSJ-100")
                .cruisingSpeed(830.0)
                .length(29.94)
                .maxAltitude(12_200)
                .passengerCapacity(108)
                .build();

        Jet createdSsj = controller.create(ssj100);

        assertNotNull(createdSsj);

        List<Jet> allJets = controller.getAllJets();

        assertEquals(1, allJets.size());
    }

    @Test
    void testUpdate() {
        Jet il18 = Jet.builder()
                .name("Ил-18")
                .cruisingSpeed(625.0)
                .length(35.9)
                .maxAltitude(10_170)
                .passengerCapacity(122)
                .build();

        Jet createdIL = controller.create(il18);

        assertNotNull(createdIL);

        createdIL.setName("Ил-18Д");
        createdIL.setPassengerCapacity(20);

        Jet updatedIL = controller.update(createdIL.getId(), createdIL);

        assertNotNull(updatedIL);
        assertEquals("Ил-18Д", updatedIL.getName());

        List<Jet> allJets = controller.getAllJets();

        assertEquals(1, allJets.size());
    }

    @Test
    void testRemove() {
        Jet yak40 = Jet.builder()
                .name("Як-40")
                .cruisingSpeed(510.0)
                .length(20.36)
                .maxAltitude(8_000)
                .passengerCapacity(40)
                .build();

        Jet createdYak = controller.create(yak40);

        assertNotNull(createdYak);

        List<Jet> allJets = controller.getAllJets();

        assertEquals(1, allJets.size());

        controller.remove(createdYak.getId());

        allJets = controller.getAllJets();

        assertEquals(0, allJets.size());
    }
}
