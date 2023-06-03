package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.HddDTO;
import java.util.Collection;

/**
 * Сервис Жесткого диска
 */

public interface HddService {

  /**
   * Добавление в магазин Жесткого диска
   * @param hddDTO
   * @return
   */
  HddDTO add(HddDTO hddDTO);

  /**
   * Изменение данных Жесткого диска
   * @param id
   * @param VolumeGb
   * @param serialNumber
   * @param manufacturer
   * @param price
   * @param quantity
   * @return
   */
  HddDTO patch(Long id, Integer VolumeGb, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Жестких дисках в магазине
   * @return
   */
  Collection<HddDTO> findAll();

  /**
   * Поиск Жесткого диска по идентификатору
   * @param id
   * @return
   */
  HddDTO findById(Long id);
}
