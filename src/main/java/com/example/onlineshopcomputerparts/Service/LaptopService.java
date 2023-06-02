package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.LaptopDTO;
import java.util.Collection;

/**
 * Сервис Ноутбука
 */

public interface LaptopService {

  /**
   * Добавление в магазин Ноутбука
   * @param laptopDTO
   * @return
   */
  LaptopDTO add(LaptopDTO laptopDTO);

  /**
   * Изменение даннх Ноутбука
   * @param id
   * @param diagonal
   * @param serialNumber
   * @param manufacturer
   * @param price
   * @param quantity
   * @return
   */
  LaptopDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Ноутбуках в магазине
   * @return
   */
  Collection<LaptopDTO> findAll();

  /**
   * Поиск Ноутбука по идентификатору
   * @param id
   * @return
   */
  LaptopDTO findById(Long id);
}
