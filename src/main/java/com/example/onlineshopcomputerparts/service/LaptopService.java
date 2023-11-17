package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import java.util.Collection;

/**
 * Сервис Ноутбука
 */

public interface LaptopService {

  /**
   * Добавление в магазин Ноутбука
   */
  LaptopDTO add(LaptopDTO laptopDTO);

  /**
   * Изменение данных Ноутбука
   */
  LaptopDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Ноутбуках в магазине
   */
  Collection<LaptopDTO> findAll();

  /**
   * Поиск Ноутбука по идентификатору
   */
  LaptopDTO findById(Long id);
}
