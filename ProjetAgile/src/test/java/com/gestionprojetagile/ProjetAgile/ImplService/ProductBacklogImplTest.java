package com.gestionprojetagile.ProjetAgile.ImplService;

import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import com.gestionprojetagile.ProjetAgile.web.repositories.ProdBacklogRepo;
import com.gestionprojetagile.ProjetAgile.web.service.ImplService.ProductBacklogImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductBacklogImplTest {

    @Mock
    private ProdBacklogRepo productBacklogRepo;

    @InjectMocks
    private ProductBacklogImpl productBacklogService;

    private ProductBacklog productBacklog;

    @BeforeEach
    void setUp() {
        productBacklog = new ProductBacklog();
        productBacklog.setId(1L);
        productBacklog.setName("Backlog 1");
    }

    @Test
    void testCreateProductBacklog() {
        when(productBacklogRepo.save(any(ProductBacklog.class))).thenReturn(productBacklog);

        ProductBacklog createdProductBacklog = productBacklogService.createProductBacklog(productBacklog);

        assertNotNull(createdProductBacklog);
        assertEquals(productBacklog.getId(), createdProductBacklog.getId());
        assertEquals(productBacklog.getName(), createdProductBacklog.getName());

        verify(productBacklogRepo, times(1)).save(any(ProductBacklog.class));
    }

    @Test
    void testGetProductBacklogById() {
        when(productBacklogRepo.findById(1L)).thenReturn(Optional.of(productBacklog));

        ProductBacklog foundProductBacklog = productBacklogService.getProductBacklogById(1L);

        assertNotNull(foundProductBacklog);
        assertEquals(productBacklog.getId(), foundProductBacklog.getId());
        assertEquals(productBacklog.getName(), foundProductBacklog.getName());

        verify(productBacklogRepo, times(1)).findById(1L);
    }

    @Test
    void testGetProductBacklogById_NotFound() {
        when(productBacklogRepo.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productBacklogService.getProductBacklogById(2L);
        });

        assertEquals("Product Backlog non trouvé avec l'ID : 2", exception.getMessage());

        verify(productBacklogRepo, times(1)).findById(2L);
    }

    @Test
    void testGetAllProductBacklogs() {
        when(productBacklogRepo.findAll()).thenReturn(Arrays.asList(productBacklog));

        List<ProductBacklog> productBacklogs = productBacklogService.getAllProductBacklogs();

        assertNotNull(productBacklogs);
        assertEquals(1, productBacklogs.size());
        assertEquals(productBacklog.getId(), productBacklogs.get(0).getId());
        assertEquals(productBacklog.getName(), productBacklogs.get(0).getName());

        verify(productBacklogRepo, times(1)).findAll();
    }

    @Test
    void testUpdateProductBacklog() {
        when(productBacklogRepo.findById(1L)).thenReturn(Optional.of(productBacklog));
        when(productBacklogRepo.save(any(ProductBacklog.class))).thenReturn(productBacklog);

        ProductBacklog updatedProductBacklog = productBacklogService.updateProductBacklog(1L, productBacklog);

        assertNotNull(updatedProductBacklog);
        assertEquals(productBacklog.getId(), updatedProductBacklog.getId());
        assertEquals(productBacklog.getName(), updatedProductBacklog.getName());

        verify(productBacklogRepo, times(1)).findById(1L);
        verify(productBacklogRepo, times(1)).save(any(ProductBacklog.class));
    }

    @Test
    void testDeleteProductBacklog() {
        doNothing().when(productBacklogRepo).deleteById(1L);

        productBacklogService.deleteProductBacklog(1L);

        verify(productBacklogRepo, times(1)).deleteById(1L);
    }
}