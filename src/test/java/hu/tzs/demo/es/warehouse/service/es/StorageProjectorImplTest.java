package hu.tzs.demo.es.warehouse.service.es;

import hu.tzs.demo.es.warehouse.persist.StorageEventRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class StorageProjectorImplTest {

    @Mock
    private StorageEventRepository storageEventRepository;

    @InjectMocks
    private StorageProjectorImpl storageProjector;

    @Test
    void calculateStorage() {
    }

    @Test
    void calculateStorageOfProduct() {
    }
}