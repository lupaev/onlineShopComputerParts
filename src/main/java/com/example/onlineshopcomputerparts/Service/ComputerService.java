package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.ComputerDTO;
import java.util.Collection;

/**
 * Сервис Компьютера
 */

public interface ComputerService {

  /**
   * Добавление в магазин Компьютера
   * @param computerDTO
   * @return
   */
  ComputerDTO add(ComputerDTO computerDTO);

  /**
   * Изменение данных Компьютера
   * @param id
   * @param form
   * @param serialNumber
   * @param manufacturer
   * @param price
   * @param quantity
   * @return
   */
  ComputerDTO patch(Long id, String form, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Компьютерах в магазине
   * @return
   */
  Collection<ComputerDTO> findAll();

  /**
   * Поиск Компьютера по идентификатору
   * @param id
   * @return
   */
  ComputerDTO findById(Long id);
}
