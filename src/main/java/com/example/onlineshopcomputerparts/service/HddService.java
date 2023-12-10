package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import com.example.onlineshopcomputerparts.dto.HddFullDTO;

import java.util.Collection;

/**
 * Сервис Жесткого диска
 */

public interface HddService {

  /**
   * Добавление в магазин Жесткого диска
   */
  HddFullDTO add(HddDTO hddDTO);

  /**
   * Изменение данных Жесткого диска
   */
  HddFullDTO patch(Long id, HddDTO hddDTO);

  /**
   * Данные о всех Жестких дисках в магазине
   */
  Collection<HddFullDTO> findAll();

  /**
   * Поиск Жесткого диска по идентификатору
   */
  HddFullDTO findById(Long id);
}
