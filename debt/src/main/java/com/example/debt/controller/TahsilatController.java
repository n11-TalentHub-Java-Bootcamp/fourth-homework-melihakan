package com.example.debt.controller;



import com.example.debt.dto.BorcDto;
import com.example.debt.dto.TahsilatDto;
import com.example.debt.service.TahsilatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tahsilat")
@AllArgsConstructor
public class TahsilatController {

    private TahsilatService tahsilatService;

    @PostMapping("/collection")
    public TahsilatDto save(@RequestBody TahsilatDto tahsilatDto){
        return tahsilatService.save(tahsilatDto);
    }

    @GetMapping("/date/{startDate}/{endDate}")
    public List<TahsilatDto> findByTahsilatList(@PathVariable String startDate, @PathVariable String endDate) {
        return tahsilatService.findByOlusmaTarihiGreaterThanEqualAndOlusmaTarihiLessThanEqual(LocalDate.parse(startDate),LocalDate.parse(endDate));
    }

    @GetMapping("/user/{kullaniciId}")
    public List<TahsilatDto> findByKullaniciIdOrderByTahsilatTutari(@PathVariable Long kullaniciId){
        return tahsilatService.findByKullaniciIdOrderByTahsilatTutari(kullaniciId);
    }
    @GetMapping("/sum//{kullaniciId}")
    public TahsilatDto findByKullaniciAndTahsilatTutari(@PathVariable Long kullaniciId){
        return tahsilatService.findByKullaniciAndTahsilatTutari(kullaniciId);
    }

}
