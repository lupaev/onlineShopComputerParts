package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.LaptopDTO;
import com.example.onlineshopcomputerparts.dto.LaptopFullDTO;

import java.util.Collection;

/**
 * Сервис Ноутбука
 */

public interface LaptopService {

  /**
   * Добавление в магазин Ноутбука
   */
  LaptopFullDTO add(LaptopDTO laptopDTO);

  /**
   * Изменение данных Ноутбука
   */
  LaptopFullDTO patch(Long id, LaptopDTO laptopDTO);

  /**
   * Данные о всех Ноутбуках в магазине
   */
  Collection<LaptopFullDTO> findAll();

  /**
   * Поиск Ноутбука по идентификатору
   */
  LaptopFullDTO findById(Long id);
}
