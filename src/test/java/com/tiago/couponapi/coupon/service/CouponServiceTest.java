package com.tiago.couponapi.coupon.service;

import com.tiago.couponapi.coupon.dto.CouponRequest;
import com.tiago.couponapi.coupon.dto.CouponResponse;
import com.tiago.couponapi.coupon.mapper.CouponMapper;
import com.tiago.couponapi.coupon.model.Coupon;
import com.tiago.couponapi.coupon.model.CouponStatus;
import com.tiago.couponapi.coupon.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponMapper couponMapper;

    @InjectMocks
    private CouponService couponService;

    @Test
    @DisplayName("Deve criar o cupom quando os dados forem válidos e o código tiver 6 caracteres")
    void shouldCreateCouponSuccessfully() {
        // Arrange
        CouponRequest request = new CouponRequest("ABC-123", "Desc", BigDecimal.valueOf(0.8), Instant.now(), true);
        Coupon couponEntity = new Coupon();
        couponEntity.setId(UUID.randomUUID());

        when(couponMapper.toEntity(eq(request), eq("ABC123"))).thenReturn(couponEntity);
        when(couponRepository.save(any(Coupon.class))).thenReturn(couponEntity);
        when(couponMapper.toDto(any(Coupon.class))).thenReturn(createCouponResponse(couponEntity));

        // Act
        CouponResponse response = couponService.createCoupon(request);

        // Assert
        assertNotNull(response);
        verify(couponRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando o código não tiver 6 caracteres")
    void shouldThrowIllegalArgumentExceptionWhenCodeIsInvalid() {
        // Arrange
        CouponRequest request = new CouponRequest("ABCD--123", "Desc", BigDecimal.valueOf(0.8), Instant.now(), true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            couponService.createCoupon(request);
        });

        assertEquals("O código do cupom deve ter exatamente 6 caracteres.", exception.getMessage());
        verify(couponRepository, never()).save(any());
    }


    @Test
    @DisplayName("Deve retornar o cupom pelo ID se existir no banco")
    void shouldReturnCouponById() {
        // Arrange
        UUID id = UUID.randomUUID();
        Coupon coupon = new Coupon();
        coupon.setId(id);

        when(couponRepository.findById(id)).thenReturn(Optional.of(coupon));
        when(couponMapper.toDto(coupon)).thenReturn(createCouponResponse(coupon));

        // Act
        CouponResponse response = couponService.getCouponById(id);

        // Assert
        assertNotNull(response);
        assertEquals(id, response.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando cupom não for encontrado")
    void shouldThrowIllegalArgumentExceptionWhenCouponNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(couponRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            couponService.getCouponById(id);
        });
    }

    @Test
    @DisplayName("Deve realizar soft delete com sucesso")
    void shouldSoftDeleteCouponSuccessfully() {
        //Arrange
        UUID id = UUID.randomUUID();
        Coupon coupon = new Coupon();
        coupon.setStatus(CouponStatus.ACTIVE);

        when(couponRepository.findById(id)).thenReturn(Optional.of(coupon));

        // Act
        couponService.deleteCouponById(id);

        // Assert
        verify(couponRepository, times(1)).save(coupon);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar cupom já deletado")
    void shouldThrowIllegalArgumentExceptionWhenDeletingAlreadyDeletedCoupon() {
        // Arrange
        UUID id = UUID.randomUUID();
        Coupon coupon = new Coupon();
        coupon.setStatus(CouponStatus.DELETED);

        when(couponRepository.findById(id)).thenReturn(Optional.of(coupon));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            couponService.deleteCouponById(id);
        });

        verify(couponRepository, never()).save(coupon);
    }

    private CouponResponse createCouponResponse(Coupon couponEntity) {
        return new CouponResponse(
                couponEntity.getId(),
                couponEntity.getCode(),
                couponEntity.getDescription(),
                couponEntity.getDiscountValue(),
                couponEntity.getExpirationDate(),
                couponEntity.getPublished(),
                couponEntity.getRedeemed(),
                couponEntity.getStatus()
        );
    }
}
