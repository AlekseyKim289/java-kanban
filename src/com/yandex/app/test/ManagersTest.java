package com.yandex.app.test;

import com.yandex.app.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    @Test
    void getDefaultReturnsInitializedTaskManager() {
        TaskManager manager = Managers.getDefault();
        assertNotNull(manager);
        assertTrue(manager instanceof InMemoryTaskManager);
    }

    @Test
    void getDefaultHistoryReturnsInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
        assertTrue(historyManager instanceof InMemoryHistoryManager);
    }
}