package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.HddDTO;
import java.util.Collection;

/**
 * Сервис Жесткого диска
 */

public interface HddService {

  /**
   * Добавление в магазин Жесткого диска
   */
  HddDTO add(HddDTO hddDTO);

  /**
   * Изменение данных Жесткого диска
   */
  HddDTO patch(Long id, Integer volumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Жестких дисках в магазине
   */
  Collection<HddDTO> findAll();

  /**
   * Поиск Жесткого диска по идентификатору
   */
  HddDTO findById(Long id);
}
